package manfred.end.context.business;

import java.util.HashMap;
import java.util.Map;

/**
 * 场景1：请求上下文（最常见的业务场景）
 *
 * 应用场景：
 * 1. Web 请求处理 - 传递用户信息、租户信息
 * 2. 分布式追踪 - TraceId、SpanId
 * 3. 日志记录 - 自动记录用户、租户、请求ID
 * 4. 权限验证 - 传递用户权限信息
 * 5. 多租户系统 - 租户隔离
 *
 * 实际案例：
 * - 美团外卖：用户下单时，RequestContext 携带用户ID、城市ID、设备信息
 * - 阿里云：API 调用时，RequestContext 携带租户ID、AccessKey、Region
 * - 企业 ERP：操作时，RequestContext 携带员工ID、部门ID、权限列表
 *
 * @author manfred
 */
public class RequestContext {

    // 使用 ThreadLocal 确保线程安全
    private static final ThreadLocal<RequestContext> CONTEXT_HOLDER = new ThreadLocal<>();

    // ========== 核心信息 ==========

    /** 请求ID（用于追踪） */
    private String requestId;

    /** 追踪ID（分布式追踪） */
    private String traceId;

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    private String username;

    /** 租户ID（多租户系统） */
    private String tenantId;

    /** 租户名称 */
    private String tenantName;

    /** 部门ID */
    private Long departmentId;

    /** 角色列表 */
    private String[] roles;

    /** 权限列表 */
    private String[] permissions;

    /** 客户端IP */
    private String clientIp;

    /** 设备类型 */
    private String deviceType;

    /** 请求时间 */
    private long requestTime;

    /** 扩展属性（灵活扩展） */
    private Map<String, Object> attributes = new HashMap<>();

    // ========== 静态方法 ==========

    /**
     * 设置当前请求上下文
     */
    public static void set(RequestContext context) {
        CONTEXT_HOLDER.set(context);
    }

    /**
     * 获取当前请求上下文
     */
    public static RequestContext get() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除当前请求上下文（请求结束时必须调用）
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * 获取当前用户ID
     */
    public static Long getCurrentUserId() {
        RequestContext ctx = get();
        return ctx != null ? ctx.getUserId() : null;
    }

    /**
     * 获取当前租户ID
     */
    public static String getCurrentTenantId() {
        RequestContext ctx = get();
        return ctx != null ? ctx.getTenantId() : null;
    }

    /**
     * 获取当前 TraceId
     */
    public static String getCurrentTraceId() {
        RequestContext ctx = get();
        return ctx != null ? ctx.getTraceId() : null;
    }

    // ========== 业务方法 ==========

    /**
     * 检查是否有指定权限
     */
    public boolean hasPermission(String permission) {
        if (permissions == null) {
            return false;
        }
        for (String p : permissions) {
            if (p.equals(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查是否有指定角色
     */
    public boolean hasRole(String role) {
        if (roles == null) {
            return false;
        }
        for (String r : roles) {
            if (r.equals(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置扩展属性
     */
    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * 获取扩展属性
     */
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key) {
        return (T) attributes.get(key);
    }

    /**
     * 获取请求耗时
     */
    public long getElapsedTime() {
        return System.currentTimeMillis() - requestTime;
    }

    // ========== Builder 模式 ==========

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RequestContext context = new RequestContext();

        public Builder requestId(String requestId) {
            context.requestId = requestId;
            return this;
        }

        public Builder traceId(String traceId) {
            context.traceId = traceId;
            return this;
        }

        public Builder userId(Long userId) {
            context.userId = userId;
            return this;
        }

        public Builder username(String username) {
            context.username = username;
            return this;
        }

        public Builder tenantId(String tenantId) {
            context.tenantId = tenantId;
            return this;
        }

        public Builder tenantName(String tenantName) {
            context.tenantName = tenantName;
            return this;
        }

        public Builder departmentId(Long departmentId) {
            context.departmentId = departmentId;
            return this;
        }

        public Builder roles(String... roles) {
            context.roles = roles;
            return this;
        }

        public Builder permissions(String... permissions) {
            context.permissions = permissions;
            return this;
        }

        public Builder clientIp(String clientIp) {
            context.clientIp = clientIp;
            return this;
        }

        public Builder deviceType(String deviceType) {
            context.deviceType = deviceType;
            return this;
        }

        public RequestContext build() {
            context.requestTime = System.currentTimeMillis();
            return context;
        }
    }

    // ========== Getters ==========

    public String getRequestId() {
        return requestId;
    }

    public String getTraceId() {
        return traceId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String[] getRoles() {
        return roles;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public long getRequestTime() {
        return requestTime;
    }

    @Override
    public String toString() {
        return String.format("RequestContext[requestId=%s, traceId=%s, userId=%d, username=%s, tenantId=%s, elapsed=%dms]",
                requestId, traceId, userId, username, tenantId, getElapsedTime());
    }
}

