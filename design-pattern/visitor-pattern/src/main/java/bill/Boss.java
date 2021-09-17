package bill;

public class Boss extends AccountBookViewer {

	private double totalIncome;

    private double totalConsume;
	
	@Override
	void view(ConsumeBill bill) {
		totalConsume += bill.getAmount();
	}

	@Override
	void view(IncomeBill bill) {
		totalIncome += bill.getAmount();
	}
	
	public double getTotalIncome() {
        System.out.println("老板查看一共收入多少，数目是：" + totalIncome);
        return totalIncome;
    }

    public double getTotalConsume() {
        System.out.println("老板查看一共花费多少，数目是：" + totalConsume);
        return totalConsume;
    }

          
}