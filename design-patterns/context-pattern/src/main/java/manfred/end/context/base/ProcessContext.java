package manfred.end.context.base;

/**
 * 业务流程上下文抽象基类
 * 参考 JsonStreamContext 的设计思想
 *
 * 适用场景：
 * 1. 复杂的业务流程处理（订单处理、审批流程等）
 * 2. 需要追踪处理位置和状态的场景
 * 3. 嵌套的业务逻辑处理
 *
 * @author manfred
 */
public abstract class ProcessContext {

    // ========== 类型常量 ==========

    /** 根流程 */
    public static final int TYPE_ROOT = 0;

    /** 顺序流程（类似数组） */
    public static final int TYPE_SEQUENCE = 1;

    /** 并行流程（类似对象的多个字段） */
    public static final int TYPE_PARALLEL = 2;

    /** 条件分支流程 */
    public static final int TYPE_CONDITIONAL = 3;

    // ========== 状态字段 ==========

    /** 当前上下文类型 */
    protected int _type;

    /** 当前处理的步骤索引 */
    protected int _stepIndex;

    /** 嵌套深度 */
    protected int _depth;

    /** 当前步骤名称 */
    protected String _currentStepName;

    /** 当前处理的业务数据 */
    protected Object _currentValue;

    /** 流程开始时间 */
    protected long _startTime;

    // ========== 构造方法 ==========

    protected ProcessContext() {
        this._startTime = System.currentTimeMillis();
    }

    protected ProcessContext(int type, int stepIndex) {
        this._type = type;
        this._stepIndex = stepIndex;
        this._startTime = System.currentTimeMillis();
    }

    // ========== 抽象方法（子类必须实现） ==========

    /**
     * 获取父上下文
     */
    public abstract ProcessContext getParent();

    /**
     * 获取当前步骤名称
     */
    public abstract String getCurrentStepName();

    // ========== 通用方法 ==========

    /**
     * 判断是否为根流程
     */
    public final boolean isRoot() {
        return _type == TYPE_ROOT;
    }

    /**
     * 判断是否为顺序流程
     */
    public final boolean isSequence() {
        return _type == TYPE_SEQUENCE;
    }

    /**
     * 判断是否为并行流程
     */
    public final boolean isParallel() {
        return _type == TYPE_PARALLEL;
    }

    /**
     * 判断是否为条件分支流程
     */
    public final boolean isConditional() {
        return _type == TYPE_CONDITIONAL;
    }

    /**
     * 获取流程类型描述
     */
    public String getTypeDesc() {
        switch (_type) {
            case TYPE_ROOT: return "ROOT";
            case TYPE_SEQUENCE: return "SEQUENCE";
            case TYPE_PARALLEL: return "PARALLEL";
            case TYPE_CONDITIONAL: return "CONDITIONAL";
            default: return "UNKNOWN";
        }
    }

    /**
     * 获取当前步骤索引
     */
    public int getCurrentStepIndex() {
        return _stepIndex;
    }

    /**
     * 获取嵌套深度
     */
    public int getDepth() {
        return _depth;
    }

    /**
     * 获取当前处理的业务数据
     */
    public Object getCurrentValue() {
        return _currentValue;
    }

    /**
     * 设置当前处理的业务数据
     */
    public void setCurrentValue(Object value) {
        this._currentValue = value;
    }

    /**
     * 获取流程已执行时长（毫秒）
     */
    public long getElapsedTime() {
        return System.currentTimeMillis() - _startTime;
    }

    /**
     * 构建完整的流程路径（类似 JsonPath）
     * 例如：ROOT -> OrderProcess[0] -> PaymentValidation
     */
    public String getFullPath() {
        StringBuilder sb = new StringBuilder();
        _appendPath(sb);
        return sb.toString();
    }

    /**
     * 递归构建路径
     */
    private void _appendPath(StringBuilder sb) {
        ProcessContext parent = getParent();
        if (parent != null) {
            parent._appendPath(sb);
            sb.append(" -> ");
        }

        sb.append(getTypeDesc());

        if (!isRoot()) {
            String stepName = getCurrentStepName();
            if (stepName != null) {
                sb.append("[").append(stepName).append("]");
            } else {
                sb.append("[").append(_stepIndex).append("]");
            }
        }
    }

    /**
     * 获取根上下文
     */
    public ProcessContext getRootContext() {
        ProcessContext ctx = this;
        while (ctx.getParent() != null) {
            ctx = ctx.getParent();
        }
        return ctx;
    }

    /**
     * 判断是否有当前步骤名称
     */
    public boolean hasCurrentStepName() {
        return getCurrentStepName() != null;
    }

    @Override
    public String toString() {
        return String.format("%s[step=%s, index=%d, depth=%d, elapsed=%dms]",
                getTypeDesc(),
                getCurrentStepName() != null ? getCurrentStepName() : "?",
                _stepIndex,
                _depth,
                getElapsedTime());
    }
}

