package com.stulsoft.ai.model

import com.stulsoft.ai.http.HttpTransport

import scala.util.Try

object ModelsList:
  def models(): Try[List[Model]] =
    HttpTransport.get("https://api.openai.com/v1/models")(Model.entityToModels)
