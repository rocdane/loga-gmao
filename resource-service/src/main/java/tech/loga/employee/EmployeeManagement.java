package tech.loga.employee;

import java.util.List;

public interface EmployeeManagement {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id) throws EmployeeNotFoundException;
    Employee getEmployeeByEmail(String email) throws EmployeeNotFoundException;
    List<Employee> getAllEmployee();
    void editEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);
}
