package manfred.end.arch.unit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.stereotype.Service;

public class MyArchitectureTest {

    @Test
    public void xxx() {
        JavaClasses importedClasses = new ClassFileImporter()
                .importPackages("manfred.end.arch.unit");
        importedClasses.forEach(a -> {
            // System.out.println(a.toString());
            Set<JavaField> allFields = a.getAllFields();
            for (JavaField field : allFields) {
                // System.out.println(field);
                boolean accessByToString = false;
                for (JavaFieldAccess javaFieldAccess : field.getAccessesToSelf()) {
                    if (javaFieldAccess.getOrigin().getName().equals("toString")) {
                        accessByToString = true;
                    }
                }
                if (!accessByToString) {
                    System.out.println(field + "not access by toString");
                }
            }
        });
    }

    @Test
    @Ignore // 暂时忽略
    public void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.myapp");

        ArchRule rule = classes().that().areAnnotatedWith(Service.class).or().haveNameMatching(
                ".*Service")
                .should().resideInAPackage("..Service..")
                .orShould().beAnnotatedWith(Service.class);
        rule.check(importedClasses);
    }

    @Test
    public void testClassFileImporter() {
        JavaClasses javaClasses = new ClassFileImporter().importPackages("java");
        JavaClass clazz = javaClasses.get(Object.class);
        System.out.println(clazz.getSimpleName());

    }
}