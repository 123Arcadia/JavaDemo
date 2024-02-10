package JCommand.JCommandTest;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

/**
 * 参数分割符
 *
 * 不同类的参数-slave
 */
@Data
@Parameters(separators = ":")
public class JCommandExample2 {

    @Parameter(names = "-slave")
    private String slave;
}
