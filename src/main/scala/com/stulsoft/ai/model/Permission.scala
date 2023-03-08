package com.stulsoft.ai.model

import org.json4s.*
import org.json4s.FieldSerializer.*

case class Permission(id: String,
                      theObject: String,
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
                     )

object Permission:
  private val rename = FieldSerializer[Permission](renameTo("theObject", "object"),
    renameFrom("object", "theObject"))

  given formats: Formats = DefaultFormats + rename

  def fromJValue(jValue: JValue): Permission =
    val permission = Extraction.extract[Permission](jValue)
    permission
