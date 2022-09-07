package manfred.end.hystrix;

class RemoteServiceTestSimulator {

    private long wait;

    RemoteServiceTestSimulator(long wait) {
        this.wait = wait;
    }

    String execute() throws InterruptedException {
        Thread.sleep(wait);
        return "Success";
    }
}