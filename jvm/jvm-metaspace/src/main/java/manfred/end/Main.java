package manfred.end;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {
    public static void main(String[] args) {
        // 创建脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        // 获取 JavaScript 引擎
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        for (int i = 0; i < 1000000000; i++) {
            testScriptEval(engine);
        }
    }

    private static void testScriptEval(ScriptEngine engine) {
        try {
            // 执行简单的 JavaScript 代码
            engine.eval("var x = 10; var y = 20; print('Sum: ' + (x + y));");

            // 执行一个函数
            engine.eval("function greet(name) { return 'Hello, ' + name + '!'; }");
            Object result = engine.eval("greet('Java')");
            System.out.println(result);

            // 执行多行脚本
            String script = "var array = [1, 2, 3, 4, 5];\n" +
                    "                    var sum = array.reduce(function(a, b) {\n" +
                    "                        return a + b;\n" +
                    "                    }, 0);\n" +
                    "                    print('Array sum: ' + sum);";
            engine.eval(script);

        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    private static void testScript() {
        // 创建脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        // 获取 JavaScript 引擎
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            // 执行简单的 JavaScript 代码
            engine.eval("var x = 10; var y = 20; print('Sum: ' + (x + y));");

            // 执行一个函数
            engine.eval("function greet(name) { return 'Hello, ' + name + '!'; }");
            Object result = engine.eval("greet('Java')");
            System.out.println(result);

            // 执行多行脚本
            String script = "var array = [1, 2, 3, 4, 5];\n" +
                    "                    var sum = array.reduce(function(a, b) {\n" +
                    "                        return a + b;\n" +
                    "                    }, 0);\n" +
                    "                    print('Array sum: ' + sum);";
            engine.eval(script);

        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}

