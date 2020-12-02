package com.lyh.service;

import com.lyh.po.Comment;

import java.util.List;

/**
 * Created by Administrator on 2020/12/2.
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
