package tech.loga.company;

public interface BranchManagement {
    void addBranch(Branch branch, Long companyId);
    void editBranch(Branch branch, Long id);
    void removeBranch(Long branchId);
}
