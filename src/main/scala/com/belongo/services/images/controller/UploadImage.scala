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
class UploadImage {

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
    // this should be id of the user mby?
    val bucketName = "simisbucked"
    resource.upload(bucketName, file, name)
    "succesfully uploaded file"
  }

  @ResponseBody
  def allImages(): List[Picture] = {
    val user:User = UserService.getActualUserByToken("notoken")
    resource.getImagesOfUser(user)
  }


}
