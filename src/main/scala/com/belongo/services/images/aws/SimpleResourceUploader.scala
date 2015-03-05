package com.belongo.services.images.aws

import java.io.{FileOutputStream, BufferedOutputStream, File}

import com.amazonaws.event.{ProgressEvent, ProgressListener}
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.transfer.model.UploadResult
import com.amazonaws.services.s3.{S3ClientOptions, AmazonS3}
import com.amazonaws.services.s3.transfer.TransferManager
import com.belongo.services.images.data.{Picture, PictureDao}
import com.belongo.services.images.user.User
import org.springframework.beans.factory.annotation.{Value, Autowired}
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * Created by simipro on 2/26/15.
 */
@Service
class SimpleResourceUploader @Autowired()(wrapper:AmazonS3Wrapper, repo: PictureDao ) {

  var amazon:AmazonS3 = wrapper.getAmazonS3Client()
  val transferManager = new TransferManager(this.amazon)



  def checkBucket(bucketName:String) = {
    if (transferManager.getAmazonS3Client().doesBucketExist(bucketName) == false) {
      transferManager.getAmazonS3Client().createBucket(bucketName)
    }
  }

  def saveKeyToDB(path:UploadResult) = {
    val pict = new Picture()
    pict.setPath(path.getKey)
    pict.setBucket(path.getBucketName)
    repo.save(pict)
  }


  def getImagesOfUser(user: User): List[Picture] = {
      repo.findByUser(user)
  }

  /**
   * Uploads a file to aws save to db
   *
   *
   * @param user
   * @param file
   * @param fileName
   * @return
   */
  def upload(user:User, file:MultipartFile, fileName:String):String = {
    val bucketName: String = user.bucketName
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
    println("KEY: " + result.getKey + " PATH: " + result.getBucketName + " " + result.getETag)

    saveKeyToDB(result)

    val excp = upload.waitForException()
    if (excp != null) {
      println("ERR: " + excp.getLocalizedMessage)
    }
    "Successfully"
  }

}
