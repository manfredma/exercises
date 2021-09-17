package bill;

public class ConsumeBill implements Bill {

    private double amount;

    private String item;

    public ConsumeBill(double amount, String item) {
        super();
        this.amount = amount;
        this.item = item;
    }


    @Override
    public void accept(AccountBookViewer v) {
        v.view(this);
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }


    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

}