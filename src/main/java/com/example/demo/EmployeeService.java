package com.example.demo;
import com.example.demo.Exception.EmployeeAlreadyAddedException;
import com.example.demo.Exception.EmployeeNotFoundException;
import com.example.demo.Exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    private final int maxNumbEmpl = 10;
    List<Employee> employeeList = new ArrayList<>();
@PostConstruct
    public void init() {
        employeeList.add(new Employee("Корнилова", "Валерия"));
        employeeList.add(new Employee("Карпов", "Мирослав"));
        employeeList.add(new Employee("Жданова", "Агния"));
        employeeList.add(new Employee("Сычев", " Ярослав "));
        employeeList.add(new Employee("Зайцев", "Артем"));
        employeeList.add(new Employee("Смирнова", "Алена "));
        employeeList.add(new Employee("Орлов","Михаил"));
        employeeList.add(new Employee("Cорокин" ,"Дмитрий"));
        employeeList.add(new Employee("Львов","Павел"));
        employeeList.add(new Employee("Журавлев","Елисей"));
    }

    public Employee addEmployee(String firstName, String lastName) {
        for (Employee employee : employeeList) {
            if (employee.equals(new Employee(firstName, lastName))) {
                throw new EmployeeAlreadyAddedException("Сотрудник с таким ФИО в наличии");
            }
        }
        if (maxNumbEmpl > employeeList.size()) {
            employeeList.add(new Employee(firstName, lastName));
        }
        else {throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
        return new Employee(firstName,lastName);
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        boolean isRemoved = employeeList.remove(new Employee(firstName, lastName));
        if (!isRemoved) {
            throw new EmployeeNotFoundException("Удаляемый сотрудник " + firstName + " " + lastName + " не найден");
        }
        return new  Employee(firstName,lastName);
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employeeList) {
            if (employee.equals(new Employee(firstName, lastName))) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    public List<Employee> getEmployeeList() {
    return employeeList;
    }
}

