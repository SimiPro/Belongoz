package com.belongo.services.user.services

import java.util
import org.springframework.stereotype.Component

import scala.collection.JavaConversions._

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService}

/**
 * Created by simipro on 3/13/15.
 */
@Component
class UserService extends UserDetailsService {

  @Autowired
  var repo:UserRepository = _

  override def loadUserByUsername(s: String): UserDetails = {
    new BelongoUser(repo.findByEmail(s))
  }
}

class BelongoUser(user: User) extends UserDetails {
  override def getAuthorities: util.Collection[_ <: GrantedAuthority] = List(new Authorithies)

  override def isEnabled: Boolean = true
  override def getPassword: String = user.password
  override def isAccountNonExpired: Boolean = true
  override def isCredentialsNonExpired: Boolean = true
  override def isAccountNonLocked: Boolean = true
  override def getUsername: String = user.email
}

class Authorithies extends GrantedAuthority {
  override def getAuthority: String = {
    "read_write"
  }
}
