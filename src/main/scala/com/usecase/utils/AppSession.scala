package com.usecase.utils

import com.typesafe.config.Config
import org.apache.log4j.Logger
import org.apache.spark.sql.{DataFrame, SparkSession}

class AppSession {
  /**
   *  Spark settings
   */
  val logger = Logger.getLogger(this.getClass.getName)

  val spark: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("example")
    .getOrCreate()


  /**
   *  PARAMETERS
   */
  val conf:Config = LoadConf.getConfig

  def readParquet(input: String): DataFrame = {
    spark.read.parquet(conf.getString(input))
  }

  def writeParquet(df: DataFrame, output:String) ={
    df.write.mode("overwrite")
//      .partitionBy(partitionBy)
      .parquet(output)
  }
}
