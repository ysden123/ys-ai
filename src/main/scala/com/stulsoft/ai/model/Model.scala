package com.stulsoft.ai.model

import org.apache.hc.core5.http.HttpEntity
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.json4s.*
import org.json4s.jackson.JsonMethods.*
case class Model(id: String,
                 created: Long,
                 owned_by: String,
                 permission: List[Permission],
                 root: String,
                 parent: Option[String]) {
  private var object_value: String = _

  def objectValue(): String = this.object_value

  def withObjectValue(theObject: String): Model =
    this.object_value = theObject
    this
}

object Model:
  given formats: DefaultFormats = DefaultFormats

  def fromJValue(jValue: JValue): Model =
    val model = Extraction.extract[Model](jValue)
    jValue \ "object" match {
      case JString(s) => model.withObjectValue(s)
      case _ =>
    }
    model

  def entityToModels(entity:HttpEntity):List[Model] =
    val response = parse(EntityUtils.toString(entity))
    response \ "data" match {
      case JArray(arr) =>
        arr.map(jValue => Model.fromJValue(jValue))
      case _ => List.empty
    }