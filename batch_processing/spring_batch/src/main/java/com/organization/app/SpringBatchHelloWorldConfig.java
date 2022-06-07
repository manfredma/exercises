package com.organization.app;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class SpringBatchHelloWorldConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(2)
                .reader(employeeItemReader())
                .processor(employeeItemProcessor())
                .writer(employeeItemWriter())
                .build();
    }

    @Bean
    public Job listEmployeesJob(Step step1) throws Exception {
        return jobBuilderFactory.get("listEmployeesJob")
                .start(step1)
                .build();
    }

    @Bean
    ItemReader<Employee> employeeItemReader() {

        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("employees.csv"));

        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("firstName", "lastName", "age", "salary");

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(defaultLineMapper);

        return reader;
    }

    @Bean
    ItemProcessor<Employee, Employee> employeeItemProcessor() {
        return employee -> {
            employee.setFirstName(employee.getFirstName().toUpperCase());
            employee.setLastName(employee.getLastName().toUpperCase());
            return employee;
        };
    }

    @Bean
    ItemWriter<Employee> employeeItemWriter() {
        return employeesList -> {
            for (Employee employee : employeesList) {
                System.out.println("Name: "
                        + employee.getFirstName() + " "
                        + employee.getLastName() + "; "
                        + "Age: " + employee.getAge() + "; "
                        + "Salary: " + employee.getSalary());
            }
        };
    }
}