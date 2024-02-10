package JCommand.JCommandTest;

import JCommand.JCommandTest.SubParameter.MvParameters;
import com.beust.jcommander.Parameter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JCommanderExample {
    /**
     * order: 指定该参数的
     */


    /**
     * 列表List和集合set: JCommander 会将其解释为可以多次出现的选项
     *
     * arity : 指定该选项设置的参数数量(此时必须是List<T>或者Set<T>)
     * 如果数量不定：
     *      使用：variableArity = true
     *
     * 可以： 空格 、 逗号 分割参数
     * 例如： java Main -host host1 -verbose -host host2 、 java Main -hosts host1,host2
     */
    @Parameter(names = "-mutilParam", description = "mutil Params!", arity = 2)
    List<String> parameters = new ArrayList<String>();

    @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
    Integer verbose = 1;

    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    String groups;

    //{ "-log", "2", "-groups", "unit" };

    /**
     * argv3： > java Main -l 512 --pattern 2
     */
    @Parameter(names = {"--pattern", "-p"})
    int pattern;
    @Parameter(names = {"--length", "-l"})
    int length;


    /**
     * @Parameter(names = "-debug", description = "Debug mode"): 不用指定后面的参数
     * @Parameter(names = "-debug", description = "Debug mode", arity = 1): 必须指定后面的参数
     *      例如：> program -debug true 、 program -debug false
     */

    @Parameter(names = "-debug", description = "Debug mode")
    boolean debug = false;

    /**
     * 对于 String, Integer, int, Long or long: JCommander 将解析以下参数并尝试将其转换为正确的类型int：Longlong
     *
     * 如果类型解析错误：抛出异常
     */

    /**
     * 密码：password = true
     *
     * 会提示：Value for -password (Connection password):
     *
     * 控制密码是否显于控制台：echoInput = true（默认false）
     *
     */
    @Parameter(names = "-password", description = "Connection password", password = true, echoInput = true)
    private String password;

    /**
     * 自定义类型（转换器和分配器）
     */

    /**
     * @Parameter(names = "--help", help = true)
     *
     */
    @Parameter(names = {"--help"}, description = "显示参数提示信息", help = true)
    private boolean help;

    /**
     * 参数是对象
     */
    @Parameter(names = {"--mv"}, arity = 2)
    private MvParameters mvParameters;
}

