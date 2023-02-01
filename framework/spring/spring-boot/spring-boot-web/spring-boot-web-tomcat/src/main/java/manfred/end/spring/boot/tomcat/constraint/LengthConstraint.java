package manfred.end.spring.boot.tomcat.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//代表处理逻辑是MyConstraintValidator类
@Constraint(validatedBy = MyConstraintValidator.class)

public @interface LengthConstraint {

    String message() default "{too.long.or.too.short}";


    long min();

    long max();


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


