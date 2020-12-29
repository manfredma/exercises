package web.client;

import domain.Employee;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class EmployeeWebClient {


    public static void main(String[] args) {
        WebClient client = WebClient.create("http://localhost:8080");

        Mono<Employee> employeeMono = client.get()
                .uri("/employees/{id}", "1")
                .retrieve()
                .bodyToMono(Employee.class);

        employeeMono.subscribe(System.out::println);


        Flux<Employee> employeeFlux = client.get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class);

        employeeFlux.subscribe(System.out::println);

        try {
            System.out.println("结束：" + System.in.read());
        } catch (IOException e) {
            // do nothing
        }

    }
}