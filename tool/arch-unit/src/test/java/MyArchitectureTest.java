import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class MyArchitectureTest {
    @Test
    public void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.myapp");

        ArchRule rule = classes().that().areAnnotatedWith(Service.class).or().haveNameMatching(".*Service")
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