package com.auto.mementauto.services;

import com.auto.mementauto.dtos.UserDto;
import com.auto.mementauto.entities.UserEntity;
import com.auto.mementauto.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity saveUser(UserDto userDto) {
        if (Boolean.TRUE.equals(userRepository.existsUserEntitiesByLogin(userDto.getLogin()))) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setLogin(userDto.getLogin());
        user.setName(userDto.getName());
        user.setNotificationOn(true);
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByLogin(username);
        if(user == null){
            log.error("User not found in the database: {}", username);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", user);
        }
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public UserEntity findUserEntityByLogin(String login) {
        return userRepository.findUserEntityByLogin(login);
    }
}
