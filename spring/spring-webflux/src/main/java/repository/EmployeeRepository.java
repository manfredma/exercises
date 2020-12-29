package repository;

import domain.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeRepository() {
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setId(String.valueOf(i));
            employee.setName("Lily-" + i);
            employeeList.add(employee);

        }
    }


    public Mono<Employee> findEmployeeById(String id) {
        Optional<Employee> found = employeeList.stream().filter(a -> a.getId().equals(id)).findAny();
        return Mono.delay(Duration.ofSeconds(10L)).thenReturn(found.orElseGet(() -> new Employee(id, "name-" + id)));
    }

    public Flux<Employee> findAllEmployees() {
        return Flux.fromIterable(employeeList);
    }

    @NotNull
    public Mono<Employee> updateEmployee(Employee employee) {
        Optional<Employee> found = employeeList.stream().filter(a -> a.getId().equals(employee.getId())).findAny();
        found.ifPresent(value -> employeeList.remove(value));
        employeeList.add(employee);
        return Mono.just(employee);
    }
}
