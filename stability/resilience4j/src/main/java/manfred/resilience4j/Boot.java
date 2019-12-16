package manfred.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;

/**
 * @author manfred
 * @since 2019-12-16 下午8:54
 */
public class Boot {
    public static void main(String[] args) {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("uniqueName");

        // 用熔断器包装函数
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");

        // 链接其它的函数
        Try<String> result = Try.of(decoratedSupplier)
                .map(value -> value + " world'");

        // 如果函数链中的所有函数均调用成功，最终结果为Success<String>
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }
}
