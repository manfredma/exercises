package manfred.end.shard.mybatis.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
@NonTransaction
public class DynamicDataSourceAspect {
}
