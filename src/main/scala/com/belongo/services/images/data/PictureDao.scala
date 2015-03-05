package com.belongo.services.images.data

import com.belongo.services.images.user.User

/**
 * Created by simipro on 2/28/15.
 */
trait PictureDao {
  def save(picture: Picture): Unit
  def find(id:Long):Option[Picture]
  def findAll():List[Picture]
  def findByUser(user:User):List[Picture]
  def findByPath(path:String):Option[Picture]
}
