package Service;
import com.example.demo.Employee;
import com.example.demo.Exception.EmployeeAlreadyAddedException;
import com.example.demo.Exception.EmployeeNotFoundException;;
import com.example.demo.Exception.ValidateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxNumbEmpl = 10;

    @PostConstruct
    public void initEmpoloyees() {
        addEmployee( "Корнилова", "Валерия", 100_000, 1);
        addEmployee("Карпов", "Мирослав", 110_000, 1);
        addEmployee("Жданова", "Агния", 120_000, 1);
        addEmployee("Сычев", " Ярослав ", 130_000, 2);
        addEmployee("Зайцев", "Артем", 140_000, 2);
        addEmployee("Смирнова", "Алена ", 150_000, 2);
        addEmployee("Орлов","Михаил",200_000,1);
        addEmployee ("Cорокин" ,"Дмитрий", 300_000,3);
        addEmployee("Львов","Павел", 400_000,3);
        addEmployee("Журавлев","Елисей", 500_000,3);
    }


   private final Map<String, Employee> employeeMap = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        if (!validateInput(firstName,lastName)) {
            throw new ValidateException();
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник с таким ФИО в наличии");
        }
        employeeMap.put(employee.getFullName(), employee);
        return new Employee(firstName, lastName, salary, department);
    }

    public Employee deleteEmployee(String firstName, String lastName, int salary, int department) {
        if (!validateInput(firstName, lastName)) {
            throw new ValidateException();
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeMap.containsKey(employee.getFullName())) {
            employeeMap.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException("Удаляемый сотрудник " + firstName + " " + lastName + " не найден");
    }

    public Employee findEmployee(String firstName, String lastName, int salary, int department) {
        if (!validateInput(firstName, lastName)) {
            throw new ValidateException();
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Map<String, Employee> getAll() {
        return employeeMap;
    }

    private boolean validateInput (String firstName, String lastName){
     return StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName);
    }
}

