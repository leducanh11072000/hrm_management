package com.payment.admin.security;


import com.payment.admin.query.SearchFilter;
import com.payment.admin.service.UserService;
import com.payment.admin.util.HttpUtil;
import com.payment.admin.util.JwtTokenUtil;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String token = HttpUtil.getToken();
        if (ObjectUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!jwtTokenUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);

    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
        if (userDetails==null) return;
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String token) {
        String subject = jwtTokenUtil.getSubject(token);
        com.payment.admin.entity.User user = userService.findByUserName(subject);
        if (user != null) {
            String roleCodes = user.getRoleCodes();
            List<GrantedAuthority> listGrantedAuth = new ArrayList<>();
            if (!ObjectUtils.isEmpty(roleCodes)) {
                List<String> roles = new ArrayList<>(List.of(roleCodes.trim().split(";")));
                roles.removeIf(s -> s == null || s.isBlank());
                roles.forEach(role -> {
                    GrantedAuthority authority = new SimpleGrantedAuthority(role);
                    listGrantedAuth.add(authority);
                });
            }
            return new User(user.getUserName(), user.getPassword(), listGrantedAuth);
        }
        return null;
    }

}
