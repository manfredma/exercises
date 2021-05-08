package function.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@ComponentScan(basePackages = {"repository", "function", "annotated"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(EmployeeHandler employeeHandler) {

        return route(GET("/api/employee").and(accept(MediaType.APPLICATION_JSON)), employeeHandler::getAllEmployee)
                .andRoute(GET("/api/employee/{id}").and(accept(MediaType.APPLICATION_JSON)), employeeHandler::getEmployeeById)
                .andRoute(POST("/api/save").and(accept(MediaType.APPLICATION_JSON)), employeeHandler::updateEmployee);
    }
}
