package manfred.end.feature;

import org.springframework.stereotype.Component;

@Component("issue.coupon.fast")
public class FastIssueCouponService implements IssueCouponService{

    public void sendCoupon() {
        // 使用快速的算法进行发送优惠券
    }
}
