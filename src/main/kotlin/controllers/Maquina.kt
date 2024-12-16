package controllers

import utilities.abrirScanner
import utilities.cerrarScanner
import java.util.*

fun main(){

    //Llamamos a la funcion de abrirScanner
    val scan: Scanner = abrirScanner()

    //Llamamos a la funcion menu
    gestionarCompra(scan)

    //Llamamos a la funcion de cerrar scanner
    cerrarScanner(scan)
}

fun gestionarCompra(scanner: Scanner) {

    var etapas = 0
    //Iremos fase por fase

    while (etapas <= 5) {
        //Fase 1 -> Menu
        val billete = menu(scanner)
        if (billete == "Cancelant operació"){
            etapas++
        }

        else{

            //Fase 2 -> Zona
            val zona = zonas(scanner)
            if (zona == -1){
                etapas--
            }
            else{
                etapas++

                //Fase 3 -> precios
                val precioFinal = precios(billete, zona)
                println("El preu del bitllet $billete per a la zona $zona és $precioFinal €, introduexi els diners")


                //Fase 4 -> Pagar
                val canvio = pagar(precioFinal, scanner)
                println("El teu canvi és $canvio €")

                scanner.nextLine()

                //Fase 5 -> Ticket [S/N]
                println("Vols imprimir el tiquet? (S/N)")
                val imprimirTicket = scanner.nextLine().uppercase()
                if (imprimirTicket == "S"){
                    etapas = 0
                    ticket(billete, zona, precioFinal)

                } else if (imprimirTicket == "N"){
                    println("Gràcies per la seva compra!")
                    etapas = 0

                }
            }
        }
    }
}


//Funcion Menu -> 1
fun menu(scanner: Scanner): String {
    //Creamos el menú para el usuario
    println("1. Bitllet senzill\n" + "2. TCasual\n" + "3. TUsual\n" + "4. TFamiliar\n" + "5. TJove")

    return try {
        val numero = scanner.nextInt()
        scanner.nextLine()
        when (numero) {
            1 -> "Bitllet senzill"
            2 -> "TCasual"
            3 -> "TUsual"
            4 -> "TFamiliar"
            5 -> "TJove"
            else -> "Cancelant operació"
        }
    } catch (e: InputMismatchException) {

        scanner.nextLine()

        "Operacio Cancelada"
    }
}

//Creamos la funcion zonas -> 2
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
            println("Cancelant operació")
        }
    }

    //Si el usuario se a excedido le devolvemos un error
    println("Màxim d'intents per escollir la zona excedit.")
    return -1
}

//Creamos la funcion de los precios -> 3
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

//Hacemos la funcion de pagar -> 4
fun pagar (preuFinal: Double, scanner: Scanner): Double{

    var dinero_Introducido = 0.0
    while (dinero_Introducido < preuFinal) {

        val dinero = scanner.nextDouble()
        var dinero_Aceptado = arrayOf(0.05, 0.10, 0.20, 0.50, 1.00, 2.00, 5.00, 10.00, 20.00, 50.00)

        //Comprobamos si el dinero es aceptable
        if (dinero in dinero_Aceptado){
            dinero_Introducido += dinero
        }
        else {
            println("Aquest diners no es valid. Introduexi una altre moneda")
        }
    }

    //Devolvemos el final y el dinero introducido
    return dinero_Introducido - preuFinal
}

//Creamos la funcion de crear ticket -> 5
fun ticket (billet: String, zona: Int, precio: Double){

    //Imprimimos el ticket
    println("----------TICKET----------")
    println("Billet: $billet")
    println("Zona: $zona")
    println("Precio: $precio")
    println("-------------------------" )

}