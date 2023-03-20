package manfred.antlr.calc;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Calc {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0)
            inputFile = args[0];
        InputStream is = Files.newInputStream(
                Paths.get("tool/parser/antlr/src/main/java/manfred/antlr/calc/t.expr"));
        if (inputFile != null) {
            is = Files.newInputStream(Paths.get(inputFile));
        }
        CharStream input = CharStreams.fromStream(is);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        // parse
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
        System.out.println(eval.visit(tree));
    }
}
