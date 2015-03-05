package com.belongo.services.images.aws

import javax.annotation.PostConstruct

import com.amazonaws.services.s3.{S3ClientOptions, AmazonS3}
import org.slf4j.{LoggerFactory, Logger}
import org.springframework.beans.factory.annotation.{Value, Autowired}
import org.springframework.stereotype.Service


/**
 * Created by simipro on 3/3/15.
 */
@Service
class AmazonS3Wrapper {
  @Autowired
  var amazonS3:AmazonS3 =  _

  @Value(value = "${aws.development}")
  var dev:Boolean = _

  var log:Logger = LoggerFactory.getLogger(classOf[AmazonS3Wrapper])


  @PostConstruct
  def init(): Unit = {
    if (dev) {
      log.info("We initialized amazon for local/offline development")
      this.amazonS3.setEndpoint("http://localhost:4567")
      this.amazonS3.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true))
    }
  }

  def getAmazonS3Client() : AmazonS3 =  {
    amazonS3
  }


}
