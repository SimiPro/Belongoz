package com.belongo.services.user


import javax.servlet.{Filter, FilterRegistration}

import com.belongo.services.user.config.CORSFilter
import org.springframework.beans.factory.annotation.{Qualifier, Autowired}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.embedded.FilterRegistrationBean
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

  @Bean
  def corsFilterChain(@Autowired() cors:CORSFilter):FilterRegistrationBean = {
    // set cors header before filter chain else we cant handle a failed response on client side
    val registration = new FilterRegistrationBean(cors)
    registration.setOrder(0);
    registration.setName("CORSFilterChain")
    registration
  }


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
    .and()
      .withClient("ios")
      .secret("simiproo")
      .authorizedGrantTypes("authorization_code", "refresh_token","password")
      .scopes("ios")

  }
}
