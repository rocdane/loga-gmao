package tech.loga.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BranchRepository extends JpaRepository<Branch,Long> {
    Branch findByName(String name);
}
