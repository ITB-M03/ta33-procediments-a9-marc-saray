package controllers

import utilities.abrirScanner
import utilities.cerrarScanner
import java.util.*

fun main(){

    //Llamamos a la funcion de abrirScanner
    val scan: Scanner = abrirScanner()

    //Llamamos a la funcion menu
    menu(scan)

    //Llamamos a la funcion de zonas

    //Llamamos a la funcion de cerrar scanner
    cerrarScanner(scan)
}

//Funcion Menu
fun menu(scanner: Scanner): String{

    //Creamos el menu para el usuario
    println("1. Bitllet senzill\n" +
            "2. TCasual\n" +
            "3. TUsual\n" +
            "4. TFamiliar\n" +
            "5. TJove")

    val numero = scanner.nextInt()
    return when(numero){
        1 -> "Bitllet senzill"
        2 -> "TCasual"
        3 -> "TUsual"
        4 -> "TFamiliar"
        5 -> "TJove"
        else -> "Numero no valid"
    }
}

//Creamos la funcion zonas
fun zonas (scanner: Scanner):Int {

    //Creamos una variable inicializando a 3 porque el usuario solo tiene 3 intentos
    val intentos = 3

    for (intento in 1.. intentos){

        println("Quina zona desitja viatjar 1,2 o 3")

        //Pedimos el numero al usuario
        val numero_zonas = scanner.nextInt()

        if (numero_zonas in 1..3){
            return numero_zonas
        } else {
            println("Aquest numero no es valid")
        }
    }

    //Si el usuario se a excedido le devolvemos un error
    println("Màxim d'intents per escollir la zona excedit.")
    return -1
}

//Creamos la funcion de los precios
fun precios(tipoBitllet: String, zonaSeleccionada: Int): Double {
    //Precios zona 1
    val preusPrimerZona = mapOf(
        "Bitllet senzill" to 2.40,
        "TCasual" to 11.35,
        "TUsual" to 40.00,
        "TFamiliar" to 10.00,
        "TJove" to 80.00
    )

    val preuBase = preusPrimerZona[tipoBitllet] ?: return 0.0 // Si no es válido, retornamos 0.0

    //Multiplicadores el valor de cada zona
    val multiplicadorZona2 = 1.3125
    val multiplicadorZona3 = 1.8443

    //Calculamos el precio final en base a la zona seleccionada
    return when (zonaSeleccionada) {
        1 -> preuBase
        2 -> preuBase * multiplicadorZona2
        3 -> preuBase * multiplicadorZona3
        else -> 0.0
    }
}

//Hacer precios
//Imprimir ticket si el usuario queire o no
//Emparejarlo en el fun main y dokka y test