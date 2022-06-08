package manfred.antlr.array;// import ANTLR's runtime libraries

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class TreeMain {
    public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input
        CharStream input = new ANTLRInputStream(System.in);

        // create a lexer that feeds off of input CharStream
        ArrayLexer lexer = new ArrayLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        ArrayParser parser = new ArrayParser(tokens);

        ParseTree tree = parser.init(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}