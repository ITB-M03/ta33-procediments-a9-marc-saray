package controllers

import utilities.abrirScanner
import utilities.cerrarScanner
import java.util.*

fun main(){

    //Llamamos a la funcion de abrirScanner
    val scan: Scanner = abrirScanner()

    //Llamamos a la funcion menu
    menu(scan)

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
fun pagar (preuFinal: Double, scanner: Scanner): Double{

    println("El preu final es $preuFinal€. Introduexi els diners:")

    var dinero_Introducido = 0.0
    while (dinero_Introducido < preuFinal) {

        val dinero = scanner.nextDouble()

        //Comprobamos si el dinero es aceptable
        if (dinero in arrayOf(0.05, 0.10, 0.20, 0.50, 1.00, 2.00, 5.00, 10.00, 20.00, 50.00)){
            dinero_Introducido += dinero
        }
        else {
            println("Aquest diner no es valid. Introduexi una altre moneda")
        }
        //Le imprimimos el dinero que le falta por introducir
        println("Et falten ${preuFinal - dinero_Introducido}€. Introdueix un altre tipús de moneda")
    }

    //Devolvemos el final y el dinero introducido
    return dinero_Introducido - preuFinal
}

    //Creamos la funcion de crear ticket
fun ticket (billet: String, zona: Int, precio: Double, canvi: Double){

    //Imprimimos el ticket
    println("----------TICKET----------")
    println("Billet: $billet")
    println("Zona: $zona")
    println("Precio: $precio")
    println("Canvi: $canvi")
    println("-------------------------")

}