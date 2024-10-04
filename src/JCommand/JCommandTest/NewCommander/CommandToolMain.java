package JCommand.JCommandTest.NewCommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;

public class CommandToolMain {
    private final JCommander jcommander;

    @Parameter(names = {"-h", "--help"}, help = true, description = "Get all command about TEST.")
    boolean help;

    public CommandToolMain() {
        jcommander = new JCommander();
        jcommander.setProgramName("TEST");
        jcommander.addObject(this);
        jcommander.addCommand("topic", new TopicCommand());
//        jcommander.addCommand("message", new MessageCommand());
//        jcommander.addCommand("group", new ConsumerGroupCommand());
    }

    boolean run(String[] args) {
        try {
            jcommander.parse(args);
        } catch (Exception e) {
            System.err.println(ExceptionUtils.getStackTrace(e));
            System.out.println("--------------------------------------");
            jcommander.usage();
            return false;
        }

        if (help || args.length == 0) {
            jcommander.usage();
            return true;
        }

        String cmd = args[0];
        JCommander obj = jcommander.getCommands().get(cmd);
        AbstractCommand cmdObj = (AbstractCommand) obj.getObjects().get(0);
        System.out.println("obj.getObjects() = " + obj.getObjects().size());
        System.out.println("[ARG]: "+Arrays.toString(Arrays.copyOfRange(args, 0, args.length)));
        //AbstractCommand运行继承了AbstractCommandRunner的各个对象
        return cmdObj.run(Arrays.copyOfRange(args, 1, args.length));
    }



    public static void main(String[] args) {
//        args = new String[]{"topic", "list", "-t", "testTopicName0" , "-c", "zcw"};
        args = new String[]{"topic", "list" , "-c", "zcw", "-t", "testTopicName0"};
        CommandToolMain tubectlTool = new CommandToolMain();
        if (tubectlTool.run(args)) {
            System.exit(0);
        } else {
            System.exit(1);
        }

    }
}
