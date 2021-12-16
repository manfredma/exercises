package manfred.end.design.pattern.black.board.v1;

import java.util.Observable;

public abstract class AbstractBlackBoard extends Observable implements BlackBoard {

    @Override
    public void addBlackBoardObject(BlackBoardObject bbo) {

        setChanged();
        notifyController(bbo);
    }

    @Override
    public void notifyController(BlackBoardObject bbo) {
        notifyObservers(bbo);
    }
}