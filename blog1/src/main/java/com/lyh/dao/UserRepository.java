package com.lyh.dao;

import com.lyh.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2020/11/26.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
