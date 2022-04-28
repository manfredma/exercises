package type.object.pattern.game.oo;

public class Troll extends Monster {
    public Troll() {
        super(300);
    }

    @Override
    public String attackString() {
        return "The troll clubs you!";
    }
}
