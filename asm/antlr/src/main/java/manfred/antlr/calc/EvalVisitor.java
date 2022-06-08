package manfred.antlr.calc;

public class EvalVisitor extends ExprBaseVisitor<Integer> {
    @Override
    public Integer visitProg(ExprParser.ProgContext ctx) {
        Integer result = super.visitProg(ctx);
        System.out.println("visit prog: " + result);
        return result;
    }

    @Override
    public Integer visitStat(ExprParser.StatContext ctx) {
        Integer result = super.visitStat(ctx);
        System.out.println("visit stat: " + result);
        return result;
    }

    @Override
    public Integer visitExpr(ExprParser.ExprContext ctx) {
        Integer result = super.visitExpr(ctx);
        System.out.println("visit expr: " + result);
        return result;
    }
}