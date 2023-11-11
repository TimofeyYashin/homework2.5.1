package com.example.demo;
import com.example.demo.Exception.EmployeeAlreadyAddedException;
import com.example.demo.Exception.EmployeeNotFoundException;;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeService {
    private final int maxNumbEmpl = 10;
    Map<String, Employee> employeeMap = new HashMap<>();

    @PostConstruct

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник с таким ФИО в наличии");
        }
        employeeMap.put(employee.getFullName(), employee);
        return new Employee(firstName, lastName);
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeMap.containsKey(employee.getFullName())) {
            employeeMap.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException("Удаляемый сотрудник " + firstName + " " + lastName + " не найден");
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    public Map<String, Employee> getEmployeeMap() {
        return employeeMap;
    }
}

