package manfred.end.design.pattern.black.board.v1;

public interface KnowledgeSource extends Runnable {

    boolean canHandle(BlackBoardObject bbo, BlackBoard bb);

    BlackBoardObject process(BlackBoardObject bbo) throws Exception;

    void updateBlackBoardObject(BlackBoardObject bbo);

}