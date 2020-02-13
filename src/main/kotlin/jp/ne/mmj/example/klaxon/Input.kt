package jp.ne.mmj.example.klaxon

import com.beust.klaxon.TypeAdapter
import com.beust.klaxon.TypeFor
import kotlin.reflect.KClass

@TypeFor(field = "type", adapter = InputAdapter::class)
open class Input(val type: String)

data class IntInput(val value: Int) : Input("int")

// add description after type created
data class StringInput(val value: String, var description: String? = null) : Input("string") {
  init {
    // data migration from no description version
    if (description == null) {
      description = """description of $value"""
    }
  }
}

class InputAdapter : TypeAdapter<Input> {
  override fun classFor(type: Any): KClass<out Input> = when (type as String) {
    "string" -> StringInput::class
    "int" -> IntInput::class
    else -> throw IllegalArgumentException("not such type") //To change body of created functions use File | Settings | File Templates.
  }
}