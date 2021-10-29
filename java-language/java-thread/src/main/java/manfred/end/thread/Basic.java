package manfred.end.thread;

public class Basic {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(
                    t.getId() + ": " + e.toString() + "(current=" + Thread.currentThread().getId() +
                            ")");
            new RuntimeException(e).printStackTrace();
        });

        throw new RuntimeException("随便抛出个异常");
    }
}
