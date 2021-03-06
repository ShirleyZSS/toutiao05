package com.nowcoder.controller;

import com.nowcoder.model.HostHolder;
import com.nowcoder.model.News;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.UserService;
import com.sun.prism.image.ViewPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shirley on 2017/7/13.
 */
@Controller
public class HomeController {
    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;

    private List<ViewObject> getNews(int userId,int offset,int limit){
        List<News> newsList =newsService.getLatestNews(userId,offset,limit);

        List<ViewObject> vos=new ArrayList<>();
        for(News news:newsList){
            ViewObject vo=new ViewObject();
            vo.set("news",news);
            vo.set("user",userService.getUser(news.getUserId()));
            vos.add(vo);
        }
        return vos;


    }

    @RequestMapping(path={"/","/index"},method={RequestMethod.GET,RequestMethod.POST})
    public String index(Model model){
        model.addAttribute("vos",getNews(0,0,10));
        return "home";
    }

    @RequestMapping(path={"/user/{userId}"},method = {RequestMethod.GET,RequestMethod.POST})
    public String userIndex(Model model, @PathVariable("userId")int userId,
                            @RequestParam(value="pop",defaultValue = "0") int pop ){//从前端读取pop
        model.addAttribute("vos",getNews(userId,0,10));
        model.addAttribute("pop",pop);
        return "home";
    }

    @RequestMapping(path={"/logout/"},method={RequestMethod.GET,RequestMethod.POST})
    public String logout(@CookieValue("ticket")String ticket){
        userService.logout(ticket);
        return "redirect:/";//登出 自动跳转首页

    }

}
