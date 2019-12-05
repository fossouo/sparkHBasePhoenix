// import required classes and interfaces
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.deploy.yarn.Client;
import org.apache.spark.deploy.yarn.ClientArguments;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class readTable_phoenix_yarn {

    public static void main(String[] arguments) throws Exception {

        Logger.getLogger("org").setLevel(Level.DEBUG);

        try {
            UserGroupInformation.setConfiguration(new Configuration());
            UserGroupInformation.loginUserFromKeytab("hbase/dfossouo-1.dfossouo.root.hwx.site@ROOT.HWX.SITE",
                    "/tmp/hbase.keytab");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // prepare arguments to be passed to
        // org.apache.spark.deploy.yarn.Client object
        String[] args = new String[] {
                // the name of your application
                "--name",
                "enel",

                // memory for driver (optional)
                "--driver-memory",
                "1000M",

                // path to your application's JAR file
                // required in yarn-cluster mode
                "--jar",
                "/Applications/Jhipster/target/enel-1.0-SNAPSHOT.jar",

                // name of your application's main class (required)
                "--class",
                "readTable_phoenix",
                "--arg",
                "spark.driver.extraJavaOptions=-Djava.security.auth.login.config=jaas.conf",
                "--arg",
                "spark.executor.extraJavaOptions=-Djava.security.auth.login.config=jaas.conf",

                // comma separated list of local jars that want
                // SparkContext.addJar to work with
                // argument 4 to your Spark program (SparkFriendRecommendation)
                // this is a helper argument to create a proper JavaSparkContext object
                // make sure that you create the following in SparkFriendRecommendation program
                // ctx = new JavaSparkContext("yarn-cluster", "SparkFriendRecommendation");
                "--arg",
                "yarn-cluster"
        };

        // create a Hadoop Configuration object
        Configuration config = new Configuration();

        // identify that you will be using Spark as YARN mode
        System.setProperty("SPARK_YARN_MODE", "true");

        // create an instance of SparkConf object
        SparkConf sparkConf = new SparkConf();

        sparkConf.set("spark.yarn.historyServer.address",
                "dfossouo-2.dfossouo.root.hwx.site:8032");

        sparkConf.set("spark.hadoop.yarn.resourcemanager.hostname",
                "dfossouo-2.dfossouo.root.hwx.site");

        sparkConf.set("spark.hadoop.yarn.resourcemanager.address",
                "dfossouo-2.dfossouo.root.hwx.site:8032");

        sparkConf.set("spark.yarn.security.tokens.habse.enabled",
                "true");

        sparkConf.set("hadoop.root.logger","DEBUG");

        // create ClientArguments, which will be passed to Client
        ClientArguments cArgs = new ClientArguments(args, sparkConf);

        // create an instance of yarn Client client
        Client client = new Client(cArgs, config, sparkConf);

        // submit Spark job to YARN
        client.run();
    }
}