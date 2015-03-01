package com.belongo.services.images.data

import java.util.UUID
import javax.persistence.{Id, GeneratedValue, Entity}

import scala.beans.BeanProperty

/**
 * Created by simipro on 2/28/15.
 */
@Entity
@SerialVersionUID(100L)
class Picture extends Serializable {

  //TODO: Way to small need to change to UUID
  @Id
  @GeneratedValue
  @BeanProperty
  var id:Long = _

  @BeanProperty
  var userId:Long = _

  @BeanProperty
  var path: String  = _

}
