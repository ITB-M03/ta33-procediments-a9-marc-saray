/*
package org.example.controllers

import utilities.abrirScanner
import utilities.cerrarScanner
import java.util.*



fun main() {
    iniciTickets()

}
fun iniciTickets() {

    //Llamamos a la funcion scanner
    val scan: Scanner = abrirScanner()

    //Fase-1
    val billet = menu(scan)
    if (billet == "") {
        println("Operació cancel·lada")
        return
    }


    //Fase-2
    val zones = num_Zona(scan)
    if (zones == 0) {
        println("Operacio cancel·lada")
        return
    }

    //Fase-3


    
    //Llamamos a la funcion de cerrar scanner
    cerrarScanner(scan)
}

//Hacemos la funcion del menu
fun menu (scan: Scanner): String{
    //Le decimos al usuario que billete quiere
    println("Quin billet desitja adquirir? (1..5)" +
            "1. Bitllet senzill\n" +
            "2. TCasual\n" +
            "3. TUsual\n" +
            "4. TFamiliar\n" +
            "5. TJove")
    //Si el usuario
    return when (scan.nextInt()) {
        1 -> "Bitllet senzill"
        2 ->"TCasual"
        3 ->"TUsual"
        4 ->"TFamiliar"
        5 ->"TJove"
        0 -> "" //se devuelve vacio para cancelar la operacion
        else -> {
            println("Aquest numero no es valid, posa un numero del 1 al 5")
            menu(scan) //Lo vuelve a llamar para que ponga un nuevo numero
        }
    }
}

fun num_Zona(scan: Scanner): Any {
    var contador_num = 0

    // Bucle para pedir hasta 3 intentos
    while (contador_num < 3) {
        println("Quina zona vol viatjar (1..3)")

        val zona = scan.nextInt()  // Leer la zona en cada iteración del bucle

        if (zona in 1..3) {
            return zona // Si la zona es válida, la devolvemos y salimos
        } else if (zona == 0) {
            return 0 // Si el usuario ingresa 0, cancelamos la operación
        } else {
            println("Aquest numero no es valid, posa un numero del 1 al 3")
        }

        contador_num++ // Incrementamos el contador de intentos
    }

    return -1 // Indicamos que la operación fue cancelada por exceso de intentos
}



//Creamos la funcion de la zona1
fun precio(billet: String, zones:Int ): Double{
    //Creamos una funcion de los euros
    fun diners_Aceptat (diners: Double) : Boolean{
        //Creamos el array con el dinero que solo se puede agregar
        val dinersAceptat = arrayOf(0.05, 0.10, 0.20, 0.50, 1.00, 2.00, 5.00, 10.00, 20.00, 50.00)

        //Devolvemos el contenido del array
        return dinersAceptat.contains(diners)
    }

}


//Creamos la funcion con las multiplicaciones de las zonas 2 y 3
fun preu_zonas (): Double{
    //Les damos el valor a las zonas
    val zona2 = 1.3125
    val zona3 = 1.8443
    //Devolvemos las 2 zonas
    return zona2 + zona3
}

//















//Debemos creear 5 funciones 1 para cada 1 que haga su funcion
//Que se pueda tirar para atras
fun billet_Senzill(){
    println("Ha escogit el Billet senzill")


}

fun t_Casual(){
    println("Ha escogit la TCasual")

}

fun t_Usual(){
    println("Ha escogit la TUsual")

}

fun t_Familiar(){
    println("Ha escogit la TFamiliar")

}

fun t_Jove(){
    println("Ha escogit la TJove")

}
*/
