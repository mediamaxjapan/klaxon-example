package jp.ne.mmj.example.klaxon

import com.beust.klaxon.Klaxon
import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

class RemovalTest : Spek({

  test("removed field is just ignored") {
    val oldJson = """[{"value" : 1, "type" : "int"}, {"description" : "description of hello", "value" : "hello", "type" : "string"}]"""
    val loaded = Klaxon().parseArray<Item>(oldJson)!!
    assertEquals(2, loaded.size)
    val newJson = """[{"value" : 1, "type" : "int"}, {"value" : "hello", "type" : "string"}]"""
    assertEquals(newJson, Klaxon().toJsonString(loaded))
  }

})
