package com.bofu.a20210421_fu_project.extensions

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class GetOptionTest {


    private val num1 = 7
    private val num2 = 14
    private var num3 = 111



    @Test
    fun getOptionResultTest() {
        val option1 = num1.getOption()
        assertThat(option1).isEqualTo(1)

        val option2 = num2.getOption()
        assertThat(option2).isEqualTo(2)

        val option3 = num3.getOption()
        assertThat(option3).isEqualTo(3)
    }

    @Test
    fun getOptionRepetitionTest () {
        val option4 = (num3 + 1).getOption()
        assertThat(option4).isEqualTo(1)
    }
}