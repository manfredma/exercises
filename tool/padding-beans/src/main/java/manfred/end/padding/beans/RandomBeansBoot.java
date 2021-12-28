package manfred.end.padding.beans;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import manfred.end.padding.beans.model.Person;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

public class RandomBeansBoot {
    public static void main(String[] args) {
        EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom();
        // hello world
        System.out.println("--- hello world ---");
        Person person = enhancedRandom.nextObject(Person.class);
        Stream<Person> persons = enhancedRandom.objects(Person.class, 2);
        System.out.println(person);
        persons.forEach(System.out::println);

        // 自定义规则
        System.out.println("--- 自定义规则 ---");
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(3)
                .charset(StandardCharsets.UTF_8)
                .timeRange(LocalTime.of(5, 0), LocalTime.of(6,0))
                .dateRange(LocalDate.now(), LocalDate.now().plusDays(1))
                .stringLengthRange(5, 50)
                .collectionSizeRange(1, 10)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .build();

        person = enhancedRandom.nextObject(Person.class);
        persons = enhancedRandom.objects(Person.class, 2);
        System.out.println(person);
        persons.forEach(System.out::println);

        // 精确控制到字段
        System.out.println("--- 控制到字段 ---");
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(String.class, (Randomizer<String>) () -> "foo")
                .excludeField(field -> field.getName().equals("age"))
                .build();
        person = enhancedRandom.nextObject(Person.class);
        persons = enhancedRandom.objects(Person.class, 2);
        System.out.println(person);
        persons.forEach(System.out::println);

    }

}
