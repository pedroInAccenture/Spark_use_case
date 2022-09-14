package com.usecase.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, lit}

object Analytic {

  def addLiteralColumn(data: DataFrame): DataFrame = {
    data.select(col("*"), lit(1).alias("literal"))
  }
2
  def createTop(data: DataFrame): DataFrame = {
    data
  }

  0
}
