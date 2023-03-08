package com.stulsoft.ai.completion.text

import org.json4s.*
import org.json4s.jackson.Serialization.*

class CreateCompletionRequest(val model: String,
                              val prompt: String) {
  private var suffix: Option[String] = Option.empty
  private var max_tokens: Option[Int] = Option.empty
  private var temperature: Option[Double] = Option.empty
  private var top_p: Option[Double] = Option.empty
  private var n: Option[Int] = Option.empty
  private var stream: Option[Boolean] = Option.empty
  private var logprobs: Option[Int] = Option.empty
  private var echo: Option[Boolean] = Option.empty
  private var stop: Option[String] = Option.empty
  private var presence_penalty: Option[Double] = Option.empty
  private var frequency_penalty: Option[Double] = Option.empty
  private var best_of: Option[Int] = Option.empty
  private var user: Option[String] = Option.empty

  def withSuffix(suffix: String): CreateCompletionRequest =
    this.suffix = Option(suffix)
    this

  def withMax_tokens(max_tokens: Int): CreateCompletionRequest =
    this.max_tokens = Option(max_tokens)
    this

  def withTemperature(temperature: Double): CreateCompletionRequest =
    this.temperature = Option(temperature)
    this

  def withTop_p(top_p: Double): CreateCompletionRequest =
    this.top_p = Option(top_p)
    this

  def withN(n: Int): CreateCompletionRequest =
    this.n = Option(n)
    this

  def withStream(stream: Boolean): CreateCompletionRequest =
    this.stream = Option(stream)
    this

  def withLogprobs(logprobs: Int): CreateCompletionRequest =
    this.logprobs = Option(logprobs)
    this

  def withEcho(echo: Boolean): CreateCompletionRequest =
    this.echo = Option(echo)
    this

  def withStop(stop: String): CreateCompletionRequest =
    this.stop = Option(stop)
    this

  def withPresence_penalty(presence_penalty: Double): CreateCompletionRequest =
    this.presence_penalty = Option(presence_penalty)
    this

  def withFrequency_penalty(frequency_penalty: Double): CreateCompletionRequest =
    this.frequency_penalty = Option(frequency_penalty)
    this

  def withBest_of(best_of: Int): CreateCompletionRequest =
    this.best_of = Option(best_of)
    this

  def withUser(user: String): CreateCompletionRequest =
    this.user = Option(user)
    this

  def toJson(): String =
    val buffer = StringBuilder("{")

    def addValue[T](value: Option[T], name: String): Unit =
      value match
        case Some(s: String) =>
          buffer.append(s""","$name":"$s"""")
        case Some(v) =>
          buffer.append(s""","$name":$v""")
        case _ =>

    buffer.append(s""""model":"$model"""")
    buffer.append(s""","prompt":"$prompt"""")
    addValue(suffix, "suffix")
    addValue(max_tokens, "max_tokens")
    addValue(temperature, "temperature")
    addValue(top_p, "top_p")
    addValue(n, "n")
    addValue(stream, "stream")
    addValue(logprobs, "logprobs")
    addValue(echo, "echo")
    addValue(presence_penalty, "presence_penalty")
    addValue(frequency_penalty, "frequency_penalty")
    addValue(best_of, "best_of")
    addValue(user, "user")

    buffer.append("}").toString()


  override def toString: String =
    val buffer = StringBuilder("CreateCompletionRequest(")

    def addValue[T](value: Option[T], name: String): Unit =
      value match
        case Some(s: String) =>
          buffer.append(s""", $name: "$s"""")
        case Some(v) =>
          buffer.append(s""", $name: $v""")
        case _ =>

    buffer.append(s"""model: "$model"""")
    buffer.append(s""", prompt: "$prompt"""")
    addValue(suffix, "suffix")
    addValue(max_tokens, "max_tokens")
    addValue(temperature, "temperature")
    addValue(top_p, "top_p")
    addValue(n, "n")
    addValue(stream, "stream")
    addValue(logprobs, "logprobs")
    addValue(echo, "echo")
    addValue(stop, "stop")
    addValue(presence_penalty, "presence_penalty")
    addValue(frequency_penalty, "frequency_penalty")
    addValue(best_of, "best_of")
    addValue(user, "user")

    buffer.append(")").toString()
}