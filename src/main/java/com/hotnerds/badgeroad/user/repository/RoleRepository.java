package com.hotnerds.badgeroad.user.repository;

import com.hotnerds.badgeroad.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
