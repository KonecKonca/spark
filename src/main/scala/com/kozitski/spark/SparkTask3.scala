package com.kozitski.spark

object SparkTask3 extends App {

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
    .filter(row => {
        val isBooking = row.getAs[String](FieldNumber.IS_BOOKING.getTrain)
        val srchAdultsCnt = row.getAs[String](FieldNumber.SRCH_ADULTS_CNT.getTrain)
        val srchChildrenCnt = row.getAs[String](FieldNumber.SRCH_CHILDREN_CNT.getTrain)


      isBooking != null && srchAdultsCnt != null && srchChildrenCnt != null && isBooking.equals("0") &&
      Integer.parseInt(srchAdultsCnt) > 1 && Integer.parseInt(srchChildrenCnt) > 1
    })
    .map(row => (
                  (
                    row.getAs[String](FieldNumber.HOTEL_CONTINENT.getTrain),
                    row.getAs[String](FieldNumber.HOTEL_COUNTRY.getTrain),
                    row.getAs[String](FieldNumber.HOTEL_MARKET.getTrain)
                  ),
                  1)
                )
    .reduceByKey(_ + _)
    .sortBy(tuple => -tuple._2)
    .take(3)
    .foreach(println)

}
