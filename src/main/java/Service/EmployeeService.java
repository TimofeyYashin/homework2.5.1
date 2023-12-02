package Service;

import com.example.demo.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int salary, int department);
    Employee deleteEmployee(String firstName, String lastName,int salary, int department);
    Employee findEmployee(String firstName, String lastName, int salary, int department);

    Map<String,Employee> getAll();
}
