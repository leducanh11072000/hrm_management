package com.payment.admin.service;

import com.payment.admin.common.exception.ApiException;
import com.payment.admin.dto.request.AddRoleRequest;
import com.payment.admin.dto.request.UpdateUserRequest;
import com.payment.admin.entity.User;
import com.payment.admin.query.SearchFilter;
import com.payment.admin.repository.UserRepository;
import com.payment.admin.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<User,Long, UserRepository> {
    @Override
    protected UserRepository getDao() {
        return repository;
    }

    @Autowired
    private UserRepository repository;


    public User findByUserName(String userName) {
        return repository.findUserByUserName(userName);
    }

    public User update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id).orElse(null);
        String roles = null;
        if (!ObjectUtils.isEmpty(request.getRoles())) {
            roles = request.getRoles().stream().map(AddRoleRequest::getCode).collect(Collectors.joining(";"));
        }
        if (user != null) {
            user.setRoleCodes(roles);
            return repository.save(user);
        }
        throw new ApiException("User not found!");
    }
}
