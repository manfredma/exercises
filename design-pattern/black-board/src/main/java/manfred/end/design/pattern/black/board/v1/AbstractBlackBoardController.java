package manfred.end.design.pattern.black.board.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractBlackBoardController implements BlackBoardController {

    protected List<KnowledgeSource> ksList = new ArrayList<KnowledgeSource>();

    @Override
    public void update(Observable bb, Object bbo) {

        ExecutorService exsvc = Executors.newFixedThreadPool(1);

        if (((BlackBoardObject) bbo).isReady())
            execOutcome((BlackBoardObject) bbo);
        else {
            for (KnowledgeSource ks : ksList) {
                if (ks.canHandle((BlackBoardObject) bbo, (AbstractBlackBoard) bb)) {
                    enrollKnowledgeSource(ks, exsvc);
                    break;
                }
            }
        }

        exsvc.shutdown();
    }

    @Override
    public void setKnowledgeSourceList(List<KnowledgeSource> ksList) {
        this.ksList = ksList;
    }

    @Override
    public void enrollKnowledgeSource(KnowledgeSource ks, ExecutorService exsvc) {
        exsvc.execute(ks);
    }

}