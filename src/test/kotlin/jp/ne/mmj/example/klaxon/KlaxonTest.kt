package jp.ne.mmj.example.klaxon

import com.beust.klaxon.Klaxon
import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

class KlaxonTest : Spek({

  test("original version (without description)") {
    val json = """[{"value" : 1, "type" : "int"}, {"value" : "hello", "type" : "string"}]"""
    val inputs = Klaxon().parseArray<Input>(json)
    assertEquals(2, inputs!!.size)
  }

  test("migrated when schema updated (add description to StringInput)") {
    val json = """[{"value" : 1, "type" : "int"}, {"value" : "hello", "type" : "string"}]"""
    val inputs = Klaxon().parseArray<Input>(json)
    assertEquals(2, inputs!!.size)
    val actual = inputs[1] as StringInput
    assertEquals("description of hello", actual.description)
    val updated = """[{"value" : 1, "type" : "int"}, {"description" : "description of hello", "value" : "hello", "type" : "string"}]"""
    assertEquals(updated, Klaxon().toJsonString(inputs))
  }

})
