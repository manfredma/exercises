package jdk.serialization;

import java.nio.charset.StandardCharsets;

/**
 * byte[] → String 转换的深度分析与优化
 *
 * 核心问题：new String(bytes, charset) 会涉及 byte[] → char[] 转换开销
 *
 * JDK 内部转换流程：
 * 1. byte[] → CharsetDecoder 解码 → char[] → String 内部存储
 * 2. JDK 9+ 优化：Latin-1 字符串直接存 byte[]（Compact Strings）
 *
 * 优化方案：
 * 1. JDK 9+ Compact Strings 优化（自动）
 * 2. 使用 String 的私有构造器（反射，不推荐）
 * 3. ASCII 快速路径优化
 * 4. 字符串池化（String intern）
 * 5. 避免不必要的转换
 */
public class DirectByteBufferString {

    public static void main(String[] args) {
        System.out.println("=== JDK 版本信息 ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));

        System.out.println("\n=== 场景 1: byte[] → String 转换开销分析 ===");
        analyzeConversionOverhead();

        System.out.println("\n=== 场景 2: ASCII 快速路径优化 ===");
        benchmarkASCIIOptimization();

        System.out.println("\n=== 场景 3: JDK 9+ Compact Strings 优化 ===");
        benchmarkCompactStrings();

        System.out.println("\n=== 场景 4: 字符串复用策略 ===");
        benchmarkStringReuse();
    }

    /**
     * 场景 1: 分析 byte[] → String 的转换开销
     *
     * JDK 内部流程（简化版）：
     * 1. new String(byte[], Charset)
     * 2. → StringCoding.decode(charset, bytes, offset, length)
     * 3. → CharsetDecoder.decode(ByteBuffer)
     * 4. → 分配 char[] 数组
     * 5. → 逐字节解码填充 char[]
     * 6. → 创建 String 对象（可能直接用 byte[] 如果是 Latin-1）
     */
    private static void analyzeConversionOverhead() {
        // 测试 1: UTF-8 中文字符串（需要 byte[] → char[] 转换）
        byte[] utf8Chinese = "这是一段中文测试文本".getBytes(StandardCharsets.UTF_8);

        // 测试 2: ASCII 字符串（JDK 9+ 可能直接用 byte[] 存储）
        byte[] ascii = "This is an ASCII test text".getBytes(StandardCharsets.UTF_8);

        // 测试 3: Latin-1 字符串（JDK 9+ Compact Strings 优化）
        byte[] latin1 = "Hello World 123".getBytes(StandardCharsets.ISO_8859_1);

        int iterations = 1_000_000;

        // UTF-8 中文
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = new String(utf8Chinese, StandardCharsets.UTF_8);
        }
        long utf8Time = System.nanoTime() - start;

        // ASCII
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = new String(ascii, StandardCharsets.UTF_8);
        }
        long asciiTime = System.nanoTime() - start;

