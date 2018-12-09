package io.github.ishariov.method.counter.example.web.rest;

import io.github.ishariov.method.counter.example.dto.EmployeeDTO;
import io.github.ishariov.method.counter.example.dto.JobTitle;
import io.github.ishariov.method.counter.example.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{jobTitle}")
    public List<EmployeeDTO> getEmployeesByJobTitle(@PathVariable JobTitle jobTitle) {
        return employeeService.getEmployeesByJobTitle(jobTitle);
    }
}
