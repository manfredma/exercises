package manfred.end.design.pattern.black.board.v1;

public abstract class AbstractKnowledgeSource implements KnowledgeSource {

    protected BlackBoardObject bbo;

    protected BlackBoard bb;

    @Override
    public void run() {
        try {
            updateBlackBoardObject(process(bbo));
        } catch (Exception ex) {
            //TODO: log the exception
        }
    }

    @Override
    public void updateBlackBoardObject(BlackBoardObject bbo) {
        bb.addBlackBoardObject(bbo);
    }
}