package jp.ne.mmj.example.klaxon

import com.beust.klaxon.Json
import com.beust.klaxon.TypeAdapter
import com.beust.klaxon.TypeFor
import kotlin.reflect.KClass

@TypeFor(field = "type", adapter = ItemAdapter::class)
abstract class Item(
  val type: String
)

data class IntItem(val value: Int) : Item("int")

// remove description after type created
data class StringItem(
  val value: String
  // just remove property no longer needed,
  // Klaxon ignore when deserialize json data
  // val description: String
) : Item("string")

class ItemAdapter : TypeAdapter<Item> {
  override fun classFor(type: Any): KClass<out Item> = when (type as String) {
    "string" -> StringItem::class
    "int" -> IntItem::class
    else -> throw IllegalArgumentException("not such type") //To change body of created functions use File | Settings | File Templates.
  }
}