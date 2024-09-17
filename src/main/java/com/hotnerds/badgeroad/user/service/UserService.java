package com.hotnerds.badgeroad.user.service;

import com.hotnerds.badgeroad.user.dto.LoginDto;
import com.hotnerds.badgeroad.user.dto.UserDto;
import com.hotnerds.badgeroad.user.entity.Badge;
import com.hotnerds.badgeroad.user.entity.User;
import com.hotnerds.badgeroad.user.exception.DuplicateUserException;
import com.hotnerds.badgeroad.user.exception.NotFoundUserException;
import com.hotnerds.badgeroad.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final JdbcTemplate jdbcTemplate;
    @Transactional
    public UserDto findByEmail(String email) {
        return UserDto.from(userRepository.findByEmail(email).orElse(null));
    }

    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .build();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new DuplicateUserException("이미 가입되어 있는 유저입니다.");
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .build();

        return UserDto.from(userRepository.save(user));
    }

    public Boolean login(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        return user.map(value -> value.getPassword().equals(loginDto.getPassword())).orElse(false);


    }

//    public List<Badge> findAllBadgesByEmail(String email) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("email", email);
//        Optional<User> user = userRepository.findByEmail(email);
//
//        return jdbcTemplate.queryForList(
//                "SELECT " +
//                        "badge_name" +
//                    "FROM " +
//                        "user_badges" +
//                        "WHERE " +
//                        "email = :email", params);
//        )
//    }
}
