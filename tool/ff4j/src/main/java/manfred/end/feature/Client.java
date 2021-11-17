package manfred.end.feature;

import javax.annotation.Resource;

public class Client {

    /**
     * 通过不同的开关可以切换真正的IssueCouponService实现类
     */
    @Resource(name = "issue.coupon.ordinary")
    private IssueCouponService issueCouponService;


}
