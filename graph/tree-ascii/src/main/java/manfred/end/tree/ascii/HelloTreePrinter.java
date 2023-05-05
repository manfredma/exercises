package manfred.end.tree.ascii;

import hu.webarticum.treeprinter.SimpleTreeNode;
import hu.webarticum.treeprinter.printer.listing.ListingTreePrinter;
import hu.webarticum.treeprinter.printer.traditional.TraditionalTreePrinter;

public class HelloTreePrinter {
    public static void main(String[] args) {
        SimpleTreeNode rootNode = new SimpleTreeNode("I'm the root!");
        rootNode.addChild(new SimpleTreeNode("I'm a child..."));
        rootNode.addChild(new SimpleTreeNode("I'm an other child..."));

        // 打印
        new ListingTreePrinter().print(rootNode);
        new TraditionalTreePrinter().print(rootNode);

    }
}
