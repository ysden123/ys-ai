package com.stulsoft.ai.completion.text

import com.stulsoft.ai.http.HttpTransport
import com.typesafe.scalalogging.LazyLogging
import org.apache.hc.core5.http.io.entity.EntityUtils

import scala.util.{Failure, Success, Try}

object CompletionService extends LazyLogging:
  def getCompletion(): Try[Completion] =
    logger.info("==>")
//    val request = CreateCompletionRequest("text-davinci-003", "Say this is a test")
//    val request = CreateCompletionRequest("text-davinci-003", "What resources about Scala you can recommend?")
    val request = CreateCompletionRequest("text-davinci-003", "Please translate next phrase to English: Я люблю программирование")
      .withTemperature(0.5)
      .withMax_tokens(5)
    HttpTransport.post("https://api.openai.com/v1/completions", request.toJson())(httpEntity => {
      val result = Completion.entityToCompletion(httpEntity)
      EntityUtils.consume(httpEntity)
      result
    })

  def main(args: Array[String]): Unit = {
    logger.info("==>main")
    getCompletion() match
      case Success(completion) =>
//        println(s"completion: $completion")
        completion.choices.foreach(choice=> println(s"(${choice.index}):  ${choice.text}"))
      case Failure(exception) =>
        println(exception.getMessage)
  }
