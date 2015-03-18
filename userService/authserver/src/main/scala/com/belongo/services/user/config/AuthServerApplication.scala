package com.belongo.services.user.config

import java.security.Principal


import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.web.bind.annotation.{RestController, RequestMapping}

/**
 * Created by simipro on 3/13/15.
 */
@EnableResourceServer
@RestController
class AuthServerApplication {

  @RequestMapping(Array("/user"))
  def user(user:Principal):Principal = {
    println(user.toString)
    user
  }

}
