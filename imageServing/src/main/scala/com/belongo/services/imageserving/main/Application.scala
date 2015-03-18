package com.belongo.services.imageserving.main


import com.belongo.services.imageserving.WebConfig
import org.springframework.boot.SpringApplication

object Application {

  def main (args: Array[String] ) {
    SpringApplication.run(classOf[WebConfig], args:_*)
  }

}

