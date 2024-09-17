package com.hotnerds.badgeroad.store.service;

import com.hotnerds.badgeroad.store.entity.Store;
import com.hotnerds.badgeroad.store.repository.StoreRepository;
import com.hotnerds.badgeroad.user.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
}
