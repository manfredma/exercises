package type.object.pattern.game.to;

public class Monster {
    private Breed breed;

    public Monster(Breed breed) {
        this.breed = breed;
    }

    public int getHealth() {
        return breed.getHealth();
    }

    public String attackString() {
        return breed.getAttackString();
    }
}

