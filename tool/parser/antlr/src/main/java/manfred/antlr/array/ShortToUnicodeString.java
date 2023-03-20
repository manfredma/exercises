package manfred.antlr.array;

public class ShortToUnicodeString extends ArrayBaseListener {
    /**
     * Translate { to "
     */
    @Override
    public void enterInit(ArrayParser.InitContext ctx) {
        System.out.print('"');
    }

    /**
     * Translate } to "
     */
    @Override
    public void exitInit(ArrayParser.InitContext ctx) {
        System.out.print('"');
    }

    /**
     * Translate integers to 4-digit hexadecimal strings prefixed with \\u
     */
    @Override
    public void enterValue(ArrayParser.ValueContext ctx) {
        // Assumes no nested array initializers
        int value = Integer.valueOf(ctx.INT().getText());
        System.out.printf("\\u%04x", value);
    }
}