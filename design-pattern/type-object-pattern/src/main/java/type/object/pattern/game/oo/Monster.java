package type.object.pattern.game.oo;

public abstract class Monster {
    private final int health;

    public Monster(int startingHealth) {
        this.health = startingHealth;
    }

    public int getHealth() {
        return health;
    }

    public abstract String attackString();
}