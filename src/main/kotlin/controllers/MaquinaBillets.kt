package org.example.controllers

import utilities.abrirScanner
import utilities.cerrarScanner
import java.util.*

fun main() {

    //Llamamos a la funcion scanner
    val scan: Scanner = abrirScanner()

    //Llamamos a la funcion menu
    menu()

    //LLamamos a la funcion de pedir un numero
    val numero = pedirNumero(scan)

    //Llamamos a la funcion del 'MenuFuncional'
    menuFuncional(numero)


    //Llamamos a la funcion de cerrar scanner
    cerrarScanner(scan)
}

//Hacemos la funcion del menu
fun menu (){
    println("Quin billet desitja adquirir?" +
            "1. Bitllet senzill\n" +
            "2. TCasual\n" +
            "3. TUsual\n" +
            "4. TFamiliar\n" +
            "5. TJove")
}

//Creamos la funcion de pedir numero
fun pedirNumero(scan: Scanner): Int {
    //Leemos el numero del usuario
    val num = scan.nextInt()
    //Devolvemos el valor de num
    return num
}
//Hacer un menu funcional, que si el usuario escoga el 1 vaya a la funcion x
//Debemos creear 5 funciones 1 para cada 1 que haga su funcion
fun menuFuncional (num: Int) : Int{

    return when(num){
        1 ->billet_Senzill()
        2 ->t_Casual()
        3 ->t_Usual()
        4 -> t_Familiar()
        5 -> t_Jove()
    }
}
//Creamos la funcion de viaje
fun viatjar (scan: Scanner):Int{
    println("Quina zona vol viatjar")
    //Leemos el numero del usuario
    val num_Viaje = scan.nextInt()
    //Devolvemos el valor
    return num_Viaje
}

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