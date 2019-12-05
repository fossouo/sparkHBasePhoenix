// import required classes and interfaces
import org.apache.spark.deploy.yarn.Client;
import org.apache.spark.deploy.yarn.ClientArguments;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;

public class readTable_phoenix_yarn {

    public static void main(String[] arguments) throws Exception {

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

                // comma separated list of local jars that want
                // SparkContext.addJar to work with
                "--addJars",
                "/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/lib/phoenix-spark-4.14.1-cdh5.16.2.jar:/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/phoenix-4.14.1-cdh5.16.2-client.jar",
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

        // create ClientArguments, which will be passed to Client
        ClientArguments cArgs = new ClientArguments(args, sparkConf);

        // create an instance of yarn Client client
        Client client = new Client(cArgs, config, sparkConf);

        // submit Spark job to YARN
        client.run();
    }
}