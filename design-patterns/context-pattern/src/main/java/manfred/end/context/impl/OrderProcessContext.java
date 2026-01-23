package manfred.end.context.impl;

import manfred.end.context.base.ProcessContext;

/**
 * 订单处理上下文实现
 * 参考 SmileWriteContext 的实现方式
 *
 * 特点：
 * 1. 实现对象复用，减少 GC 压力
 * 2. 维护父子关系
 * 3. 支持嵌套的子流程
 *
 * @author manfred
 */
public class OrderProcessContext extends ProcessContext {

    /** 父上下文 */
    private final OrderProcessContext _parent;

    /** 子上下文复用（性能优化） */
    private OrderProcessContext _childToRecycle;

    /** 订单ID */
    private String _orderId;

    /** 用户ID */
    private String _userId;

    // ========== 构造方法 ==========

    private OrderProcessContext(int type, OrderProcessContext parent, String stepName) {
        super(type, -1);
        this._parent = parent;
        this._currentStepName = stepName;
        this._depth = (parent == null) ? 0 : parent._depth + 1;
    }

    /**
     * 重置上下文状态（用于对象复用）
     */
    private OrderProcessContext reset(int type, String stepName) {
        this._type = type;
        this._stepIndex = -1;
        this._currentStepName = stepName;
        this._currentValue = null;
        this._startTime = System.currentTimeMillis();
        return this;
    }

    // ========== 工厂方法 ==========

    /**
     * 创建根上下文
     */
    public static OrderProcessContext createRootContext(String orderId, String userId) {
        OrderProcessContext ctx = new OrderProcessContext(TYPE_ROOT, null, "OrderRoot");
        ctx._orderId = orderId;
        ctx._userId = userId;
        return ctx;
    }

    /**
     * 创建顺序子流程上下文（带对象复用）
     */
    public OrderProcessContext createSequenceContext(String stepName) {
        OrderProcessContext child = _childToRecycle;
        if (child == null) {
            _childToRecycle = child = new OrderProcessContext(TYPE_SEQUENCE, this, stepName);
            child._orderId = this._orderId;
            child._userId = this._userId;
            return child;
        }
        child._orderId = this._orderId;
        child._userId = this._userId;
        return child.reset(TYPE_SEQUENCE, stepName);
    }

    /**
     * 创建并行子流程上下文
     */
    public OrderProcessContext createParallelContext(String stepName) {
        OrderProcessContext child = _childToRecycle;
        if (child == null) {
            _childToRecycle = child = new OrderProcessContext(TYPE_PARALLEL, this, stepName);
            child._orderId = this._orderId;
            child._userId = this._userId;
            return child;
        }
        child._orderId = this._orderId;
        child._userId = this._userId;
        return child.reset(TYPE_PARALLEL, stepName);
    }

    /**
     * 创建条件分支子流程上下文
     */
    public OrderProcessContext createConditionalContext(String stepName) {
        OrderProcessContext child = _childToRecycle;
        if (child == null) {
            _childToRecycle = child = new OrderProcessContext(TYPE_CONDITIONAL, this, stepName);
            child._orderId = this._orderId;
            child._userId = this._userId;
            return child;
        }
        child._orderId = this._orderId;
        child._userId = this._userId;
        return child.reset(TYPE_CONDITIONAL, stepName);
    }

    // ========== 实现抽象方法 ==========

    @Override
    public OrderProcessContext getParent() {
        return _parent;
    }

    @Override
    public String getCurrentStepName() {
        return _currentStepName;
    }

    // ========== 业务方法 ==========

    /**
     * 进入下一步
     */
    public void nextStep(String stepName) {
        this._stepIndex++;
        this._currentStepName = stepName;
    }

    /**
     * 完成当前步骤，返回父上下文
     */
    public OrderProcessContext completeAndGetParent() {
        // 清理当前值，避免内存泄漏
        this._currentValue = null;
        return _parent;
    }

    /**
     * 获取订单ID
     */
    public String getOrderId() {
        return _orderId;
    }

    /**
     * 获取用户ID
     */
    public String getUserId() {
        return _userId;
    }

    /**
     * 记录步骤日志
     */
    public void logStep(String message) {
        System.out.printf("[%s] %s - %s (elapsed: %dms)%n",
                getFullPath(),
                getCurrentStepName(),
                message,
                getElapsedTime());
    }
}

