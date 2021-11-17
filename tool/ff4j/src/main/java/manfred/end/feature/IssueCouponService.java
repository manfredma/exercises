package manfred.end.feature;

import org.ff4j.aop.Flip;

public interface IssueCouponService {
    @Flip(name = "send-coupon-alg", alterBean = "issue.coupon.fast")
    void sendCoupon();
}
