package com.xiaoli.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoli.blog.dataobject.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapper<BlogInfo> {
}
