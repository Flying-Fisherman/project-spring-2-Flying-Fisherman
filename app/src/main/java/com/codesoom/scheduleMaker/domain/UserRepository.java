package com.codesoom.scheduleMaker.domain;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(String id);

    boolean existsByPhone(String phone);
}
