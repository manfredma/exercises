package manfred.end.feature;

import org.springframework.stereotype.Component;

@Component("issue.coupon.ordinary")
public class OrdinaryIssueCouponService implements IssueCouponService {

    public void sendCoupon() {
        // 使用之前普通算法进行发送优惠券
    }
}
