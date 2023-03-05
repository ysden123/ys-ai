package com.stulsoft.ai.model
import com.stulsoft.ai.model.ModelsList.models
import org.scalatest.funsuite.AnyFunSuite

import scala.util.Success

class ModelListTest  extends AnyFunSuite:
  test("models"){
    models() match {
      case Success(models) =>
        assert(models.size > 0)
        assert(models.head != null)
      case _ =>
        fail("Error")
    }
  }
