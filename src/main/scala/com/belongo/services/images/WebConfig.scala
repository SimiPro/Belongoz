package com.belongo.services.images

import com.belongo.services.images.config.ScalaObjectMapper
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, Primary}

/**
 * Created by simipro on 2/26/15.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
class WebConfig {

  /*
This mapper does all the magic to convert scala case classes to json and back :)
 */
  @Bean
  @Primary
  def scalaObjectMapper() = new ScalaObjectMapper

}
