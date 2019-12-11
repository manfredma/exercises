package manfred.multi.task;

import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.Executor;

/**
 * @author manfred
 * @since 2019-12-11 上午9:48
 */
@Builder
@Getter
public class MultiTaskContext {
    /**
     * 是否以提交顺序返回
     */
    private boolean completeSubmitOrder = false;

    /**
     * 超时时间，-1 表示不超时
     * 单位： ms（毫秒）
     */
    private long timeoutInMs = -1;

    /**
     * 并行任务使用的线程池，如果没有传入则使用
     */
    private Executor executor;

}
