package se.ifmo.labs.s311723.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.labs.s311723.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
