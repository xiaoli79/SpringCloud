package com.xiaoli.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoli.api.BlogServiceApi;
import com.xiaoli.api.pojo.BlogInfoResponse;
import com.xiaoli.user.api.pojo.UserInfoRegisterRequest;
import com.xiaoli.user.api.pojo.UserInfoRequest;
import com.xiaoli.user.api.pojo.UserInfoResponse;
import com.xiaoli.user.api.pojo.UserLoginResponse;
import com.xiaoli.user.convert.BeanConvert;
import com.xiaoli.user.dataobject.UserInfo;
import com.xiaoli.user.mapper.UserInfoMapper;
import com.xiaoli.user.service.UserService;
import common.xiaoli.common.costant.Constants;
import common.xiaoli.common.exception.BlogException;
import common.xiaoli.common.pojo.Result;
import common.xiaoli.common.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BlogServiceApi blogServiceApi;

    @Autowired
    private Redis redis;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //超时时间  超时时间为2周！！
    private static final long EXPIRE_TIME = 14 * 24 * 60 * 60;
    private static final String USER_PREFIX = "user";



    @Override
    public UserLoginResponse login(UserInfoRequest user) {
        //验证账号密码是否正确
        UserInfo userInfo = queryUserInfo(user.getUserName());
        if (userInfo==null || userInfo.getId()==null){
            throw new BlogException("用户不存在");
        }
//        if (!user.getPassword().equals(userInfo.getPassword())){
//            throw new BlogException("用户密码不正确");
//        }
        if (!SecurityUtil.verify(user.getPassword(),userInfo.getPassword())){
            throw new BlogException("用户密码不正确");
        }
        //账号密码正确的逻辑
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userInfo.getId());
        claims.put("name", userInfo.getUserName());

        String jwt = JWTUtils.genJwt(claims);
        return new UserLoginResponse(userInfo.getId(), jwt);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        UserInfo userInfo = selectUserInfoById(userId);
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse selectAuthorInfoByBlogId(Integer blogId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        //1. 根据博客ID, 获取作者ID
        Result<BlogInfoResponse> blogDeatail = blogServiceApi.getBlogDeatail(blogId);
        //2. 根据作者ID, 获取作者信息
        if (blogDeatail== null || blogDeatail.getData()==null){
            throw new BlogException("博客不存在");
        }
        UserInfo userInfo = selectUserInfoById(blogDeatail.getData().getId());
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    /**
     * 用户注册
     * @param registerRequest
     * @return
     */

    @Override
    public Integer register(UserInfoRegisterRequest registerRequest) {
        checkUserInfo(registerRequest);

//      获取用户信息~~
        UserInfo userInfo = BeanConvert.convertUserInfoByEncrypt(registerRequest);
        try{
            int result = userInfoMapper.insert(userInfo);
            if(result == 1){
                log.info("当注册成功的时候，redis中设置key");
                redis.set(buildkey(userInfo.getUserName()),JsonUtil.toJson(userInfo),EXPIRE_TIME);
                //发送消息
                userInfo.setPassword("");
//              生产者
                rabbitTemplate.convertAndSend(Constants.USER_EXCHANGE_NAME,"" ,JsonUtil.toJson(userInfo));
                return userInfo.getId();
            }else{
                throw new BlogException("用户注册失败");
            }
        }catch (Exception e){
            log.error("用户注册失败"+e.getMessage());
            throw new BlogException("用户注册失败");
        }
    }

    private String buildkey(String userName) {
        return redis.buildKey(USER_PREFIX,userName);
    }


    private void checkUserInfo(UserInfoRegisterRequest registerRequest) {

        UserInfo userInfo = selectUserInfoByName(registerRequest.getUserName());
        if(userInfo!=null){
            throw new BlogException("用户名已经存在");
        }
        //TODO
        //邮箱格式，url格式
        if(!RegexUtil.checkMail(registerRequest.getEmail())){
            throw new BlogException("邮箱格式不合法");
        }
        if(!RegexUtil.checkURL(registerRequest.getGithubUrl())){
            throw new BlogException("githubUrl格式不合法");
        }
    }


    private UserInfo queryUserInfo(String userName) {
        String key = buildkey(userName);
        boolean exist = redis.hasKey(key);
        if(exist){
            log.info("从redis中获取数据，key:{}",key);
//          从redis中获取数据
            String userJson = redis.get(key);
            UserInfo userInfo = JsonUtil.parseJson(userJson, UserInfo.class);
            return userInfo==null?selectUserInfoByName(userName):userInfo;
        }else{
//          从mysql中获取数据~~
            UserInfo userInfo = selectUserInfoByName(userName);
//          从MySQL中获取数据的同时更新redis~~
            redis.set(key,JsonUtil.toJson(userInfo),EXPIRE_TIME);
            return userInfo;

        }
    }



    public UserInfo selectUserInfoByName(String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName, userName).eq(UserInfo::getDeleteFlag, 0));
    }
    private UserInfo selectUserInfoById(Integer userId) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, userId).eq(UserInfo::getDeleteFlag, 0));
    }
}
