package JCommand.JCommandTest;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args2) {
        JCommanderExample jct = new JCommanderExample();
        System.out.println("----------argv1-------------");
        String[] argv1 = {"-log", "2", "-groups", "unit"};
//        new JCommander(jct, argv);

        JCommander jCommander = JCommander.newBuilder().addObject(jct).build();
        jCommander.parse(argv1);
//        Assert.assertEquals(jct.verbose.intValue(), 2);
        System.out.println("jct = " + jct.toString());
        //jct = JCommanderExample(parameters=[], verbose=2, groups=unit, debug=false)
        //jct.verbose.intValue() = 2

        System.out.println("----------argv2-------------");
        String[] argv2 = {"-log", "3", "-groups", "unit"};
        JCommander.newBuilder().addObject(jct).build().parse(argv2);
        System.out.println("jct = " + jct.toString());
        //jct = JCommanderExample(parameters=[], verbose=3, groups=unit, debug=false)

        System.out.println("----------argv3-------------");
        String[] argv3 = {"-l", "3", "-groups", "unit"};
        JCommander.newBuilder().addObject(jct).build().parse(argv3);
        System.out.printf("%d %d", jct.length, jct.pattern); //3 0

        System.out.println("----------argv4 密码-------------");
        String[] argv4 = {"-password"};
        JCommander.newBuilder().addObject(jct).build().parse(argv4);
        System.out.println("jct = " + jct);

        System.out.println("----------argv5-------------");
        String[] argv5 = {"--help"};
        JCommander.newBuilder().addObject(jct).build().parse(argv5);
        if (jct.isHelp()) {
            jCommander.usage();
            System.out.println("[help]jct = " + jct);
        }


        System.out.println("----------argv6--不同class的参数-----------");
        String[] argv6 = {"-slave", "slave", "-log", "3"};
        JCommandExample2 slaveArgsParam = new JCommandExample2();
        JCommander.newBuilder().addObject(new Object[]{jct, slaveArgsParam}).build().parse(argv6);
        System.out.println("jct = " + jct);
        System.out.println("slaveArgsParam = " + slaveArgsParam);

        System.out.println("----------argv7--(List / Set)选项指定数量的参数-----------");
        String[] argv7 = {"-mutilParam", "parms1", "parms2"};
        JCommander.newBuilder().addObject(jct).build().parse(argv7);
        System.out.println("parameters = " + jct.parameters);
        //parameters = [parms1, parms2]

        String input = "The version is 2.0";
        String regex = "(2\\.0|1\\.0)";

        System.out.println("----------argv8--参数是对象(设置其自参数)-----------");
        String[] argv8 = {"--mv", "subParams1", "subParams2"};
        JCommander.newBuilder().addObject(jct).build().parse(argv8);
        System.out.println("[参数是对象]jct = " + jct.getMvParameters());


    }
//
//    static class MvParameters {
//        @SubParameter(order = 0)
//        String from;
//        @SubParameter(order = 1)
//        String to;
//    }
//
//    @Test
//    public void arity() {
//        class Parameters {
//            @Parameter(names = {"--mv"}, arity = 2)
//            private MvParameters mvParameters;
//        }
//
//        Parameters args = new Parameters();
//        JCommander.newBuilder()
//                .addObject(args)
//                .args(new String[]{"--mv", "from", "to"})
//                .build();
//
//        System.out.println("args = " + args);
//    }
}
