package com.belongo.services.images.config

/**
 * Created by simipro on 2/26/15.
 */
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * Created by Simon on 20.02.2015.
 */
class ScalaObjectMapper extends ObjectMapper {
  registerModule(DefaultScalaModule)

}
