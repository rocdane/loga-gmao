package tech.loga.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.loga.company.CompanyManagement;

@CrossOrigin
@RestController
@RequestMapping("/resource-service")
public class EmployeeController {

    private final CompanyManagement companyManagement;
    private final EmployeeManagement employeeManagement;

    @Autowired
    public EmployeeController(CompanyManagement companyService,
                              EmployeeManagement employeeManagement) {
        this.companyManagement = companyService;
        this.employeeManagement = employeeManagement;
    }
}
