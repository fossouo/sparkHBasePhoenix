import org.apache.spark.sql.*;
import org.apache.spark.SparkContext;
import org.apache.phoenix.spark.*;
import org.apache.phoenix.spark.DefaultSource;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.phoenix.query.QueryServices;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;

import org.apache.phoenix.jdbc.PhoenixDriver;

import com.google.common.collect.ImmutableMap;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class readTable_phoenix23 {
    public static void main(String[] args){

        try {
            UserGroupInformation.setConfiguration(new Configuration());
            UserGroupInformation.loginUserFromKeytab("hbase/dfossouo-1.dfossouo.root.hwx.site@ROOT.HWX.SITE",
                    "/tmp/hbase.keytab");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Create a SparkContext to initialize
        String warehouseLocation = new File("spark-warehouse").getAbsolutePath();
        SparkConf conf = new SparkConf();
        conf.setAppName("ECLoad").set("spark.sql.warehouse.dir", warehouseLocation);

        conf.set("spark.testing.memory", "2147480000");         // if you face any memory issue

        System.out.println("Using JDBC Driver");

        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);

        /* DataFrameReader dfphoenix = sqlContext
                .read()
                .format("phoenix")
                .options(ImmutableMap.of("driver", "org.apache.phoenix.jdbc.PhoenixDriver", "zkUrl",
                        "jdbc:phoenix:dfossouo-2.dfossouo.root.hwx.site:2181:/hbase:hbase/dfossouo-1.dfossouo.root.hwx.site@ROOT.HWX.SITE:/tmp/hbase.keytab", "table", "WEB_STAT"))
                .load();

        dfphoenix.createOrReplaceTempView("WEB_STAT");

        dfphoenix = sqlContext.sql("SELECT * FROM TABLE1 WHERE COL1='test_row_1' AND ID=1L");
        dfphoenix.show();
*/
        System.out.println("End of JDBC Driver");

        /*

                readPhoenix.write().format("org.apache.phoenix.spark").mode(SaveMode.Overwrite)
        .options(ImmutableMap.of("driver", "org.apache.phoenix.jdbc.PhoenixDriver","zkUrl",
                "jdbc:phoenix:localhost:2181","table","RESULT"))
        .save();
         */

     /*   System.out.println("Using phoenix Driver");
        Map<String, String> options = new HashMap<String, String>();
        options.put("zkUrl", "dfossouo-2.dfossouo.root.hwx.site:2181:/hbase");
        options.put("table", "WEB_STAT");
        Dataset<Row> df = spark.sqlContext().load("org.apache.phoenix.spark", options);
        df.show();
        System.out.println("End of phoenix Driver");*/





    }
}