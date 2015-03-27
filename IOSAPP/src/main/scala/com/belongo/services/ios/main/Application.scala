package com.belongo.services.ios.main

import com.belongo.services.ios.config.AppConfig
import org.springframework.boot.SpringApplication

object Application {

  def main(args: Array[String]) {
    SpringApplication.run(classOf[AppConfig], args:_*)
  }


}