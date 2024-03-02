package Utils;

import jdk.jfr.Configuration;
import jdk.jfr.consumer.RecordingStream;
import org.junit.Test;

public class MoniterBlocked {
    @Test
    public void test() {
        Configuration config = null;
        try {
            config = Configuration.getConfiguration("default");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置监控的锁 block 时间超过多少就会采集
        config.getSettings().put("jdk.JavaMonitorEnter#threshold", "1s");
        try (var es = new RecordingStream(config)) {
            es.onEvent("jdk.JavaMonitorEnter", recordedEvent -> {
                //如果堆栈包含我们关注的，则报警
                if (recordedEvent.getStackTrace().getFrames().stream().anyMatch(recordedFrame -> recordedFrame.toString().contains("org.apache.logging.log4j.core.async.AsyncLoggerConfigDisruptor.enqueue")))  {
                    System.out.println("Alarm: " + recordedEvent);
                }
            });
            es.start();
        }
    }

}
