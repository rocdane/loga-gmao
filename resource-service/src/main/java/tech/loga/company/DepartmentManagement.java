package tech.loga.company;

import tech.loga.asset.Asset;
import tech.loga.employee.Employee;

public interface DepartmentManagement {
    void addDepartment(Department department, Long branchId);
    void editDepartment(Department department, Long id);
    void removeDepartment(Long departmentId);
    void addEmployee(Employee employee, Long departmentId);
    void addAsset(Asset asset, Long assetId);
}
