package com.belongo.services.images.aws

import com.belongo.services.images.data.{PictureDao, Picture}
import com.belongo.services.images.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by simipro on 3/12/15.
 */
@Service
class SimpleImageServing @Autowired()(repo: PictureDao){


  def getImagesOfUser(user: User): List[Picture] = {
    repo.findByUser(user)
  }

}
