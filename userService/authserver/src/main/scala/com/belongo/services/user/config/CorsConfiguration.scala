package com.belongo.services.user.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Created by simipro on 3/18/15.
 */
// TODO: DELETE THIS IN PRODUCTION DAMNT ANGULARJS AND THE NEW BROWSER WITH THEIR GAY OPTION REQUEST BEFORE THE REAL ONE'll COMES
@Configuration
@Order(-1)
class CorsConfiguration extends WebSecurityConfigurerAdapter {

  override def configure(http:HttpSecurity) = {
    http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/token", "/userservice/**")
      .and().csrf().disable()
      .authorizeRequests().anyRequest().permitAll()
  }
}
