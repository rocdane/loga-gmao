package tech.loga.company;

import tech.loga.asset.Asset;
import tech.loga.employee.Employee;

import java.util.List;

public interface CompanyManagement {
    Company createCompany(Company company);
    Company getCompanyById(Long id);
    Company getCompanyByName(String name);
    List<Company> getAllCompany();
    void editCompany(Company company, Long id);
    void deleteCompany(Long id);
}
