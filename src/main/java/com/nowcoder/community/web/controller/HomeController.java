package com.nowcoder.community.web.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPost=new ArrayList<>();

        if(list!=null){
            for (DiscussPost post : list) {
                Map<String,Object> map=new HashMap<>(); //需要将帖子的ID转换为UserName
                User user = userService.findUserById(post.getUserId());
                map.put("post",post);
                map.put("user",user);
                discussPost.add(map);
            }
        }

        model.addAttribute("discussPosts",discussPost);
        return "/index";
    }


    @RequestMapping(path = "/upImage", method = RequestMethod.GET)
    public void getHeader(HttpServletResponse response) {
        //https://tu1.whhost.net/uploads/20181207/09/1544147361-vJzlAfVDMT.jpeg
        //服务器存放路径 upload + / + fileName
        String fileName = "E:/idea/IntelliJ IDEA 2021.2.3/image/logo4.png";
        //解析文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //响应图片
        response.setContentType("image/" + suffix);

        try (
                FileInputStream is = new FileInputStream(fileName); //在这个小括号里，结束后，会自动调用close方法
        ) {
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = is.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
        }
    }

}
