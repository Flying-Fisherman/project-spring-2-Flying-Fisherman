package com.codesoom.scheduleMaker.infra;

import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaUserRepository
        extends UserRepository, CrudRepository<User, Long> {

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(String id);

    boolean existsByPhone(String phone);

    Optional<User> findById(String userId);

    Optional<User> findByUid(Long uid);

    void delete(User user);
}
