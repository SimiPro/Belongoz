package com.belongo.services.images.user

/**
 * Created by simipro on 3/1/15.
 */
class UserService {

}

object UserService {
  def getActualUserByToken(token:String): User = {
      new User(0, "email", "pw", "dummybucked2")
  }
}
