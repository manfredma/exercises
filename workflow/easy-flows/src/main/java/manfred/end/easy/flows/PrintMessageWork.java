package manfred.end.easy.flows;

import org.jeasy.flows.work.*;

class PrintMessageWork implements Work {

    private String message;

    public PrintMessageWork(String message) {
        this.message = message;
    }

    public String getName() {
        return "print message work";
    }

    public WorkReport execute(WorkContext workContext) {
        System.out.println(message);
        return new DefaultWorkReport(WorkStatus.COMPLETED, workContext);
    }
}