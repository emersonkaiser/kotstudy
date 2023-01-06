package br.com.emersonmendes.kotstudy.other

import org.junit.jupiter.api.Assertions as AquiTaoOsAsserts
import org.junit.jupiter.api.Test as IssoEhUmMetodoDeTeste

class AnyTests {

    @IssoEhUmMetodoDeTeste
    fun `should compare objects references`() {

        val car1 = Car("blue", 1988)
            .also {
                it.color = "green"
            }
            .apply {
                color = "red"
            }
            .let {
                it.color = "blue"
                it
            }
            .run {
                color = "blue"
                this
            }

        val car2 = Car(color = "blue", year = 1988)

        AquiTaoOsAsserts.assertTrue(car1 == car2)
        AquiTaoOsAsserts.assertFalse(car1 === car2)
        AquiTaoOsAsserts.assertFalse(car1.color.ehIgual(car2.color))

    }

}

private fun String.ehIgual(value: String): Boolean = false

data class Car(
    var color: String,
    val year: Int
)
