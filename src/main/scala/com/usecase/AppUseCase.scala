package com.usecase

import com.usecase.sql.Analytic
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
    val dfAthletesWithExtraColumn = Analytic.addLiteralColumn(dfAthletes)


    /**
     * OUTPUT
     */

    writeParquet(dfAthletesWithExtraColumn, "output.path")

    logger.info("=====> end process")

  }
}
