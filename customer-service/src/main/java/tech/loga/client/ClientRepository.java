package tech.loga.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByNameIgnoreCase(String name);
    Optional<Client> findByContactIgnoreCase(String contact);
}
