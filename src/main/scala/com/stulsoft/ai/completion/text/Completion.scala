package com.stulsoft.ai.completion.text

import com.stulsoft.ai.model.Model
import org.apache.hc.core5.http.HttpEntity
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.json4s.*
import org.json4s.FieldSerializer.{ignore, renameFrom, renameTo}
import org.json4s.jackson.Serialization.read

case class Choice(text: String, index: Int, finish_reason: String)

case class Usage(prompt_tokens: Int, completion_tokens: Int, total_tokens: Int)

case class Completion(id: String,
                      theObject: String,
                      created: Long,
                      model: String,
                      choices: List[Choice],
                      usage: Usage
                     )

object Completion:
  private val rename4Completion = FieldSerializer[Completion](renameTo("theObject", "object"),
    renameFrom("object", "theObject"))

  private val ignore4Choice = FieldSerializer[Completion](ignore("logprobs"))

  given formats: Formats = DefaultFormats + ignore4Choice + rename4Completion

  def entityToCompletion(entity: HttpEntity): Completion =
    read[Completion](EntityUtils.toString(entity))

  def jsonToChoice(json: String): Choice =
    read[Choice](json)


