package io.github.ishariov.method.counter.example.dto;

public class EmployeeDTO {
    private final String name;
    private final String surname;
    private final JobTitle jobTitle;

    public EmployeeDTO(String name, String surname, JobTitle jobTitle) {
        this.name = name;
        this.surname = surname;
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }
}
