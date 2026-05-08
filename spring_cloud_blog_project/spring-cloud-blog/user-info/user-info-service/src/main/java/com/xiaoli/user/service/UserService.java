package com.xiaoli.user.service;


import com.xiaoli.user.api.pojo.UserInfoRegisterRequest;
import com.xiaoli.user.api.pojo.UserInfoRequest;
import com.xiaoli.user.api.pojo.UserInfoResponse;
import com.xiaoli.user.api.pojo.UserLoginResponse;

public interface UserService {
    UserLoginResponse login(UserInfoRequest user);

    UserInfoResponse getUserInfo(Integer userId);

    UserInfoResponse selectAuthorInfoByBlogId(Integer blogId);

    Integer register(UserInfoRegisterRequest registerRequest);
}
