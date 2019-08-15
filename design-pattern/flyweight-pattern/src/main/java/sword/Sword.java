package sword;

abstract class Sword {
    private int aggressivity;
    private String speed;
    private String material;

    Sword(int aggressivity, String speed, String material){
        this.aggressivity = aggressivity;
        this.speed = speed;
        this.material = material;
    }

    public int attack(int Durability) {

        if (Durability>50)
            return this.getAggressivity();
        else if (Durability > 0 )
            return this.getAggressivity() / 2;
        return 0;
    }

    public int getAggressivity() {
        return aggressivity;
    }

    public String getSpeed() {
        return speed;
    }

    public String getMaterial() {
        return material;
    }
}