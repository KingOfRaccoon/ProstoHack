package elements

import androidx.compose.runtime.Composable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = UIElement.Serializer::class)
interface UIElement {
    var ord: Int
    val elementType: String
//        get() = this::class.simpleName.orEmpty()

    @Composable
    fun render(navigateTo: (String) -> Unit)

    object Serializer : KSerializer<UIElement> {
        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
            prettyPrint = true
            coerceInputValues = true
            useArrayPolymorphism = true
        }
        override val descriptor: SerialDescriptor
            get() = PolymorphicSerializer(UIElement::class).descriptor
        override fun serialize(encoder: Encoder, value: UIElement) {
            when (value) {
                is Button -> encoder.encodeSerializableValue(Button.serializer(), value)
                is TextField -> encoder.encodeSerializableValue(TextField.serializer(), value)
                is EditTextField -> encoder.encodeSerializableValue(EditTextField.serializer(), value)
                is Image -> encoder.encodeSerializableValue(Image.serializer(), value)
                is Card -> encoder.encodeSerializableValue(Card.serializer(), value)
                is Column -> encoder.encodeSerializableValue(Column.serializer(), value)
                is HorizontalPager -> encoder.encodeSerializableValue(HorizontalPager.serializer(), value)
                is LazyColumn -> encoder.encodeSerializableValue(LazyColumn.serializer(), value)
                is LazyRow -> encoder.encodeSerializableValue(LazyRow.serializer(), value)
                is Row -> encoder.encodeSerializableValue(Row.serializer(), value)
                is VerticalPager -> encoder.encodeSerializableValue(VerticalPager.serializer(), value)
            }
        }

        override fun deserialize(decoder: Decoder): UIElement {
            val jsonElement = (decoder as JsonDecoder).decodeJsonElement()
            return when (val itemType = jsonElement.jsonObject["elementType"]?.jsonPrimitive?.content) {
                "Button" -> json.decodeFromJsonElement(Button.serializer(), jsonElement)
                "TextField" -> json.decodeFromJsonElement(TextField.serializer(), jsonElement)
                "EditTextField" -> json.decodeFromJsonElement(EditTextField.serializer(), jsonElement)
                "Image" -> json.decodeFromJsonElement(Image.serializer(), jsonElement)
                "Card" -> json.decodeFromJsonElement(Card.serializer(), jsonElement)
                "Column" -> json.decodeFromJsonElement(Column.serializer(), jsonElement)
                "HorizontalPager" -> json.decodeFromJsonElement(HorizontalPager.serializer(), jsonElement)
                "LazyColumn" -> json.decodeFromJsonElement(LazyColumn.serializer(), jsonElement)
                "LazyRow" -> json.decodeFromJsonElement(LazyRow.serializer(), jsonElement)
                "Row" -> json.decodeFromJsonElement(Row.serializer(), jsonElement)
                "VerticalPager" -> json.decodeFromJsonElement(VerticalPager.serializer(), jsonElement)
                else -> throw SerializationException("Unknown itemType: $itemType")
            }
        }
    }
}