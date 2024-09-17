package com.hotnerds.badgeroad.user.repository;

import com.hotnerds.badgeroad.user.entity.Badge;
import com.hotnerds.badgeroad.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String email);

   @EntityGraph(attributePaths = "badge")
   List<Badge> findAllBadgesByEmail(String email);
}
