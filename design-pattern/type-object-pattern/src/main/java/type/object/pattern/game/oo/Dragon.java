package type.object.pattern.game.oo;

public class Dragon extends Monster {
    public Dragon() {
        super(500);
    }

    @Override
    public String attackString() {
        return "The dragon breathes fire!";
    }
}
