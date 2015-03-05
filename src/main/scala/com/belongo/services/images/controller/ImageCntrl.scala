package com.belongo.services.images.controller


import com.belongo.services.images.aws.SimpleResourceUploader
import com.belongo.services.images.data.Picture
import com.belongo.services.images.user.{User, UserService}
import org.springframework.beans.factory.annotation.{ Autowired}
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile

/**
 * Created by simipro on 2/26/15.
 */
@RestController
@RequestMapping(Array("/image"))
class ImageCntrl {

  @Autowired
  var resource:SimpleResourceUploader = _


  @RequestMapping(value = Array("/upload"), method = Array(RequestMethod.GET))
  @ResponseBody
  def upload():String = {
    return "You can upload a file posting to the same url different request method"
  }



  @RequestMapping(value = Array("/upload"), method = Array(RequestMethod.POST))
  @ResponseBody
  def upload(@RequestParam("name") name:String, @RequestParam("file") file:MultipartFile) : String = {
    val user:User = UserService.getActualUserByToken(getActualToken())
    // this should be id of the user mby?
    resource.upload(user, file, name)
    "succesfully uploaded file"
  }

  def getActualToken(): String = {
    "notoken"
  }

  @ResponseBody
  @RequestMapping(value = Array("/user"), method = Array(RequestMethod.GET))
  def allImages(): List[Picture] = {
    val user:User = UserService.getActualUserByToken(getActualToken())
    resource.getImagesOfUser(user)
  }


}
