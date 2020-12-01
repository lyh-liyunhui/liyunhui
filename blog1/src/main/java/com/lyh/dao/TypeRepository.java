package com.lyh.dao;

import com.lyh.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2020/11/27.
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
