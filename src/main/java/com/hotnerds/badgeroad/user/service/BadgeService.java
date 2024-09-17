package com.hotnerds.badgeroad.user.service;

import com.hotnerds.badgeroad.store.repository.StoreRepository;
import com.hotnerds.badgeroad.user.entity.Badge;
import com.hotnerds.badgeroad.user.repository.BadgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BadgeService {

    BadgeRepository badgeRepository;

    public Optional<Badge> findByName(String name) {
        return badgeRepository.findByName(name);
    }

    public List<Badge> findAllBadges() {
        return badgeRepository.findAll();
    }

    public void putAllBadgesFromStore() {
//        StoreRepository
    }

}
