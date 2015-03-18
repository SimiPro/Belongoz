package com.belongo.services.images.data

import javax.persistence.{EntityManager, Entity, GeneratedValue, Id}
import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import scala.beans.BeanProperty


/**
 * Created by simipro on 3/9/15.
 */
@Entity
@SerialVersionUID(101L)
class Localisation extends Serializable {

  //TODO: Way to small need to change to UUID
  @Id
  @GeneratedValue
  @BeanProperty
  var id:Long = _

  @BeanProperty
  var lat:Double= _

  @BeanProperty
  var lon:Double = _

}

trait LocalisationDao {
  def save(localisation: Localisation)
}

@Repository("localDao")
@Transactional
class LocalisationDaoImpl extends LocalisationDao {
  @Autowired
  var entityManager:EntityManager = _

  override def save(local: Localisation): Unit = local.id match {
    case 0 => entityManager.persist(local)
    case _ => entityManager.merge(local)
  }
}

class LocalisationBuilder() {

  var lat:Double = _
  var lon:Double = _

  def setLat(lat:Double):LocalisationBuilder = {
    this.lat = lat
    this
  }

  def setLon(lon:Double):LocalisationBuilder = {
    this.lon = lon
    this
  }

  def build():Localisation = {
    val local:Localisation = new Localisation()
    local.lat = this.lat
    local.lon = this.lon
    local
  }

}
