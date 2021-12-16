package manfred.end.design.pattern.black.board.v1;

import java.util.List;
import java.util.Observer;
import java.util.concurrent.ExecutorService;

public interface BlackBoardController extends Observer {

    void setKnowledgeSourceList(List<KnowledgeSource> ksList);

    void enrollKnowledgeSource(KnowledgeSource ks, ExecutorService exsvc);

    void execOutcome(BlackBoardObject bbo);

}