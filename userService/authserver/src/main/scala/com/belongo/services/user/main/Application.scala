package com.belongo.services.user.main

import com.belongo.services.user.OAuthConfig
import org.springframework.boot.SpringApplication


object Application {

  def main(args: Array[String]) {
    SpringApplication.run(classOf[OAuthConfig], args:_*)
  }

}