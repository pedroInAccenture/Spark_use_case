package com.usecase

import com.usecase.sql.Transformation
import com.usecase.utils.AppSession

object AppUseCase extends AppSession {

  def run(): Unit = {

    /**
     * INPUTS
     */
    logger.info("=====> Reading file")

    val dfAthletes = readParquet("input.pathAthletes")


    /**
     * TRANSFORMATIONS
     */
    logger.info("=====> Transforming data")
    val dfAthletesWithExtraColumn = Transformation.addLiteralColumn(dfAthletes)


    /**
     * OUTPUT
     */

    writeParquet(dfAthletesWithExtraColumn, "output.path")
    spark.stop()
    logger.info("=====> end process")

  }
}
