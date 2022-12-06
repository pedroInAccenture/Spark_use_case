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

//val spark = SparkSession.builder()
//  .appName("Spark academy")
//  .config("spark.sql.warehouse.dir", "spark-warehouse")
//  .enableHiveSupport()
//  .getOrCreate()

  spark.conf.set("spark.sql.sources.partitionOverWriteMode", "dynamic")
  spark.conf.set("spark.yarn.nodemanager.vmem-check-enabled", "false")

  /**
   *  PARAMETERS
   */
  val conf:Config = LoadConf.getConfig

  def readParquet(input: String): DataFrame = {
    println(">>>>>" + conf)
    println(">>>>>" + conf.getString(input))
    spark.read.parquet(conf.getString(input))
  }

  def writeParquet(df: DataFrame, output:String) ={
    println(">>>>>" + conf.getString(output))
    df.write.mode("overwrite")
//      .partitionBy(partitionBy)
      .parquet(conf.getString(output))
  }
}
