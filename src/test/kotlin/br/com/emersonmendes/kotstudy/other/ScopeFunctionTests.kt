package br.com.emersonmendes.kotstudy.other

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ScopeFunctionTests {


    /**
     * https://kotlinlang.org/docs/scope-functions.html
     */


    @Test
    fun `let scope`() {

        val numbers = listOf("one", "two", "three", "four", "five")

        numbers.let {
            Assertions.assertThat(this.javaClass.name).isEqualTo("br.com.emersonmendes.kotstudy.other.ScopeFunctionTests")
        }

        // 1
        val resultList = numbers.map { it.length }.filter { it > 3 }
        Assertions.assertThat(resultList).contains(5 , 4, 4)

        // 2
        numbers.map { it.length }.filter { it > 3 }
            .let {
                Assertions.assertThat(it).contains(5, 4, 4)

            }

        // 3
        numbers.map { it.length }.filter { it > 3 }
            .let(::println)

    }

    @Test
    fun `with scope`() {

        val numbers = mutableListOf("one", "two", "three")

        with(numbers) {
            Assertions.assertThat(this).contains("one", "two", "three")
            Assertions.assertThat(size).isEqualTo(3)
            Assertions.assertThat(first()).isEqualTo("one")
            Assertions.assertThat(last()).isEqualTo("three")
        }

        val with = with(numbers) {
            Assertions.assertThat(this.javaClass.name).isEqualTo("java.util.ArrayList")
            123
        }

        Assertions.assertThat(with).isEqualTo(123)

    }

    /**
        run does the same as with but invokes as let
     */
    @Test
    fun `run scope`() {
        val numbers = mutableListOf("one", "two", "three")

        numbers.run {
            Assertions.assertThat(this).contains("one", "two", "three")
            Assertions.assertThat(size).isEqualTo(3)
            Assertions.assertThat(first()).isEqualTo("one")
            Assertions.assertThat(last()).isEqualTo("three")
        }

        val run = numbers.run {
            Assertions.assertThat(this.javaClass.name).isEqualTo("java.util.ArrayList")
            123
        }

        Assertions.assertThat(run).isEqualTo(123)

        run {
            Assertions.assertThat(this.javaClass.name).isEqualTo("br.com.emersonmendes.kotstudy.other.ScopeFunctionTests")
        }

    }

    @Test
    fun `apply scope`() {

        val adam = Person("Adam").apply {
            age = 32
            city = "London"
        }

        Assertions.assertThat(adam.age).isEqualTo(32)
        Assertions.assertThat(adam.city).isEqualTo("London")
        Assertions.assertThat(adam.name).isEqualTo("Adam")

    }

    @Test
    fun `also scope`() {

        val adam = Person("Adam").also {
            it.age = 32
            it.city = "London"
        }

        Assertions.assertThat(adam.age).isEqualTo(32)
        Assertions.assertThat(adam.city).isEqualTo("London")
        Assertions.assertThat(adam.name).isEqualTo("Adam")

        val numbers = mutableListOf("one", "two", "three")
        numbers
            .also { println("The list elements before adding new one: $it") }
            .add("four")

    }

}

class Person(val name: String) {

    var age: Int = 0
    var city: String = ""
        get() = field
        set(value) {
            field = value
        }

}
