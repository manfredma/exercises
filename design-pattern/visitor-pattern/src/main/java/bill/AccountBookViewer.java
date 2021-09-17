package bill;

public abstract class AccountBookViewer {

    /**
     * 查看消费的单子
     *
     * @param bill 支出单
     */
    abstract void view(ConsumeBill bill);


    /**
     * 查看收入的单子
     *
     * @param bill 收入单
     */
    abstract void view(IncomeBill bill);

}
