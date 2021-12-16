package manfred.end.design.pattern.black.board.v1;

public abstract class AbstractBlackBoardObject implements BlackBoardObject {

    protected boolean isReady;

    @Override
    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

}