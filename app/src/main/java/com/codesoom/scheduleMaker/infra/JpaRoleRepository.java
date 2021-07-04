package com.codesoom.scheduleMaker.infra;

import com.codesoom.scheduleMaker.domain.Role;
import com.codesoom.scheduleMaker.domain.RoleRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaRoleRepository
        extends RoleRepository, CrudRepository<Role, Long> {
    List<Role> findAllByUserUid(Long userUid);

    Role save(Role role);
}
