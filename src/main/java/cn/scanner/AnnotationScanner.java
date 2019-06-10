package cn.scanner;

import cn.invoker.Invoker;
import cn.invoker.InvokerHandler;
import cn.annotation.SocketCmd;
import cn.annotation.SocketModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义注解扫描器
 *
 * @author zhuliang
 */
@Component
public class AnnotationScanner implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            //扫描所有接口
            for (Class<?> interFace : interfaces) {
                SocketModule socketModule = interFace.getAnnotation(SocketModule.class);
                if (socketModule == null) {
                    continue;
                }
                Method[] methods = interFace.getMethods();
                if (methods != null && methods.length > 0) {
                    //遍历所有带有SocketCmd注解的方法
                    for (Method method : methods) {
                        SocketCmd socketCmd = method.getAnnotation(SocketCmd.class);
                        if (socketCmd == null) {
                            continue;
                        }
                        int module = socketModule.module();
                        int cmd = socketCmd.cmd();
                        Invoker invoker = Invoker.valueOf(bean, method);
                        if (InvokerHandler.getInvoker(module, cmd) == null) {
                            InvokerHandler.addInvoker(module, cmd, invoker);
                        } else {
                            System.out.println("重复注册执行器module：" + module + "   cmd：" + cmd);
                        }
                    }
                }
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
