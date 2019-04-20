package com.kozitski.spark

object SparkTask2 extends App {

  val spark = org.apache.spark.sql.SparkSession.builder
    .master("local")
    .appName("Spark CSV Reader")
    .getOrCreate

  val loadedData = spark.read
    .format("csv")
    .option("header", "true")
    .option("mode", "DROPMALFORMED")
    .load("D:\\andreiWorkDir\\projects\\101data\\6_spark\\mavenExample\\fileName.csv")

  loadedData.rdd
            .filter(row =>  {
              val userLocationCountry = row.getAs[String](4)
              val hotelCountry = row.getAs[String](19)

              userLocationCountry != null && hotelCountry != null && userLocationCountry.equals(hotelCountry)
            })
            .map(row => (row.getAs[String](19), 1))
            .reduceByKey(_ + _)
            .sortBy(tuple => -tuple._2)
            .take(1)
            .foreach(println)

}
