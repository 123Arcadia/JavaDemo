package JCommand.JCommandTest.NewCommander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Parameters(commandDescription = "Command for topic management")
public class TopicCommand extends AbstractCommand {

    @Parameter()
    private List<String> params;

    final private static String[] requestMethod = new String[]{"--method", ""};

    final private static Map<String, Object> requestParams = new HashMap<>();

//    final private static CliWebapiAdmin cliWebapiAdmin = new CliWebapiAdmin(requestParams);

    public TopicCommand() {
        super("topic");

        jcommander.addCommand("list", new TopicList());
//        jcommander.addCommand("update", new TopicUpdate());
//        jcommander.addCommand("create", new TopicCreate());
//        jcommander.addCommand("delete", new TopicDelete());
    }

    @Parameters(commandDescription = "List topic")
    private static class TopicList extends AbstractCommandRunner {

        @Parameter()
        private List<String> params;

        @Parameter(names = {"-t", "--topic"}, order = 0, description = "Topic name")
        private String topicName;

        @Parameter(names = {"-sid", "--topic-status-id"}, order = 1, description = "Topic status ID")
        private int topicStatusId = 0;

        @Parameter(names = {"-bid", "--broker-id"}, order = 2, description = "Brokers' ID, separated by commas")
        private String brokerId;

        @Parameter(names = {"-dp", "--delete-policy"}, order = 3, description = "File aging strategy")
        private String deletePolicy;

        @Parameter(names = {"-np", "--num-partitions"}, order = 4, description = "Number of partitions")
        private int numPartitions = 3;

        @Parameter(names = {"-nts", "--num-topic-stores"}, order = 5, description = "Number of topic stores")
        private int numTopicStores = 1;

        @Parameter(names = {"-uft",
                "--unflush-threshold"}, order = 6, description = "Maximum allowed disk unflushing message count")
        private int unflushThreshold = 1000;

        @Parameter(names = {"-ufi",
                "--unflush-interval"}, order = 7, description = "Maximum allowed disk unflushing interval")
        private int unflushInterval = 10000;

        @Parameter(names = {"-ufd",
                "--unflush-datahold"}, order = 8, description = "Maximum allowed disk unflushing data size")
        private int unflushDataHold = 0;

        @Parameter(names = {"-mc",
                "--memcache-msgcnt-ink"}, order = 9, description = "Maximum allowed memory cache unflushing message count")
        private int memCacheMsgCntInK = 10;

        @Parameter(names = {"-ms",
                "--memcache-msgsize-inmb"}, order = 10, description = "Maximum allowed memory cache size in MB")
        private int memCacheMsgSizeInMB = 2;

        @Parameter(names = {"-mfi",
                "--memcache-flush-intvl"}, order = 11, description = "Maximum allowed disk unflushing data size")
        private int memCacheFlushIntvl = 20000;

        @Parameter(names = {"-c", "--creator"}, order = 12, description = "Record creator")
        private String createUser;

        @Parameter(names = {"-m", "--modifier"}, order = 13, description = "Record modifier")
        private String modifyUser;

        @Override
        void run() {
            try {
                requestMethod[1] = "admin_query_topic_info";
                requestParams.clear();
//                if (topicName != null)
//                    requestParams.put(WebFieldDef.TOPICNAME.name, topicName);
//                requestParams.put(WebFieldDef.TOPICSTATUSID.name, topicStatusId);
//                if (brokerId != null)
//                    requestParams.put(WebFieldDef.BROKERID.name, brokerId);
//                if (deletePolicy != null)
//                    requestParams.put(WebFieldDef.DELETEPOLICY.name, deletePolicy);
//                requestParams.put(WebFieldDef.NUMPARTITIONS.name, numPartitions);
//                requestParams.put(WebFieldDef.NUMTOPICSTORES.name, numTopicStores);
//                requestParams.put(WebFieldDef.UNFLUSHTHRESHOLD.name, unflushThreshold);
//                requestParams.put(WebFieldDef.UNFLUSHINTERVAL.name, unflushInterval);
//                requestParams.put(WebFieldDef.UNFLUSHDATAHOLD.name, unflushDataHold);
//                requestParams.put(WebFieldDef.UNFMCACHECNTINK.name, memCacheMsgCntInK);
//                requestParams.put(WebFieldDef.MCACHESIZEINMB.name, memCacheMsgSizeInMB);
//                requestParams.put(WebFieldDef.UNFMCACHEINTERVAL.name, memCacheFlushIntvl);
//                if (createUser != null)
//                    requestParams.put(WebFieldDef.CREATEUSER.name, createUser);
//                if (modifyUser != null)
//                    requestParams.put(WebFieldDef.MODIFYUSER.name, modifyUser);
//                cliWebapiAdmin.processParams(requestMethod);

                if (topicName!=null) {
                    System.out.println("[RUN]topicName = " + topicName);
                }

                if (createUser!=null) {
                    System.out.println("[RUN]createUser = " + createUser);
                }
                System.out.println("------------------END------------------");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }



}
