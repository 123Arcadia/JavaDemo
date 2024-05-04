package org.example.cglibProxy;

public class main {

    /**
     * cglib要使用jdk1.8,
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 该类没有实现接口
         */
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("java");
        //enhancer = net.sf.cglib.proxy.Enhancer@2e5d6d97
        //before method send
        //send message:java
        //after method send


//        AliSmsService aliSmsService = new AliSmsService();
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(aliSmsService.getClass());
//        enhancer.setCallback(new FixedValue() {
//            @Override
//            public Object loadObject() throws Exception {
//                return "[Callback: FixedValue]";
//            }
//        });
//        enhancer.setClassLoader(aliSmsService.getClass().getClassLoader());
//        Object obj = enhancer.create();
//        System.out.println(obj);
    }
}
