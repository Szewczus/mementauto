package com.auto.mementauto.repositories;


import com.auto.mementauto.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Boolean existsUserEntitiesByLogin(String login);
    public UserEntity findUserEntityByLogin(String login);
    public UserEntity findUserEntityById(Long id);
}
