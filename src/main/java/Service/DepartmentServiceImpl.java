package Service;
import com.example.demo.Employee;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
       return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .max(Comparator.comparingInt(Employee::getSalary))
               .orElse(null);

    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getAll().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
