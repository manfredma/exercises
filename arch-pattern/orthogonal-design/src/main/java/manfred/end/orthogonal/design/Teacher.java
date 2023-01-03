package manfred.end.orthogonal.design;

/**
 * @author manfred on 2022/11/18.
 */
public class Teacher implements Highly {
    private String name;

    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
