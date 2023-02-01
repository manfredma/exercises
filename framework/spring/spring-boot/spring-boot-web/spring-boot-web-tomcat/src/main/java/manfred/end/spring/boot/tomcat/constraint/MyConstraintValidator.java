package manfred.end.spring.boot.tomcat.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<LengthConstraint, Object> {
    private long max = 1;
    private long min = 1;

    @Override
    public void initialize(LengthConstraint constraintAnnotation) {
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) {
            return true;
        }

        return o.toString().trim().length() >= min && o.toString().trim().length() <= max;
    }
}