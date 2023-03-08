package com.stulsoft.ai.completion.text

import org.apache.hc.core5.http.io.entity.{BasicHttpEntity, EntityUtils}
import org.apache.hc.core5.http.{ContentType, Header, HttpEntity}
import org.scalatest.funsuite.AnyFunSuite

import java.io.{InputStream, OutputStream}
import java.util
import scala.io.Source

class CompletionTest extends AnyFunSuite:
  test("jsonToChoice") {
    try {
      val json = Source.fromResource("choice.json").getLines().mkString("\n")
      val choice = Completion.jsonToChoice(json)
      println(choice)
    } catch
      case exception: Exception =>
        exception.printStackTrace()
  }

  test("entityToCompletion") {
    try {
      val inputStream: InputStream = getClass.getClassLoader.getResourceAsStream("completionResponse.json")
      val httpEntity: HttpEntity = new BasicHttpEntity(inputStream, ContentType.APPLICATION_JSON)

      val completion = Completion.entityToCompletion(httpEntity)
      println(completion)

      EntityUtils.consume(httpEntity)
    } catch
      case exception: Exception =>
        exception.printStackTrace()
  }
