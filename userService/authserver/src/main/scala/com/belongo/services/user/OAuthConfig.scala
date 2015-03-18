package com.belongo.services.user


import org.springframework.beans.factory.annotation.{Autowired}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.security.authentication.AuthenticationManager

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.{AuthorizationServerConfigurerAdapter, EnableAuthorizationServer}
import org.springframework.security.oauth2.config.annotation.web.configurers.{AuthorizationServerSecurityConfigurer, AuthorizationServerEndpointsConfigurer}


@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableAuthorizationServer
class OAuthConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  var authManager:AuthenticationManager = _


  override def configure(security:AuthorizationServerSecurityConfigurer) = {

  }

  override def configure(endpoints:AuthorizationServerEndpointsConfigurer) = {
    endpoints.authenticationManager(authManager)
  }
  override def configure(clients:ClientDetailsServiceConfigurer) = {
    clients.inMemory()
      .withClient("curl")
      .secret("curl")
      .authorizedGrantTypes("authorization_code", "refresh_token", "password")
      .scopes("belongo")
  }
}
