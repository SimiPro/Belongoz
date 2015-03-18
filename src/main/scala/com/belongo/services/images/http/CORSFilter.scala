package com.belongo.services.images.http

import javax.servlet._
import javax.servlet.http.HttpServletResponse

import org.springframework.stereotype.Component

/**
 * Created by simipro on 3/9/15.
 */
@Component
class CORSFilter extends Filter{
  override def init(filterConfig: FilterConfig): Unit = {

  }

  override def doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain): Unit = {
      val response = res.asInstanceOf[HttpServletResponse]
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
      response.setHeader("Access-Control-Max-Age", "3600");
      response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with");
      chain.doFilter(req, res);
  }

  override def destroy(): Unit = {

  }
}
