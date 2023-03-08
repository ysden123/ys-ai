package com.stulsoft.ai.model

import org.apache.hc.core5.http.HttpEntity
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.json4s.*
import org.json4s.FieldSerializer.*
import org.json4s.jackson.JsonMethods.*
case class Model(id: String,
                 theObject: String,
                 created: Long,
                 owned_by: String,
                 permission: List[Permission],
                 root: String,
                 parent: Option[String])
object Model:
  private val rename4Model = FieldSerializer[Model](renameTo("theObject", "object"),
    renameFrom("object", "theObject"))

  private val rename4Permission = FieldSerializer[Permission](renameTo("theObject", "object"),
    renameFrom("object", "theObject"))

  given formats: Formats = DefaultFormats + rename4Model + rename4Permission

  def fromJValue(jValue: JValue): Model =
    val model = Extraction.extract[Model](jValue)
    model

  def entityToModels(entity:HttpEntity):List[Model] =
    val response = parse(EntityUtils.toString(entity))
    response \ "data" match {
      case JArray(arr) =>
        arr.map(jValue => Model.fromJValue(jValue))
      case _ => List.empty
    }