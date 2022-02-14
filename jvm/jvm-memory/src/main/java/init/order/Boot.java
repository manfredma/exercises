package init.order;

public class Boot {
    final int i;
    int j;
    public Boot() {
        escapeI();
        this.j = 4;
        this.i = 3;
    }

    private void escapeI() {
        System.out.println(this.i);
    }

    public static void main(String[] args) {
        Boot b = new Boot();
        System.out.println(b.i);
    }
}
