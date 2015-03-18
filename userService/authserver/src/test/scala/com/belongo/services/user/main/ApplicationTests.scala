package com.belongo.services.user.main


import com.belongo.services.user.OAuthConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.{TestRestTemplate, IntegrationTest, SpringApplicationConfiguration}
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate
import org.junit.Assert._

/**
 * Created by simipro on 3/13/15.
 */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[OAuthConfig]))
@WebAppConfiguration
@IntegrationTest(Array("server.port:0"))
class ApplicationTests {

  var template:RestTemplate = new TestRestTemplate()

  @Value("${local.server.port}")
  var port:Int = _

  @Value("${server.contextPath}")
  var contextpath:String = _

  @Test
  def homePageProtected():Unit = {
    val response = template.getForEntity("http://localhost:" + port + contextpath, classOf[String])
    println("http://localhost:" + port + contextpath)
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode())
  }

  @Test
  def userEndpointProtected(): Unit = {
    val response = template.getForEntity("http://localhost:" + port + contextpath + "/user", classOf[String])
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode)
    /*
    val auth = response.getHeaders.getFirst("WWW-Authenticate")
    assertTrue("Wrong header: " + auth, auth.startsWith("Basic realm=\""))
    */
  }

  @Test
  def authorizationRedirects(): Unit = {
    val response = template.getForEntity("http://localhost:" + port + contextpath + "/oauth/authorize", classOf[String])
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode)
    val auth = response.getHeaders.getFirst("WWW-Authenticate")
    assertTrue("Wrong header: " + auth, auth.startsWith("Basic realm=\""))

  }

}
