package com.payment.admin.controller;

import com.payment.admin.common.CommonResult;
import com.payment.admin.dto.request.AuthRequest;
import com.payment.admin.dto.request.UpdatePasswordRequest;
import com.payment.admin.entity.User;
import com.payment.admin.service.UserService;
import com.payment.admin.util.HttpUtil;
import com.payment.admin.util.JwtTokenUtil;
import com.payment.admin.util.MD5Util;
import com.payment.admin.util.RandomUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/register")
    public Object save(@RequestBody @Valid AuthRequest request) {
        String userName = request.getUserName();
        String password = request.getPassword();

        User user = userService.findByUserName(userName);
        if (user != null) {
            return CommonResult.failed("account is not exit");
        }
        String salt = RandomUtil.getRandomString(5);
        User userInfo = userService.insertOrUpdate(
                 User.builder()
                        .userName(userName)
                        .password(MD5Util.md5(password, salt))
                        .salt(salt)
                        .build()
        );
        userInfo.setPassword(password);
        return CommonResult.success();
    }


    @PostMapping("/login")
    public Object login(@RequestBody @Valid AuthRequest request) {
        try {
            User user = userService.findByUserName(request.getUserName());
            System.out.println(user);
            if (user == null) {
                return CommonResult.failed("User Not Exist");
            }
            String passwdMd5 = MD5Util.md5(request.getPassword(), user.getSalt());
            if (!user.getPassword().equals(passwdMd5)) {
                return CommonResult.failed("wrongPasswordEntered");
            }

            String token = jwtTokenUtil.generateAccessToken(user);
            Map<String, String> result = new HashMap<>(1);
            result.put("token", token);
            return CommonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return CommonResult.failed("loginFailed");
    }


    @PostMapping( "/logout")
    public Object logout() {
        return CommonResult.success();
    }

    @PostMapping("/updatePwd")
    public Object updatePwd(@RequestBody @Valid UpdatePasswordRequest request) {
        try {
            String token = HttpUtil.getToken();
            String userName = jwtTokenUtil.getSubject(token);
            User userInfo = userService.findByUserName(userName);
            if (!MD5Util.md5(request.getOldPassword(), userInfo.getSalt()).equals(userInfo.getPassword())) {
                return CommonResult.failed("wrongOldPassword");
            }
            if (!request.getPassword().equals(request.getRePassword())) {
                return CommonResult.failed("theNewPasswordIsInconsistent");
            }
            userInfo.setPassword(MD5Util.md5(request.getPassword(), userInfo.getSalt()));
            userService.insertOrUpdate(userInfo);
            return CommonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return CommonResult.failed("failedToChangePassword");
    }
}
