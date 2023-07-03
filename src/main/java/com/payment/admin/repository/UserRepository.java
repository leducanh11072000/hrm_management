package com.payment.admin.repository;

import com.payment.admin.entity.User;
import com.payment.admin.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Long> {

    User findUserByUserName(String userName);
}
