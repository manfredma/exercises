package manfred.end.shard.mybatis.aop;

import manfred.end.shard.mybatis.annotation.NoneTransaction;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
@NoneTransaction
public class DynamicDataSourceAspect {
}
