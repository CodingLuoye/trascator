package cn.main;

import cn.invoker.Invoker;
import cn.invoker.InvokerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author YCKJ
 */
public class App {

    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");

        Invoker invoker = InvokerHandler.getInvoker(1, 1);

        invoker.invoke(null);
    }
}