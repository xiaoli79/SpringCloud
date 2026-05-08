package com.xiaoli.blog.controller;

import com.xiaoli.api.pojo.AddBlogInfoRequest;
import com.xiaoli.api.pojo.BlogInfoResponse;
import com.xiaoli.api.BlogServiceApi;
import com.xiaoli.api.pojo.UpBlogRequest;
import com.xiaoli.blog.service.BlogService;
import common.xiaoli.common.pojo.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController implements BlogServiceApi {
    @Autowired
    private BlogService blogService;


    @Override
    public Result<List<BlogInfoResponse>> getList(){
        return Result.success(blogService.getList());
    }


    @Override
    public Result<BlogInfoResponse> getBlogDeatail(@NotNull Integer blogId){
        log.info("getBlogDetail, blogId: {}", blogId);
        return Result.success(blogService.getBlogDeatil(blogId));
    }

    @Override
//    @Validated 用于启用方法参数的验证，确保参数的有效性~~
    public Result<Boolean> addBlog(@Validated @RequestBody AddBlogInfoRequest addBlogInfoRequest){
        log.info("addBlog 接收参数: "+ addBlogInfoRequest);
        return Result.success(blogService.addBlog(addBlogInfoRequest));
    }
    /**
     * 更新博客
     */
//    @RequestBody 用于将请求中的参数映射到方法的参数列表上~~
    @Override
    public Result<Boolean> updateBlog(@Valid @RequestBody UpBlogRequest upBlogRequest){
        log.info("updateBlog 接收参数: "+ upBlogRequest);
        return Result.success(blogService.update(upBlogRequest));

    }

    @Override
    public Result<Boolean> deleteBlog(@NotNull Integer blogId){
        log.info("deleteBlog 接收参数: "+ blogId);
        return  Result.success(blogService.delete(blogId));
    }
}
