package manfred.end.feature;

public class IssueCouponServiceV1 {

    private Features features = new Features();

    public void sendCoupon() {
        if (features.isEnable("fast-algorithm")) {
            fastSendCoupon();
        } else {
            ordinarySendCoupon();
        }
    }

    private void fastSendCoupon() {
        // 使用快速的算法进行发送优惠券
    }

    private void ordinarySendCoupon() {
        // 使用之前普通算法进行发送优惠券
    }
}