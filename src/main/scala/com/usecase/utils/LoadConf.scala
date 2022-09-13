package com.usecase.utils

import com.typesafe.config.{Config, ConfigFactory}

object LoadConf {

  def input():Config = getConfig.getConfig("input")

  def output():Config = getConfig.getConfig("output")

  def getConfig:Config = ConfigFactory.load("config/application.conf").getConfig("app")

}
