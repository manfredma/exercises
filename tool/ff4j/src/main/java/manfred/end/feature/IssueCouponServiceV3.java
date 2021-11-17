package manfred.end.feature;

import javax.annotation.Resource;

public class IssueCouponServiceV3 {

    @Resource
    private FeatureService featureService;

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
