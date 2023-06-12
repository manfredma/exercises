package manfred.end.shard.mybatis.annotation;

import manfred.end.shard.mybatis.policy.ArchiveNamePolicy;
import manfred.end.shard.mybatis.policy.DefaultArchiveNamePolicy;
import manfred.end.shard.mybatis.policy.DefaultShardPolicy;
import manfred.end.shard.mybatis.policy.ShardPolicy;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ShardConfig {

    int count() default 1;

    Class<? extends ShardPolicy> shardPolicy() default DefaultShardPolicy.class;

    Class<? extends ArchiveNamePolicy> archiveNamePolicy() default DefaultArchiveNamePolicy.class;

    boolean hasShadow() default true;
}
