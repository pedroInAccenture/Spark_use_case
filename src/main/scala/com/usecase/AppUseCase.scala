package com.usecase

import com.typesafe.config.Config
import com.usecase.utils.LoadConf
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lit}

object AppUseCase {

  def run(): Unit ={
    /**
     * Spark settings
     */
    val logger = Logger.getLogger(this.getClass.getName)

    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("example")
      .getOrCreate()

    /**
     * PARAMETERS
     */
    val conf: Config = LoadConf.getConfig


    /**
     * INPUTS
     */
    logger.info("=====> Reading file")

    val dfAthletes = spark.read
      .parquet(conf.getString("input.pathAthletes"))


    /**
     * TRANSFORMATIONS
     */
    logger.info("=====> Transforming table")
    val dfTransformed = dfAthletes.select(col("*"), lit(1).alias("literal"))


    /**
     * OUTPUT
     */
    logger.info("=====> Writing file")
    dfTransformed.write
      .mode("overwrite")
      .parquet(conf.getString("output.path"))


    logger.info("=====> end process")
    logger.warn("=====> sleeping")


    /**
     * OUTPUT
     */
    logger.info("=====> Writing file")

  }
}
