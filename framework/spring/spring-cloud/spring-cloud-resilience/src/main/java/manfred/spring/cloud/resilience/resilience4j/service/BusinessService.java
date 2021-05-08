package manfred.spring.cloud.resilience.resilience4j.service;


import io.vavr.control.Try;

public interface BusinessService {
    String failure();

    String success();

    String ignore();

    Try<String> methodWithRecovery();
}
