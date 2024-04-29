package tech.loga.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeResource implements EmployeeManagement {

    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public EmployeeResource(EmployeeRepository employeeRepository,
                            ContractRepository contractRepository) {
        this.employeeRepository = employeeRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        if(employeeRepository.findById(id).isPresent()) {
            return employeeRepository.findById(id).get();
        }
        throw new EmployeeNotFoundException(String.format("Employee with id:%d not found",id));
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        if(employeeRepository.findByEmail(email).isPresent()){
            return employeeRepository.findByEmail(email).get();
        }
        throw new EmployeeNotFoundException(String.format("Employee with email:%s not found",email));
    }

    @Override
    @Transactional
    public void editEmployee(Employee employee, Long id) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setFirstName(employee.getFirstName());
                    up.setLastName(employee.getLastName());
                    up.setBirthdate(employee.getBirthdate());
                    up.setAddress(employee.getAddress());
                    up.setEmail(employee.getEmail());
                    up.setPhone(employee.getPhone());
                    employeeRepository.saveAndFlush(up);
                },() -> {
                    throw new EmployeeRegistrationFailedException(String.format("Update employee with id:%d failed",id));
                });
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(employeeRepository::delete,() -> {
                    throw new EmployeeRegistrationFailedException(String.format("Delete employee with id:%d failed",id));
                });
    }
}
