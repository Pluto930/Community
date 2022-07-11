package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelect(){
        User user = userMapper.selectById(1);
        System.out.println(user);

        user=userMapper.selectByName("SYSTEM");
        System.out.println(user);

        user=userMapper.selectByEmail("nowcoder1@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsert(){
        User user=new User();
        user.setUsername("张三");
        user.setPassword("123456");
        user.setSalt("12345");
        user.setEmail("123@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        int row = userMapper.insertUser(user);
        System.out.println(row);
    }

    @Test
    public void testUpdate(){
        int row = userMapper.updateUser(150, 1);
        System.out.println(row);

        row=userMapper.updateHeader(150,"http://www.nowcoder.com/102.png");
        System.out.println(row);

        row=userMapper.updatePassword(150,"846385");
        System.out.println(row);
    }

    @Test
    public void testDiscussPost(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

}
