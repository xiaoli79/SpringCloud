package com.xiaoli.blog.service;


import com.xiaoli.api.pojo.AddBlogInfoRequest;
import com.xiaoli.api.pojo.BlogInfoResponse;
import com.xiaoli.api.pojo.UpBlogRequest;

import java.util.List;

public interface BlogService {
    List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDeatil(Integer blogId);

    Boolean addBlog(AddBlogInfoRequest addBlogInfoRequest);

    Boolean update(UpBlogRequest upBlogRequest);

    Boolean delete(Integer blogId);
}
