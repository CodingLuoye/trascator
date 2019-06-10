package cn.invoker;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行器管理者
 *
 * @author zhuliang
 */
public class InvokerHandler {

    public static Map<Integer, Map<Integer, Invoker>> invokers = new HashMap<Integer, Map<Integer, Invoker>>();

    public static void addInvoker(int module, int cmd, Invoker invoker) {
        Map<Integer, Invoker> map = invokers.get(module);
        if (map == null) {
            map = new HashMap<Integer, Invoker>(1);
            invokers.put(module, map);
        }
        map.put(cmd, invoker);
    }

    public static Invoker getInvoker(int module, int cmd) {
        Map<Integer, Invoker> map = invokers.get(module);
        if (map == null) {
            return null;
        }
        return map.get(cmd);
    }
}
