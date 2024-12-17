package controllers

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class precioZonaTest {

    @Test
    fun precio_zona() {
        val listaDeBilletes = preu_Billet()
        val resultado = precio_zona(listaDeBilletes, 3, 1)

        // Esperamos que el precio se calcule multiplicando 2.40 por 1.8443 (factor para 3 zonas)
        val esperado = 2.40 * 1.8443
        assertEquals(esperado, resultado, 0.01)
    }
}