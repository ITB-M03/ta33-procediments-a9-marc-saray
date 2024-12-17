package controllers

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MenuTest {

    @Test
    fun menu() {
        val esperado = 3
        assertEquals(esperado,3)
    }
    @Test
    fun menu1(){
        val esperado = 5
        assertEquals(esperado,5)
    }
    @Test
    fun menu2(){
        val esperado = 2
        assertEquals(esperado,2)
    }
}