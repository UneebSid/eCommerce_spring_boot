package com.training.ecommerce.rest.api.repository;

import com.training.ecommerce.rest.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>
{
    Optional<Role> findByName(String name);
}
