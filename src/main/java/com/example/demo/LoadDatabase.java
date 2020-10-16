package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private final static Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
                                   OrderRepository orderRepository) { //request a copy of the EmployeeRepository
        return args -> {
            //new employee entities
            employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
            employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));
            employeeRepository.save(new Employee("Merry", "Brandybuck", "warrior"));
            employeeRepository.save(new Employee("Pippin", "Took", "ranger"));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

            //new order entities
            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            orderRepository.save(new Order("Android", Status.CANCELLED));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}

