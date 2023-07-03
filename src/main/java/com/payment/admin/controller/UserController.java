package com.payment.admin.controller;

import com.payment.admin.common.CommonResult;
import com.payment.admin.dto.request.AddRoleRequest;
import com.payment.admin.dto.request.AddUserRequest;
import com.payment.admin.dto.request.UpdateUserRequest;
import com.payment.admin.dto.request.UserSearchRequest;
import com.payment.admin.entity.User;
import com.payment.admin.query.Page;
import com.payment.admin.query.SearchFilter;
import com.payment.admin.service.UserService;
import com.payment.admin.util.MD5Util;
import com.payment.admin.util.RandomUtil;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('admin')")
    @Parameter(in = ParameterIn.HEADER)
    public Object get(@RequestBody @Valid UserSearchRequest request){
        Page<User> page = Page.defaultPage(request.getLimit(), request.getPage());
        page.addFilter(SearchFilter.build("userName", SearchFilter.Operator.LIKE,request.getUserName()));
        page.addFilter(SearchFilter.build("email", SearchFilter.Operator.LIKE,request.getEmail())); // "email%"
        page = userService.queryPage(page);
        return CommonResult.success(page.getRecords());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public Object add(@RequestBody @Valid AddUserRequest request) {
        User user = userService.findByUserName(request.getUserName());
        if (user != null) {
            return CommonResult.failed("account is exit");
        }
        String salt = RandomUtil.getRandomString(5);
        String roles = null;
        if (!ObjectUtils.isEmpty(request.getRoles())){
            roles = request.getRoles().stream().map(AddRoleRequest::getCode).collect(Collectors.joining(";"));
        }
        User userInfo = userService.insertOrUpdate(
                 User.builder()
                        .userName(request.getUserName())
                        .password(MD5Util.md5(request.getPassword(), salt))
                        .salt(salt)
                        .roleCodes(roles)
                        .build()
        );
        return CommonResult.success(userInfo);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Object update(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserRequest request){
        return CommonResult.success(userService.update(id,request));
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable Long id){
        userService.deleteById(id);
        return CommonResult.success();
    }

}
