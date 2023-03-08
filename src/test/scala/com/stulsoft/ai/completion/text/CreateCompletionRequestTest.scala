package com.stulsoft.ai.completion.text

import org.scalatest.funsuite.AnyFunSuite

class CreateCompletionRequestTest extends AnyFunSuite:
  test("default constructor") {
    var request = CreateCompletionRequest(model = "test", prompt = "test prompt")
    println(request)

    request = CreateCompletionRequest(model = "test", prompt = "test prompt").withSuffix("the suffix")
    println(request)

    request = CreateCompletionRequest(model = "test", prompt = "test prompt")
      .withSuffix("the suffix") withN 321 withEcho true
    println(request)
  }

  test("toJson") {
    var request = CreateCompletionRequest(model = "test", prompt = "test prompt")
    println(request.toJson())
    request = CreateCompletionRequest(model = "test", prompt = "test prompt")
      .withSuffix("the suffix")
      .withEcho(true)
      .withStop("stop value")
      .withTop_p(1)
      .withN(456)
    println(request.toJson())
  }
