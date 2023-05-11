package manfred.end.tree.ascii;

import org.barfuin.texttree.api.DefaultNode;
import org.barfuin.texttree.api.TextTree;
import org.barfuin.texttree.api.TreeOptions;
import org.barfuin.texttree.api.style.TreeStyle;

public class HelloTextTree {
    public static void main(String[] args) {
        DefaultNode tree = new DefaultNode("root");
        tree.addChild(new DefaultNode("child1"));
        tree.addChild(new DefaultNode("child2"));
        tree.addChild(new DefaultNode("child2"));


        String rendered = TextTree.newInstance().render(tree);
        // or whatever else you want to do with the rendered tree
        System.out.println(rendered);


        TreeOptions options = new TreeOptions();
        options.setStyle(new TreeStyle("├─── ", "│    ", "╰─── ", "<", ">"));

        String renderedWithStyle = TextTree.newInstance(options).render(tree);
        System.out.println(renderedWithStyle);


    }
}
