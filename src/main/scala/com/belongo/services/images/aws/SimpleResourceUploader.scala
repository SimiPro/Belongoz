package com.belongo.services.images.aws

import java.io.{FileOutputStream, BufferedOutputStream, File}

import com.amazonaws.event.{ProgressEvent, ProgressListener}
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.{S3ClientOptions, AmazonS3}
import com.amazonaws.services.s3.transfer.TransferManager
import com.belongo.services.images.data.{Picture, PictureDao}
import com.belongo.services.images.user.User
import org.springframework.beans.factory.annotation.{Value, Autowired}
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * Created by simipro on 2/26/15.
 */
@Service
class SimpleResourceUploader @Autowired()(amazon:AmazonS3, repo: PictureDao ) {



  @Value(value = "${aws.development}")
  var dev:Boolean = _

  val transferManager = new TransferManager(this.amazon)


  if (dev) {
    this.amazon.setEndpoint("http://localhost:4567")
    this.amazon.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true))
  }


  def checkBucket(bucketName:String) = {
    if (transferManager.getAmazonS3Client().doesBucketExist(bucketName) == false) {
      transferManager.getAmazonS3Client().createBucket(bucketName)
    }
  }

  def saveKeyToDB(path:String) = {
    val pict = new Picture()
    pict.setPath(path)
    repo.save(pict)
  }


  def getImagesOfUser(user: User): List[Picture] = {
    repo.findByPath()
  }

  /**
   * Uploads a file to aws save to db
   *
   *
   * @param bucketName
   * @param file
   * @param fileName
   * @return
   */
  def upload(bucketName:String, file:MultipartFile, fileName:String):String = {
    checkBucket(bucketName)

    val realFile = new File(fileName)
    val stream = new BufferedOutputStream(new FileOutputStream(realFile))
    stream.write(file.getBytes)
    stream.close()

    println(realFile.getAbsoluteFile)


    val request = new PutObjectRequest(bucketName, realFile.getName, realFile)
    val upload = transferManager.upload(request)
    upload.addProgressListener(new ProgressListener() {
      override def progressChanged(progressEvent: ProgressEvent): Unit = {
        println("New Progress: " + progressEvent.getBytesTransferred())
        println("Progress: " + upload.getProgress.getPercentTransferred + "%")
      }
    })

    upload.waitForCompletion()
    val result = upload.waitForUploadResult()
    println("KEY: " + result.getKey)

    saveKeyToDB(result.getKey)

    val excp = upload.waitForException()
    if (excp != null) {
      println("ERR: " + excp.getLocalizedMessage)
    }
    "Successfully"
  }

}
