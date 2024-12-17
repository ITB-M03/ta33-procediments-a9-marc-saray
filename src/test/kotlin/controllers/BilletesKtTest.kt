package controllers

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BilletesKtTest {

    @Test
    fun precio_zona() {
        val listaDeBilletes = preu_Billet()
        val resultado = precio_zona(listaDeBilletes, 3, 1)

        val esperado = 2.40 * 1.8443
        assertEquals(esperado, resultado, 0.01)
    }
    @Test
    fun precio_zona2() {
        val listaDeBilletes = preu_Billet()
        val resultado = precio_zona(listaDeBilletes, 1, 1)

        val esperado = 2.40
        assertEquals(esperado, resultado, 0.01)
    }
    @Test
    fun precio_zona3() {
        val listaDeBilletes = preu_Billet()
        val resultado = precio_zona(listaDeBilletes, 2, 1)

        val esperado = 2.40 * 1.3125
        assertEquals(esperado, resultado, 0.01)
    }
}