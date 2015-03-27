package com.belongo.services.ios.endpoints

import java.io.{File, FileOutputStream, FileInputStream, OutputStream}

import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestBody, ResponseBody, RequestMethod, RequestMapping}
import org.springframework.web.multipart.MultipartFile

/**
 * Created by simipro on 27/03/15.
 */
@Controller
@RequestMapping(value = Array("/ios/image"))
@EnableOAuth2Resource
class ImageUpload {

  @RequestMapping(value = Array("/upload"), method = Array(RequestMethod.POST))
  @ResponseBody
  def upload(@RequestBody file:MultipartFile): String = {
    println("JEPEEE UPLOADED!!")
    var filez = new File(file.getName)
    var stream = new FileOutputStream("testli.png")
    stream.write(file.getBytes)
    stream.close()
    "Im Secured"
  }


  @RequestMapping(value = Array("/test"), method = Array(RequestMethod.GET))
  @ResponseBody
  def test(): String = {
    "IT WORKS"
  }



}
