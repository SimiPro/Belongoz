package com.belongo.services.images.controller


import java.security.Principal

import com.belongo.services.images.aws.{SimpleImageServing, SimpleResourceUploader}
import com.belongo.services.images.data.{LocalisationBuilder, Localisation, Picture}
import com.belongo.services.images.user.{User, UserService}
import org.springframework.beans.factory.annotation.{ Autowired}
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile

/**
 * Created by simipro on 2/26/15.
 */
@RestController
@RequestMapping(Array("/image"))
@EnableOAuth2Resource
class ImageCntrl {

  @Autowired
  var serving:SimpleImageServing = _

  @Autowired
  var resource:SimpleResourceUploader = _


  @RequestMapping(value = Array("/upload"), method = Array(RequestMethod.GET))
  @ResponseBody
  def upload(@RequestParam("file") file:MultipartFile):String = {
    println("FILENAME:::: " + file.getName)
    return "You can upload a file posting to the same url different request method"
  }



  @RequestMapping(value = Array("/upload"), method = Array(RequestMethod.POST))
  @ResponseBody
  def upload(
              @RequestParam("name") name:String,
              @RequestParam("file") file:MultipartFile,
              @RequestParam("lat")  lat:Double,
              @RequestParam("lon")  lon:Double,
              userz:Principal) : String = {
    println(userz.toString)
    val localisation:Localisation = new LocalisationBuilder()
      .setLat(lat)
      .setLon(lon)
      .build()
    val user:User = UserService.getActualUserByToken(getActualToken())
    // this should be id of the user mby?
    resource.upload(user, file, localisation)
    "succesfully uploaded file"
  }

  def getActualToken(): String = {
    "notoken"
  }

  @ResponseBody
  @RequestMapping(value = Array("/user"), method = Array(RequestMethod.GET))
  def allImages(@RequestParam("token") token:String): List[PictureRequest] = {
    val user:User = UserService.getActualUserByToken(token)
    val images = resource.getImagesOfUser(user)
    val response = List()
    images.foreach(P => {
        response :+ new PictureRequest(P.getPath)
    })
    response
  }


}

case class PictureRequest(source:String)
