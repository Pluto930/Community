package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //param 用于给参数取别名  如果这个方法只有一个参数，并且在if里使用（动态sql），必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

}
