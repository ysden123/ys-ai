package com.stulsoft.ai.model

import org.json4s.*
import org.json4s.jackson.JsonMethods.*
import org.json4s.FieldSerializer.*
import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class ModelTest extends AnyFunSuite:
  test("parsing") {
    val modelText: String =
      """
        |{
        |    "id": "gpt-3.5-turbo",
        |    "object": "model",
        |    "created": 1677610602,
        |    "owned_by": "openai",
        |    "permission": [
        |      {
        |        "id": "modelperm-ZErASyl63fhYUeMMk7QKOHAB",
        |        "object": "model_permission",
        |        "created": 1677691854,
        |        "allow_create_engine": false,
        |        "allow_sampling": true,
        |        "allow_logprobs": true,
        |        "allow_search_indices": false,
        |        "allow_view": true,
        |        "allow_fine_tuning": false,
        |        "organization": "*",
        |        "group": null,
        |        "is_blocking": false
        |      }
        |    ],
        |    "root": "gpt-3.5-turbo",
        |    "parent": null
        |  }
        |""".stripMargin

    val jValue = parse(modelText)
    val model = Model.fromJValue(jValue)
    assert(model != null)
    assertResult("gpt-3.5-turbo")(model.root)
    assertResult(1)(model.permission.size)
    val permission = model.permission.head
    assertResult("modelperm-ZErASyl63fhYUeMMk7QKOHAB")(permission.id)
  }

  test("multiple model parsing") {
    val buffer = Source.fromResource("models.json").getLines().mkString("\n")
    val response = parse(buffer)
    response \ "data" match {
      case JArray(arr) =>
        val models = arr.map(jValue => Model.fromJValue(jValue))
        assertResult(false)(models.isEmpty)
        assertResult("babbage")(models.head.root)
      case _ => fail("Wrong parsing")
    }
  }
