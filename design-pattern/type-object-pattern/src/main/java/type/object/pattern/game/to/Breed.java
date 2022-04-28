package type.object.pattern.game.to;

public class Breed {

    private int health;

    private String attackString;

    public Breed(int health, String attackString) {
        this.health = health;
        this.attackString = attackString;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getAttackString() {
        return attackString;
    }

    public void setAttackString(String attackString) {
        this.attackString = attackString;
    }
}