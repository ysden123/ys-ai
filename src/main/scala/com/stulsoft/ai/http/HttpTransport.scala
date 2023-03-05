package com.stulsoft.ai.http

import com.typesafe.scalalogging.LazyLogging
import org.apache.hc.client5.http.classic.methods.{HttpGet, HttpPost}
import org.apache.hc.client5.http.impl.classic.{HttpClientBuilder, HttpClients}
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder
import org.apache.hc.core5.http.{ClassicHttpResponse, HttpEntity}

import scala.util.{Failure, Success, Try, Using}

object HttpTransport extends LazyLogging:
  private def apiKey(): String = System.getenv("OPENAI_API_KEY")

  def get[T](url: String)(f: HttpEntity => T): Try[T] =
    Try {
      Using(HttpClients.createDefault()) {
        httpClient => {
          val httpGet = ClassicRequestBuilder.get(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", s"Bearer ${apiKey()}")
            .build
          httpClient.execute(httpGet, response => {
            response.getCode match {
              case 200 =>
                val entity: HttpEntity = response.getEntity
                val result = f(entity)
                EntityUtils.consume(entity)
                result
              case _ =>
                throw new RuntimeException(response.getReasonPhrase)
            }
          })
        }
      } match {
        case Success(result: T) => result
        case Failure(exception) =>
          logger.error(exception.getMessage, exception)
          throw exception
      }
    }

  def post[T](url: String, payload: String)(f: HttpEntity => T): Try[T] =
    Try {
      Using(HttpClients.createDefault()) {
        httpClient => {
          val httpPost = ClassicRequestBuilder.post(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", s"Bearer ${apiKey()}")
            .setEntity(payload)
            .build

          httpClient.execute(httpPost, response => {
            response.getCode match {
              case 200 =>
                val entity: HttpEntity = response.getEntity
                val result = f(entity)
                EntityUtils.consume(entity)
                result
              case _ =>
                throw new RuntimeException(response.getReasonPhrase)
            }
          })
        }
      } match {
        case Success(result: T) => result
        case Failure(exception) =>
          logger.error(exception.getMessage, exception)
          throw exception
      }
    }