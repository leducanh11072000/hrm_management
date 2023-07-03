package com.payment.admin.service;

import com.payment.admin.dto.request.AddRoleRequest;
import com.payment.admin.entity.Role;
import com.payment.admin.repository.RoleRepository;
import com.payment.admin.service.base.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role,String, RoleRepository> {
    @Override
    protected RoleRepository getDao() {
        return repository;
    }

    @Autowired
    private RoleRepository repository;


}
