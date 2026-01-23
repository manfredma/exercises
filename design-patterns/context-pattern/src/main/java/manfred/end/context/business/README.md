# 业务系统中的 Context 模式实战

## 概述

Context 模式在企业业务系统中有大量实际应用场景。本文档展示真实业务场景中的 Context 设计。

## 典型业务场景

### 1. 请求上下文（RequestContext）
- 追踪用户请求信息
- 传递用户身份、权限、租户信息
- 分布式追踪（TraceId）

### 2. 交易上下文（TransactionContext）
- 管理分布式事务
- 追踪交易流程
- 记录交易日志

### 3. 审批上下文（ApprovalContext）
- 多级审批流程
- 审批历史追踪
- 审批权限验证

### 4. 计算上下文（CalculationContext）
- 价格计算（优惠、折扣）
- 积分计算
- 佣金计算

### 5. 导入导出上下文（ImportExportContext）
- 批量数据处理
- 进度追踪
- 错误收集

### 6. 规则引擎上下文（RuleContext）
- 业务规则执行
- 规则链路追踪
- 规则结果缓存

### 7. 多租户上下文（TenantContext）
- 租户隔离
- 租户配置
- 租户数据过滤

### 8. 安全上下文（SecurityContext）
- 用户认证信息
- 权限验证
- 数据权限过滤

## 代码示例

详见各个场景的具体实现文件。

