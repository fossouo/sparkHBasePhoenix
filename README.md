# sparkHBasePhoenix

1. You need to configure you spark-defaults.conf adding below settings : 

spark.executor.extraClassPath=/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/lib/phoenix-spark-4.14.1-cdh5.16.2.jar:/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/phoenix-4.14.1-cdh5.16.2-client.jar
spark.driver.extraClassPath=/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/lib/phoenix-spark-4.14.1-cdh5.16.2.jar:/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/phoenix-4.14.1-cdh5.16.2-client.jar
export HADOOP_CONF_DIR=/etc/hadoop/conf:/etc/hive/conf:/etc/hbase/conf:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-common-1.2.0-cdh5.16.2.jar:/etc/hbase/conf:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-spark-1.2.0-cdh5.16.2.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-client-1.2.0-cdh5.16.2.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-protocol-1.2.0-cdh5.16.2.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/htrace-core-3.2.0-incubating.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-server-1.2.0-cdh5.16.2.jar 
export SPARK_CLASSPATH=/etc/hadoop/conf:/etc/hive/conf:/etc/hbase/conf:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-common-1.2.0-cdh5.16.2.jar:/etc/hbase/conf:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-spark-1.2.0-cdh5.16.2.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-client-1.2.0-cdh5.16.2.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-protocol-1.2.0-cdh5.10.2.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/htrace-core-3.2.0-incubating.jar:/opt/cloudera/parcels/CDH/lib/hbase/lib/hbase-server-1.2.0-cdh5.16.2.jar 


2. You will have to provide below arguments to your spark-submit : 

[root@dfossouo-1 ~]# spark-submit --class readTable_phoenix --master yarn --deploy-mode client /tmp/enel-1.0-SNAPSHOT.jar --conf "spark.executor.extraClassPath=/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/lib/phoenix-spark-4.14.1-cdh5.16.2.jar:/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/phoenix-4.14.1-cdh5.16.2-client.jar" --conf "spark.driver.extraClassPath=/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/lib/phoenix-spark-4.14.1-cdh5.16.2.jar:/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/phoenix-4.14.1-cdh5.16.2-client.jar" --keytab /tmp/hbase.keytab --principal hbase/dfossouo-1.dfossouo.root.hwx.site@ROOT.HWX.SITE --conf "spark.authenticate=false" --conf "spark.authenticate=false" --conf "spark.shuffle.service.enabled=false" --conf "spark.dynamicAllocation.enabled=false" --conf "spark.network.crypto.enabled=false" --jars /opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/lib/phoenix-spark-4.14.1-cdh5.16.2.jar,/opt/cloudera/parcels/PHOENIX-4.14.1-cdh5.16.2.p0.1216424/lib/phoenix/phoenix-4.14.1-cdh5.16.2-client.jar


3. The result must match something like this : 

19/11/28 00:08:09 INFO Configuration.deprecation: hadoop.native.lib is deprecated. Instead, use io.native.lib.available
Here is the schema of Phoenix Table
root
 |-- HOST: string (nullable = true)
 |-- DOMAIN: string (nullable = true)
 |-- FEATURE: string (nullable = true)
 |-- DATE: date (nullable = true)
 |-- CORE: long (nullable = true)
 |-- DB: long (nullable = true)
 |-- ACTIVE_VISITOR: integer (nullable = true)


19/11/28 00:08:12 INFO scheduler.DAGScheduler: ResultStage 0 (show at readTable_phoenix.java:60) finished in 0.328 s
19/11/28 00:08:12 INFO scheduler.DAGScheduler: Job 0 finished: show at readTable_phoenix.java:60, took 0.416260 s
+----+--------------+---------+----------+----+---+--------------+
|HOST|        DOMAIN|  FEATURE|      DATE|CORE| DB|ACTIVE_VISITOR|
+----+--------------+---------+----------+----+---+--------------+
|  EU|     Apple.com|      Mac|2012-12-31|  35| 22|            34|
|  EU|     Apple.com|    Store|2013-01-02| 345|722|           170|
|  EU|    Google.com|Analytics|2013-01-13|  25|  2|             6|
|  EU|    Google.com|   Search|2013-01-08| 395|922|           190|
|  EU|Salesforce.com|Dashboard|2013-01-05|  12| 22|            43|
|  EU|Salesforce.com|    Login|2013-01-11|   5| 62|           150|
|  EU|Salesforce.com|  Reports|2013-01-02|  25| 11|             2|
|  EU|Salesforce.com|  Reports|2013-01-02| 125|131|            42|
|  EU|Salesforce.com|  Reports|2013-01-04|  75| 22|             3|
|  EU|Salesforce.com|  Reports|2013-01-04| 475|252|            53|
|  EU|Salesforce.com|  Reports|2013-01-13| 355| 52|             5|
|  NA|     Apple.com|    Login|2012-12-31|  35| 22|            40|
|  NA|     Apple.com|    Login|2013-01-03| 135|  2|           110|
|  NA|     Apple.com|      Mac|2013-01-01| 345|255|           155|
|  NA|     Apple.com|      Mac|2013-01-07|   3|  2|            10|
|  NA|     Apple.com|     iPad|2013-01-04|  85|  2|            18|
|  NA|     Apple.com|     iPad|2013-01-05|  35| 22|            10|
|  NA|     Apple.com|     iPad|2013-01-06|   9| 27|             7|
|  NA|    Google.com|Analytics|2013-01-06|  23|  1|            57|
|  NA|    Google.com|Analytics|2013-01-10|   7|  2|             7|
+----+--------------+---------+----------+----+---+--------------+
only showing top 20 rows

