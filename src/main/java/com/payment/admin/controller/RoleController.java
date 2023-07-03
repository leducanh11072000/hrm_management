package com.payment.admin.controller;

import com.payment.admin.common.CommonResult;
import com.payment.admin.common.exception.ApiException;
import com.payment.admin.dto.request.AddRoleRequest;
import com.payment.admin.entity.Role;
import com.payment.admin.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin')")
    public Object get() {
        return CommonResult.success(service.queryAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public Object add(@RequestBody @Valid AddRoleRequest request){
        Role entity = new Role();
        BeanUtils.copyProperties(request, entity);
        return CommonResult.success(service.insertOrUpdate(entity));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Object update(@PathVariable("id") String id, @RequestBody @Valid AddRoleRequest role){
        Role entity = service.get(id);
        if (entity == null) {
            throw new ApiException("Role not Exit!");
        }
        BeanUtils.copyProperties(role, entity);
        return CommonResult.success(service.insertOrUpdate(entity));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Object delete(@PathVariable String id){
        service.deleteById(id);
        return CommonResult.success();
    }
}
