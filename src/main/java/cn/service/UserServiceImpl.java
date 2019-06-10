package cn.service;

import org.springframework.stereotype.Component;

/**
 * @author YCKJ
 */
@Component
public class UserServiceImpl implements UserService {
    /**
     * 登录
     */
    public void login() {
        System.out.println("login");
    }

    /**
     * 获取信息
     */
    public void getInfo() {
        System.out.println("getInfo");
    }
}
