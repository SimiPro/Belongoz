package com.belongo.services.images

import com.belongo.services.images.config.ScalaObjectMapper
import com.belongo.services.images.http.CORSFilter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.embedded.FilterRegistrationBean
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

  @Bean
  def corsFilterChain(@Autowired() cors:CORSFilter):FilterRegistrationBean = {
    // set cors header before filter chain else we cant handle a failed response on client side
    val registration = new FilterRegistrationBean(cors)
    registration.setOrder(0);
    registration.setName("CORSFilterChain")
    registration
  }

}
