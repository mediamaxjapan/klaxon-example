package jp.ne.mmj.example.klaxon

import com.beust.klaxon.Klaxon
import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

class AdditionTest : Spek({

  test("add field and automatically migrated when schema updated") {
    val oldJson = """[{"value" : 1, "type" : "int"}, {"value" : "hello", "type" : "string"}]"""
    val loaded = Klaxon().parseArray<Input>(oldJson)!!
    // verify load successfully
    assertEquals(2, loaded.size)
    // verify new field is property migrated
    val migrated = loaded[1] as StringInput
    assertEquals("description of hello", migrated.description)
    // verify serialized with new field
    val newJson = """[{"value" : 1, "type" : "int"}, {"description" : "description of hello", "value" : "hello", "type" : "string"}]"""
    assertEquals(newJson, Klaxon().toJsonString(loaded))
  }

})
