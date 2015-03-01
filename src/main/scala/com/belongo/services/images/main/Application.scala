package com.belongo.services.images.main

import com.belongo.services.images.WebConfig
import org.springframework.boot.SpringApplication

object Application {

  def main(args: Array[String]) {
    SpringApplication.run(classOf[WebConfig], args:_*)
  }
}