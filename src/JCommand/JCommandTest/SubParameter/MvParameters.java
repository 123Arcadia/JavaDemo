package JCommand.JCommandTest.SubParameter;

import com.beust.jcommander.SubParameter;

public class MvParameters {
    @SubParameter(order = 0)
    String from;

    @SubParameter(order = 1)
    String to;
}