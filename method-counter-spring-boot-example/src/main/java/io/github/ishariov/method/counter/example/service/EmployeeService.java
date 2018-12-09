package io.github.ishariov.method.counter.example.service;

import io.github.ishariov.method.counter.example.dto.EmployeeDTO;
import io.github.ishariov.method.counter.example.dto.JobTitle;
import io.github.isharipov.method.counter.core.counter.Counter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final List<EmployeeDTO> employees = new ArrayList<>();

    @PostConstruct
    public void init() {
        employees.add(new EmployeeDTO("employeeName1", "employeeSurname1", JobTitle.DRIVER));
        employees.add(new EmployeeDTO("employeeName2", "employeeSurname2", JobTitle.PROGRAMMER));
        employees.add(new EmployeeDTO("employeeName3", "employeeSurname3", JobTitle.PLUMBER));
    }

    @Counter
    public List<EmployeeDTO> getEmployeesByJobTitle(JobTitle jobTitle) {
        return employees.stream().filter(employeeDTO -> employeeDTO.getJobTitle() == jobTitle).collect(Collectors.toList());
    }
}
