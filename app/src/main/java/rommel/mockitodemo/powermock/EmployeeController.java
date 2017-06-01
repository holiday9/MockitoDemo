package rommel.mockitodemo.powermock;

/**
 * Created by yuan on 2017/6/1.
 */

public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    public int getProjectedEmployeeCount() {

        final int actualEmployeeCount = employeeService.getEmployeeCount();
        return (int) Math.ceil(actualEmployeeCount * 1.2);
    }

    public void saveEmployee(Employee employee) {

        employeeService.saveEmployee(employee);
    }
}