        // Latin-1
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = new String(latin1, StandardCharsets.ISO_8859_1);
        }
        long latin1Time = System.nanoTime() - start;

        System.out.println("  UTF-8 中文 (" + utf8Chinese.length + " bytes): " + (utf8Time / 1_000_000) + "ms");
        System.out.println("  ASCII (" + ascii.length + " bytes):      " + (asciiTime / 1_000_000) + "ms");
        System.out.println("  Latin-1 (" + latin1.length + " bytes):   " + (latin1Time / 1_000_000) + "ms");
        System.out.println("\n  分析:");
        System.out.println("  - UTF-8 中文: 需要完整的 byte[] → char[] 解码（1个中文=3字节→1个char）");
        System.out.println("  - ASCII/Latin-1: JDK 9+ 可能直接用 byte[] 存储（Compact Strings）");
    }

    /**
     * 场景 2: ASCII 快速路径优化
     *
     * 优化思路：
     * 1. 如果确定是纯 ASCII（0-127），可以手动快速转换
     * 2. 避免 CharsetDecoder 的复杂逻辑
     * 3. 对于大量 ASCII 字符串处理场景有效
     */
    private static void benchmarkASCIIOptimization() {
        byte[] asciiBytes = "This is a pure ASCII string for testing performance optimization techniques in Java".getBytes(StandardCharsets.UTF_8);
        int iterations = 1_000_000;

        // 预热
        for (int i = 0; i < 10000; i++) {
            new String(asciiBytes, StandardCharsets.UTF_8);
            fastASCIItoString(asciiBytes);
        }

        // 方式 1: 标准 new String()
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = new String(asciiBytes, StandardCharsets.UTF_8);
        }
        long standard = System.nanoTime() - start;

        // 方式 2: 手动快速转换（仅限纯 ASCII）
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = fastASCIItoString(asciiBytes);
        }
        long fast = System.nanoTime() - start;

        System.out.println("  标准方式 new String(): " + (standard / 1_000_000) + "ms");
        System.out.println("  手动 ASCII 转换:       " + (fast / 1_000_000) + "ms");
        System.out.println("  性能提升: " + String.format("%.1f%%", (standard - fast) * 100.0 / standard));
        System.out.println("\n  注意: 手动优化通常不推荐，JDK 内部已经高度优化");
        System.out.println("       只在极端性能要求且确定是纯 ASCII 时考虑");
    }

    /**
     * 快速 ASCII 转 String（仅适用于纯 ASCII 字符 0-127）
     * 警告：生产环境不推荐，仅用于演示
     */
    private static String fastASCIItoString(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) (bytes[i] & 0xFF);  // 强制转换，假设是 ASCII
        }
        return new String(chars);
    }

    /**
     * 场景 3: JDK 9+ Compact Strings 优化
     *
     * JDK 9 引入的重大优化：
     * - 如果字符串所有字符都是 Latin-1（0-255），直接用 byte[] 存储
     * - 否则用 char[] 存储（UTF-16）
     * - 内部有 coder 标识：LATIN1 = 0, UTF16 = 1
     *
     * 这意味着：
     * - ASCII/Latin-1 字符串：byte[] → byte[]（几乎无转换开销）
     * - 包含中文/emoji：byte[] → char[]（有转换开销）
     */
    private static void benchmarkCompactStrings() {
        // Latin-1 字符串（可以用 byte[] 存储）
        String latin1Str = "Hello World 123 !@#$%";
        byte[] latin1Bytes = latin1Str.getBytes(StandardCharsets.ISO_8859_1);

        // UTF-16 字符串（需要用 char[] 存储）
        String utf16Str = "Hello 世界 🚀";
        byte[] utf16Bytes = utf16Str.getBytes(StandardCharsets.UTF_8);

        int iterations = 1_000_000;

        // 预热
        for (int i = 0; i < 10000; i++) {
            new String(latin1Bytes, StandardCharsets.ISO_8859_1);
            new String(utf16Bytes, StandardCharsets.UTF_8);
        }

        // Latin-1 转换
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = new String(latin1Bytes, StandardCharsets.ISO_8859_1);
        }
        long latin1Time = System.nanoTime() - start;

        // UTF-16 转换
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = new String(utf16Bytes, StandardCharsets.UTF_8);
        }
        long utf16Time = System.nanoTime() - start;

        System.out.println("  Latin-1 字符串: " + (latin1Time / 1_000_000) + "ms (Compact Strings 优化)");
        System.out.println("  UTF-16 字符串:  " + (utf16Time / 1_000_000) + "ms (需要 byte[]→char[] 转换)");
        System.out.println("  性能差异: " + String.format("%.1f%%", (utf16Time - latin1Time) * 100.0 / latin1Time));
        System.out.println("\n  结论: JDK 9+ 对 Latin-1 字符串自动优化，几乎消除了转换开销");
        System.out.println("       包含中文/emoji 的字符串仍有转换成本");
    }

    /**
     * 场景 4: 字符串复用策略 - 避免重复转换
     *
     * 最佳实践：
     * 1. 如果同一个 byte[] 需要多次转换，缓存 String 结果
     * 2. 使用 String.intern() 池化（小心内存泄漏）
     * 3. 使用缓存框架（如 Caffeine）
     * 4. 避免不必要的编码/解码往返
     */
    private static void benchmarkStringReuse() {
        byte[] data = "Repeated conversion test".getBytes(StandardCharsets.UTF_8);
        int iterations = 1_000_000;

        // 方式 1: 每次都转换
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = new String(data, StandardCharsets.UTF_8);
        }
        long repeated = System.nanoTime() - start;

        // 方式 2: 只转换一次，复用结果
        start = System.nanoTime();
        String cached = new String(data, StandardCharsets.UTF_8);
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
            String s = cached;  // 直接复用
        }
        long reused = System.nanoTime() - start;

        System.out.println("  重复转换: " + (repeated / 1_000_000) + "ms");
        System.out.println("  复用结果: " + (reused / 1_000_000) + "ms");
        System.out.println("  性能提升: " + String.format("%.1f%%", (repeated - reused) * 100.0 / repeated));
        System.out.println("\n  最佳实践:");
        System.out.println("  1. 缓存频繁使用的 String 对象");
        System.out.println("  2. 避免 String → byte[] → String 往返转换");
        System.out.println("  3. 考虑使用 String.intern() 或缓存框架");
        System.out.println("  4. 评估是否真的需要 String，有时 byte[] 就够了");
    }
}