package ru.portal.semusadba.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.portal.semusadba.config.security.UserRole;
import ru.portal.semusadba.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(UserRole userRole);
}
