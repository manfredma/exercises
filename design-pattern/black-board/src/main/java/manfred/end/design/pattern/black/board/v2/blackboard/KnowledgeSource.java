package manfred.end.design.pattern.black.board.v2.blackboard;

/**
 * The KnowledgeSource interface defines the methods that are available to
 * knowledge source objects. These objects interact with the Blackboard, and
 * may also let the Blackboard Controller know if further actions need to be
 * taken after they supply their knowledge contribution.
 */
public interface KnowledgeSource {

    void updateBlackboard();

    void activateController();
}