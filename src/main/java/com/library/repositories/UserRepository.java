package com.library.repositories;

import com.library.entities.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmailContainsIgnoreCase (String email);
    Optional<UserEntity> findByLastName (String lastName);
    @NonNull Optional<UserEntity> findById (@NonNull String id);
}
