package manfred.end.design.pattern.black.board.v1;

public interface BlackBoard {

    void addBlackBoardObject(BlackBoardObject bbo);

    void notifyController(BlackBoardObject bbo);
}
