package com.usecase.utils

import com.typesafe.config.{Config, ConfigFactory}

object LoadConf {

  def getConfig:Config = ConfigFactory.load("config/application.conf").getConfig(System.getProperty("env"))

  def input():Config = getConfig.getConfig("input")

  def output():Config = getConfig.getConfig("output")

}
