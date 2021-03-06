package com.nowcoder.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by Shirley on 2017/7/16.
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users=new ThreadLocal<User>();

    public User getUser(){
        return users.get();
    }

    public void  setUser(User user){
        users.set(user);
    }

    public  void clear(){
        users.remove();
    }

}
