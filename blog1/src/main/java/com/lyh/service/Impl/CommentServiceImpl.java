package com.lyh.service.Impl;

import com.lyh.dao.CommentRepository;
import com.lyh.po.Comment;
import com.lyh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/12/2.
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        List<Comment> comments=commentRepository.findByBlogId(blogId,sort);
        return 
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommetId=comment.getParentComment().getId();
        if(parentCommetId!=-1){
            comment.setParentComment(commentRepository.findOne(parentCommetId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
