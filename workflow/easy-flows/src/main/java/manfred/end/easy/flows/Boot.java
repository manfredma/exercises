package manfred.end.easy.flows;

import org.jeasy.flows.engine.WorkFlowEngine;
import org.jeasy.flows.work.WorkContext;
import org.jeasy.flows.work.WorkReport;
import org.jeasy.flows.work.WorkReportPredicate;
import org.jeasy.flows.workflow.WorkFlow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.jeasy.flows.engine.WorkFlowEngineBuilder.aNewWorkFlowEngine;
import static org.jeasy.flows.workflow.ConditionalFlow.Builder.aNewConditionalFlow;
import static org.jeasy.flows.workflow.ParallelFlow.Builder.aNewParallelFlow;
import static org.jeasy.flows.workflow.RepeatFlow.Builder.aNewRepeatFlow;
import static org.jeasy.flows.workflow.SequentialFlow.Builder.aNewSequentialFlow;

public class Boot {
    public static void main(String[] args) {
        PrintMessageWork work1 = new PrintMessageWork("foo");
        PrintMessageWork work2 = new PrintMessageWork("hello");
        PrintMessageWork work3 = new PrintMessageWork("world");
        PrintMessageWork work4 = new PrintMessageWork("ok");
        PrintMessageWork work5 = new PrintMessageWork("nok");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        WorkFlow workflow = aNewSequentialFlow() // flow 4
                .execute(aNewRepeatFlow() // flow 1
                        .named("print foo 3 times")
                        .repeat(work1)
                        .times(3)
                        .build())
                .then(aNewConditionalFlow() // flow 3
                        .execute(aNewParallelFlow() // flow 2
                                .named("print 'hello' and 'world' in parallel")
                                .execute(work2, work3)
                                .with(executorService)
                                .build())
                        .when(WorkReportPredicate.COMPLETED)
                        .then(work4)
                        .otherwise(work5)
                        .build())
                .build();

        WorkFlowEngine workFlowEngine = aNewWorkFlowEngine().build();
        WorkContext workContext = new WorkContext();
        WorkReport workReport = workFlowEngine.run(workflow, workContext);
        System.out.println(workReport);
        executorService.shutdown();
    }
}
