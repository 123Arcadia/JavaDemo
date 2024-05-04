package org.example.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

interface SmsService {
    String send(String message);
}

class SmsServiceImpl implements SmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

class dynamicProxy implements InvocationHandler {

    private final Object obj;

    public dynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------before-----");
        Object res = method.invoke(obj, args);
        System.out.println("-------after-----");
        return res;
    }
}



public class dynamicProxyTest {

    public static void main(String[] args) {
        SmsService proxy = (SmsService)JdkProxyFactory.getProxy(new SmsServiceImpl());
        String res = proxy.send("send....");
        System.out.println("res = " + res);
        ///-------before-----
        //send message:send....
        //-------after-----
        //res = send....

    }


}