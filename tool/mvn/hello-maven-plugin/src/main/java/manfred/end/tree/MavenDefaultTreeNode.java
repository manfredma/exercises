package manfred.end.tree;

import javax.annotation.Nonnull;
import org.barfuin.texttree.api.DefaultNode;

public abstract class MavenDefaultTreeNode extends DefaultNode {

    protected MessageType messageType;

    public MavenDefaultTreeNode(@Nonnull MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    protected String getDecoratedPrefix() {
        return this.messageType != MessageType.__ROOT ? "│" + this.messageType + ": " : "";
    }

    protected String getPlainText() {
        return getDecoratedPrefix() + getGav();
    }

    protected abstract String getGav();

    protected abstract void acceptor(TreeNodeVisitor visitor);
}
