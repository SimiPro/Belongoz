package com.belongo.services.images.data

import javax.persistence.EntityManager
import javax.transaction.Transactional
import com.belongo.services.images.user.User

import scala.collection.JavaConversions._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

/**
 * Created by simipro on 2/28/15.
 */
@Repository("pictureDao")
@Transactional
class PictureDaoImpl extends PictureDao {
  @Autowired
  var entityManager:EntityManager = _

  override def save(picture: Picture): Unit = picture.id match {
    case 0 => entityManager.persist(picture)
    case _ => entityManager.merge(picture)

  }

  override def findByPath(path:String): Option[Picture] = {
    Option(
    entityManager.createQuery("SELECT p From Picture p WHERE p.path = :path", classOf[Picture])
      .setParameter("path", path)
      .getSingleResult
    )
  }

  override def find(id: Long): Option[Picture] = {
    Option(entityManager.createQuery("From Picture", classOf[Picture]).getSingleResult)
  }

  override def findAll(): List[Picture] = {
    entityManager.createQuery("SELECT p from Picture", classOf[Picture]).getResultList.toList
  }

  override def findByUser(user:User): List[Picture] = {
    entityManager.createQuery("SELECT p from Picture p WHERE p.bucket = :bucket", classOf[Picture])
      .setParameter("bucket",user.bucketName).getResultList.toList

  }
}
