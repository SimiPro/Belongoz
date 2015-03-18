package com.belongo.services.user.services

import javax.persistence.{GeneratedValue, Id, Entity}


import org.springframework.data.repository.CrudRepository


import scala.beans.BeanProperty

/**
 * Created by simipro on 3/13/15.
 */
trait UserRepository extends CrudRepository[User, String]{

  def findByEmail(email:String):User

}

@Entity
class User() {

  @BeanProperty
  @Id
  @GeneratedValue
  var id:String = _

  @BeanProperty
  var email:String = _

  @BeanProperty
  var password:String = _

  @BeanProperty
  var token:String = _
}



