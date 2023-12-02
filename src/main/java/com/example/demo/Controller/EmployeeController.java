package com.example.demo.Controller;
import com.example.demo.Employee;
import Service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, String lastName, int salary, int department) {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, String lastName, int salary, int department) {
        return employeeService.deleteEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, String lastName, int salary, int department) {
        return employeeService.findEmployee(firstName, lastName, salary, department);
    }





}
