package cn.service;

import cn.annotation.SocketCmd;
import cn.annotation.SocketModule;

/**
 * @author YCKJ
 */
@SocketModule(module = 1)
public interface UserService {
    /**
     * 登录
     */
    @SocketCmd(cmd = 1)
    void login();

    /**
     * 获取信息
     */
    @SocketCmd(cmd = 2)
    void getInfo();
}
