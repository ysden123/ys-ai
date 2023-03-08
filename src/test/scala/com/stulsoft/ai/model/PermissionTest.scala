package com.stulsoft.ai.model

import org.json4s.{DefaultFormats, Extraction, JArray, JValue, jvalue2extractable}
import org.scalatest.funsuite.AnyFunSuite
import org.json4s._
import org.json4s.jackson.JsonMethods.*
import org.scalactic.Prettifier.default

class PermissionTest extends AnyFunSuite:
  test("parsing") {
    val permissionText: String =
      """
        |{
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
        |""".stripMargin

    val jValue = parse(permissionText)
    val permission = Permission.fromJValue(jValue)
    assert(permission != null)
    assertResult("model_permission")(permission.theObject)
  }
