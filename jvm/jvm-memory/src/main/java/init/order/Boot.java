package init.order;

public class Boot {
    final int i;
    int j;
    public Boot() {
        escapeI();
        this.j = 4;
        this.i = 3;
    }

    public Boot(int i, int j) {
        this.i = i;
        this.j = j;
    }

    private void escapeI() {
        System.out.println(this.i);
    }

    public static void main(String[] args) {
        Boot b = new Boot();
        System.out.println(b.i);

        Boot b2 = new Boot(10, 10);
        System.out.println(b2.i);
    }
}
