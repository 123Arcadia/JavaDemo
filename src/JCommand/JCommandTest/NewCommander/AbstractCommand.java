package JCommand.JCommandTest.NewCommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public abstract class AbstractCommand {

    protected final JCommander jcommander;

    @Parameter(names = {"-h", "--help"}, help = true, hidden = true)
    private boolean help;

    public AbstractCommand(String cmdName) {
        jcommander = new JCommander();
        jcommander.setProgramName("tubectl " + cmdName);
    }

    public boolean run(String[] args) {

        if (help) {
            jcommander.usage();
            return true;
        }

        try {
            jcommander.parse(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            jcommander.usage();
            return false;
        }

        String cmd = jcommander.getParsedCommand();
        if (cmd == null) {
            jcommander.usage();
            return false;
        } else {
            JCommander obj = jcommander.getCommands().get(cmd);
            AbstractCommandRunner commandRunner = (AbstractCommandRunner) obj.getObjects().get(0);
            try {
                commandRunner.run();
                return true;
            } catch (ParameterException e) {
                System.err.println(e.getMessage() + System.lineSeparator());
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
