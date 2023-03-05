package com.stulsoft.ai.model

import org.json4s._

case class Permission(id: String,
                      created: Long,
                      allow_create_engine: Boolean,
                      allow_sampling: Boolean,
                      allow_logprobs: Boolean,
                      allow_search_indices: Boolean,
                      allow_view: Boolean,
                      allow_fine_tuning: Boolean,
                      organization: String,
                      group: Option[String],
                      is_blocking: Boolean
                     ) {
  private var object_value: String = _

  def objectValue(): String = this.object_value

  def withObjectValue(theObject: String): Permission =
    this.object_value = theObject
    this
}

object Permission:
  given formats: DefaultFormats = DefaultFormats
  def fromJValue(jValue: JValue): Permission =
    val permission = Extraction.extract[Permission](jValue)
    jValue \ "object" match {
      case JString(s) => permission.withObjectValue(s)
      case _ =>
    }
    permission
