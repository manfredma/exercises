package function.server;

import domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.EmployeeRepository;

@Component
public class EmployeeHandler {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Mono<ServerResponse> getAllEmployee(ServerRequest serverRequest) {
        Flux<Employee> allUser = employeeRepository.findAllEmployees();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(allUser, Employee.class);
    }

    public Mono<ServerResponse> getEmployeeById(ServerRequest serverRequest) {
        String uid = serverRequest.pathVariable("id");
        Mono<Employee> employee = employeeRepository.findEmployeeById(uid);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(employee, Employee.class);
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest) {
        Mono<Employee> employeeMono = serverRequest.bodyToMono(Employee.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeMono.flatMap(a -> employeeRepository.updateEmployee(a)), Employee.class);
    }
}