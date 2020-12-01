package com.lyh.dao;

import com.lyh.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2020/11/28.
 */
public interface BlogRepository extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog> {
}
