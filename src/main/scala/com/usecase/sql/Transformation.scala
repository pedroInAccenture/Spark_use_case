package com.usecase.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, lit}

object Transformation {

  def addLiteralColumn(data: DataFrame): DataFrame = {
    data.select(col("*"), lit(1).alias("literal"))
  }

  def createTop(data: DataFrame): DataFrame = {
    data
  }

  
}
