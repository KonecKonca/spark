package com.kozitski.spark

object SparkTask1 extends App {

  val spark = org.apache.spark.sql.SparkSession.builder
    .master("local")
    .appName("Spark CSV Reader")
    .getOrCreate

  val loadedData = spark.read
    .format("csv")
    .option("header", "true")
    .option("mode", "DROPMALFORMED")
    .load("D:\\andreiWorkDir\\projects\\101data\\6_spark\\mavenExample\\fileName.csv")

  loadedData.printSchema()

  loadedData.rdd
            .filter(row => row.getAs[String](14) != null && row.getAs[String](14).equals("2"))
            .map(row => ((row.getAs[String](19), row.getAs[String](20), row.getAs[String](21)), 1))
            .reduceByKey(_ + _)
            .sortBy(tuple => -tuple._2)
            .take(3)
            .foreach(println)

}
