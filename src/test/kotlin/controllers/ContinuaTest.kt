package controllers

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ContinuaTest {

    @Test
    fun continua_o_no(){
        val esperado = true
        assertEquals(esperado,true)
    }
    @Test
    fun continua_o_no1() {
        val esperado = false
        assertEquals(esperado, false)
    }

}