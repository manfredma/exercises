package type.object.pattern.game.oo;

public class Boot {
    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        Troll troll = new Troll();

        System.out.println(dragon.attackString());
        System.out.println(troll.attackString());
    }
}
