package com.hotnerds.badgeroad.store.repository;

import com.hotnerds.badgeroad.store.entity.Store;
import com.hotnerds.badgeroad.user.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
