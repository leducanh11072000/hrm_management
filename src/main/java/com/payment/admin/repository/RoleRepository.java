package com.payment.admin.repository;

import com.payment.admin.entity.Role;
import com.payment.admin.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role,String> {
}
