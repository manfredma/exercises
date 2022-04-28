package type.object.pattern.game.to;

public class Boot {
    public static void main(String[] args) {
        Breed dragonBreed = new Breed(500, "The dragon breathes fire!");
        Breed trollBreed = new Breed(300, "The troll clubs you!");

        Monster dragon = new Monster(dragonBreed);
        Monster troll = new Monster(trollBreed);

        System.out.println(dragon.attackString());
        System.out.println(troll.attackString());
    }
}
