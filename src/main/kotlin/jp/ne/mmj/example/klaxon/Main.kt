package jp.ne.mmj.example.klaxon

import com.beust.klaxon.Klaxon

object Main {
  @JvmStatic
  fun main(args: Array<String>) {
    val inputs = arrayOf(IntInput(1), StringInput("hello"))
    val json = Klaxon().toJsonString(inputs)
    println(json)
  }
}