package com.xiaoli.user.convert;

import com.xiaoli.user.api.pojo.UserInfoRegisterRequest;
import com.xiaoli.user.dataobject.UserInfo;
import common.xiaoli.common.utils.SecurityUtil;

public class BeanConvert {

    public static  UserInfo convertUserInfoByEncrypt(UserInfoRegisterRequest registerRequest){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(registerRequest.getUserName());
        userInfo.setPassword(SecurityUtil.encrypt(registerRequest.getPassword()));
        userInfo.setGithubUrl(registerRequest.getGithubUrl());
        userInfo.setEmail(registerRequest.getEmail());
        return userInfo;
    }
}
