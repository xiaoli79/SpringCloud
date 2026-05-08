package com.xiaoli.user.api;


import com.xiaoli.user.api.pojo.UserInfoRegisterRequest;
import com.xiaoli.user.api.pojo.UserInfoRequest;
import com.xiaoli.user.api.pojo.UserInfoResponse;
import com.xiaoli.user.api.pojo.UserLoginResponse;
import common.xiaoli.common.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service",path = "/user")
public interface UserServiceApi {

    @RequestMapping("/login")
    Result<UserLoginResponse> login(@RequestBody UserInfoRequest user);
    @RequestMapping("/getUserInfo")
    Result<UserInfoResponse> getUserInfo(@RequestParam("userId") Integer userId);
    @RequestMapping("/getAuthorInfo")
    Result<UserInfoResponse> getAuthorInfo(@RequestParam("blogId") Integer blogId);

    @RequestMapping("/register")
    Result<Integer> register(@RequestBody UserInfoRegisterRequest requestRuquest);

}
