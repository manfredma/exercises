package manfred.end.feature;

public class IssueCouponServiceV2 {

    private FeatureService featureService = new MccFeatureService();

    public void sendCoupon() {
        if (featureService.sendCouponUseFastAlgorithm()) {
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