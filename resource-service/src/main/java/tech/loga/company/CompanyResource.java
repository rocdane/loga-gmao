package tech.loga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.loga.asset.Asset;
import tech.loga.employee.Employee;

import java.util.List;

@Service
public class CompanyResource implements CompanyManagement, BranchManagement, DepartmentManagement {

    private final CompanyRepository companyRepository;
    private final BranchRepository branchRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CompanyResource(CompanyRepository companyRepository,
                           BranchRepository branchRepository,
                           DepartmentRepository departmentRepository) {
        this.companyRepository = companyRepository;
        this.branchRepository = branchRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = null;
        if(companyRepository.findById(id).isPresent()){
            return companyRepository.findById(id).get();
        }
        throw new CompanyNotFoundException(String.format("Company with name : %d not found",id));
    }

    @Override
    public Company getCompanyByName(String name) {
        if(companyRepository.findByName(name).isPresent()){
            return companyRepository.findByName(name).get();
        }
        throw new CompanyNotFoundException(String.format("Company with name : %s not found",name));
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public void editCompany(Company company, Long id) {
        companyRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setName(company.getName());
                    up.setPhone(company.getPhone());
                    up.setEmail(company.getEmail());
                    up.setLegal(company.getLegal());
                    up.setLocation(company.getLocation());
                    up.setBranches(company.getBranches());
                    companyRepository.saveAndFlush(up);
                },() -> {
                    throw new CompanyRegistrationFailedException(String.format("Company with id : %d registration failed",id));
                });

    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository
                .findById(id)
                .ifPresentOrElse(companyRepository::delete,() -> {
                    throw new CompanyRegistrationFailedException(String.format("Company with id : %d deletion failed",id));
                });
    }

    @Override
    public void addBranch(Branch branch, Long companyId) {
        companyRepository
                .findById(companyId)
                .ifPresentOrElse(company -> {
                    company.getBranches().add(branch);
                    companyRepository.saveAndFlush(company);
                },() -> {
                    throw new CompanyRegistrationFailedException(String.format("Adding branch to Company with id : %d failed",companyId));
                });
    }

    @Override
    public void editBranch(Branch branch, Long id) {
        branchRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setName(branch.getName());
                    up.setIfu(branch.getIfu());
                    up.setEmail(branch.getEmail());
                    up.setPhone(branch.getPhone());
                    up.setAddress(branch.getAddress());
                    branchRepository.saveAndFlush(up);
                },() -> {
                    throw new CompanyRegistrationFailedException(String.format("Update branch with id : %d failed",id));
                });
    }

    @Override
    public void removeBranch(Long id) {
        branchRepository
                .findById(id)
                .ifPresentOrElse(branchRepository::delete,() -> {
                    throw new CompanyRegistrationFailedException(String.format("Update branch with id : %d failed",id));
                });
    }

    @Override
    public void addDepartment(Department department, Long branchId) {
        branchRepository
                .findById(branchId)
                .ifPresentOrElse(branch -> {
                    branch.getDepartments().add(department);
                    branchRepository.saveAndFlush(branch);
                },() -> {
                    throw new CompanyRegistrationFailedException(String.format("Adding department to branch with id:: %d failed",branchId));
                });
    }

    @Override
    public void editDepartment(Department department, Long id) {
        departmentRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setName(department.getName());
                    up.setReference(department.getReference());
                    departmentRepository.saveAndFlush(up);
                },() -> {
                    throw new CompanyRegistrationFailedException(String.format("Update department with id:: %d failed",id));
                });
    }

    @Override
    public void removeDepartment(Long departmentId) {
        departmentRepository
                .findById(departmentId)
                .ifPresentOrElse(departmentRepository::delete,() -> {
                    throw new CompanyRegistrationFailedException(String.format("Remove department with id:: %d failed",departmentId));
                });
    }

    @Override
    public void addEmployee(Employee employee, Long departmentId) {
        departmentRepository
                .findById(departmentId)
                .ifPresentOrElse(department -> {
                    department.getEmployees().add(employee);
                    departmentRepository.saveAndFlush(department);
                },() -> {
                    throw new CompanyRegistrationFailedException(String.format("Adding employee to department with id:: %d failed",departmentId));
                });
    }

    @Override
    public void addAsset(Asset asset, Long departmentId) {
        departmentRepository
                .findById(departmentId)
                .ifPresentOrElse(department -> {
                    department.getAssets().add(asset);
                    departmentRepository.saveAndFlush(department);
                },() -> {
                    throw new CompanyRegistrationFailedException(String.format("Adding asset to department with id:: %d failed",departmentId));
                });
    }
}
