package controllers

import utilities.abrirScanner
import utilities.cerrarScanner
import java.text.DecimalFormat
import java.util.*

data class Billetes(
    val billete: String,
    val precio: Double
)

var dineroaceptado = listOf(0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20, 50)
var tiposBilletes = mutableListOf<String>()
var billeteEscogidoZonaPrecio = mutableListOf<Double>()
var precioFinal = 0.0
/**
 * Función principal que inicia el programa
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param listabilletes Lista mutable de objetos Billetes que contienen los datos de los billetes disponibles
 * @param scan lee los numeros que pone el usuario
 *
 */
fun main() {
    val scan = abrirScanner()
    val listaDeBilletes = preu_Billet()
    iniciarPrograma(listaDeBilletes, scan)
    cerrarScanner(scan)
}

/**
 * La funcion inicia el programa que hara un bucle hasta que el usuario compre su ticket llamando a las otras funciones
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param listabilletes Lista mutable de objetos Billetes que contienen los datos de los billetes disponibles
 * @param scan lee los numeros que pone el usuario
 */

fun iniciarPrograma(listabilletes: MutableList<Billetes>, scan: Scanner) {
    var precioTotal: Double = 0.0
    var seguir = true
    val zonas = 3
    var etapas = 1
    var zonaSeleccionada = 1

    while (seguir && etapas <= 3) {
        val billete = menu(scan)
        if (billete == 0) {
            println("No pots tirar enrere des d'aquesta etapa inicial.")
            etapas++

        }

        val zona = zona(listabilletes, zonas, scan)
        if (zona == 0) {
            etapas--

        }

        zonaSeleccionada = zona
        precioTotal += precio_zona(listabilletes, zona, billete)
        etapas++
        seguir = continua_o_no(scan)
    }

    precioFinal = precioTotal
    pago(scan, precioTotal)
    usuario_ticket(scan, tiposBilletes, zonaSeleccionada, precioTotal)
}

/**
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param scan lee los numeros que pone el usuario double
 * @param return Devuelve el valor
 */

fun pedirNumero(scan: Scanner): Double {
    return scan.nextDouble()
}
/**
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param scan lee los numeros que pone el usuario Int
 * @param return Devuelve el valor
 */

fun pedirInt(scan: Scanner): Int {
    return scan.nextInt()
}

/**
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param preu_Billet crea una mutableList de los billetes con su precio de zona 1
 * @param return Devuelve el valor
 */
fun preu_Billet(): MutableList<Billetes> {
    return mutableListOf(
        Billetes("billete sencillo", 2.40),
        Billetes("TCasual", 11.35),
        Billetes("TUsual", 40.00),
        Billetes("TFamiliar", 10.00),
        Billetes("TJove", 80.00)
    )
}
/**
 * Menu de tickets, hacemos un bucle que leera si el numnero del usuario es del 0 al 5 sino no acabara
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param scan lee los numeros que pone el usuario Int
 * @param return Devuelve el valor
 */

fun menu(scan: Scanner): Int {
    var tipo_billete: Int
    var continuar = false

    do {
        println(
            """
            Quin billet desitja adquirir? 
            1. Bitllet senzill 
            2. TCasual 
            3. TUsual  
            4. TFamiliar  
            5. TJove
            0. Tornar enrere
            """.trimIndent()
        )

        tipo_billete = pedirInt(scan)

        scan.nextLine()

        if (tipo_billete in 0..5) {
            continuar = true
        } else {
            println("Opció incorrecta, has d'agafar un numero del 1 al 5")
        }
    } while (!continuar)

    return tipo_billete
}

/**
 * Esta funcion hara un bucle de las zonas, si el usuario no pone el numero dentro de ese area no acabara el bucle
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param listabilletes Lista mutable de objetos Billetes que contienen los datos de los billetes disponibles
 * @param scan lee los numeros que pone el usuario
 * @param zonas coge el valor del numero que devolvera
 * @param return Devuelve el valor
 *
 */
fun zona(listabilletes: MutableList<Billetes>, zonas: Int, scan: Scanner): Int {
    var zona: Int
    var continuar = false

    do {
        println(
            """
            Quina zona vol viatjar? 
            1 
            2 
            3
            0. Tornar enrere
            """.trimIndent()
        )

        zona = pedirInt(scan)

        scan.nextLine()

        if (zona in 0..3) {
            continuar = true
        } else {
            println("Opció incorrecta, introdueix un número del 1 al 3.")
        }

    } while (!continuar)

    mostrarBillete(listabilletes, zona, zonas)
    return zona
}

/**
 * Esta vuncion lo que hara es mostrar los billetes y las zonas que ha cogido el usuario
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param listabilletes Lista mutable de objetos Billetes que contienen los datos de los billetes disponibles
 * @param scan lee los numeros que pone el usuario
 * @param zonas coge el valor de zonas
 * @param zona coge el valor de zona
 * @param return Devuelve el valor
 *
 */
fun mostrarBillete(listabilletes: MutableList<Billetes>, zona: Int, zonas: Int) {

    val opcion_billete = listabilletes[zona - 1].billete
    tiposBilletes.add(opcion_billete)
    println("Ha escollit la opcio: $opcion_billete, zona $zona")
}
/**
 * Esta funcion lo que hara es si el usuario qquiere seguir comprando o no
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param scan lee los numeros que pone el usuario
 * @param Boolean devolvera un resultado booleano
 * @param return Devuelve el valor
 *
 */

fun continua_o_no(scan: Scanner): Boolean {
    var continua = false
    var error = true

    while (error) {
        println("Vol continuar comprant[S/N]")
        val usuario = scan.nextLine().uppercase()  // Este nextLine ya consume el salto de línea

        if (usuario == "S") {
            continua = true
            error = false
        } else if (usuario == "N") {
            continua = false
            error = false
        } else {
            println("Opcion incorrecta")
        }
    }
    return continua
}

/**
 * Esta funcion lo que hara es multiplicar el tipo de billete que haya escogido por la zona que haya escogido
 *
 *@author Marc Cuenca
 * @version 1.0
 *
 * @param listabilletes Lista mutable de objetos Billetes que contienen los datos de los billetes disponibles
 * @param zona coge el valor de zona
 * @param tipoBilleteSeleccionado cogera el tipo de billete que haya escogido el usuario
 * @param Double el resultado devolvera un Double
 * @param return Devuelve el valor
 *
 */
fun precio_zona(listabilletes: MutableList<Billetes>, zonas: Int, tipoBilleteSeleccionado: Int): Double {
    var zona_precio = listabilletes[tipoBilleteSeleccionado - 1].precio
    val multi_zona_2 = 1.3125
    val multi_zona_3 = 1.8443

    if (zonas == 2) {
        zona_precio *= multi_zona_2
    } else if (zonas == 3) {
        zona_precio *= multi_zona_3
    }

    billeteEscogidoZonaPrecio.add(zona_precio)
    return zona_precio
}
/**
 * Esta funcion lo que hhara es mirar la lista de dineroaceptado y dira si el dinero que a introducido es  buneo o no
 *
 * @author Marc Cuenca
 * @version 1.0
 *
 * @param scan lee los numeros que pone el usuario
 * @param Int Devolvera el resultado Int
 * @param return Devuelve el valor
 *
 */

fun dinero(scan: Scanner): Int {
    var resultado: Int
    var aceptado = false
    do {
        println("Introduexi el diners")
        resultado = pedirInt(scan)
        scan.nextLine()
        if (resultado in dineroaceptado) {
            aceptado = true
        } else {
            println("Aquest diners no son valids")
        }
    } while (!aceptado)
    return resultado
}
/**
 * Esta funcion lo que hara es que dira el dinero que tiene que pagar el usuario y el dinero restante y el cambio que dara
 *
 * @author Marc Cuenca
 * @version 1.0
 *
 * @param scan lee los numeros que pone el usuario
 * @param precio cogera el precio del billete
 */

fun pago(scan: Scanner, precio: Double) {
    var precioRestante = precio
    var pagoCompletado = false

    while (!pagoCompletado) {
        println("Ha de pagar ${"%.2f".format(precioRestante)}€")
        val dineroIngresado = dinero(scan)
        precioRestante -= dineroIngresado

        if (precioRestante <= 0.0) {
            pagoCompletado = true
        }

        if (precioRestante < 0.0) {
            println("Pagament completat. El seu canvi es: ${"%.2f".format(-precioRestante)}€")
        }
    }
}

/**
 * Esta funcion lo que hara es que dira si el usuario quera el ticket , si lo quiere lo imprimira, sino no
 *
 * @author Marc Cuenca
 * @version 1.0
 *
 * @param scan lee los numeros que pone el usuario
 * @param tiposBilletes cogera la lista de los tipos de billetes
 * @param zona cogera el valor de zona
 * @param precio cogera el valor de precio
 *
 */

fun usuario_ticket(scan: Scanner, tiposBilletes: MutableList<String>, zona: Int, precio: Double) {
    println("Vol el Ticket? (S/N)")
    val ticket_si_no = scan.nextLine().uppercase()

    if (ticket_si_no == "S") {
        imprimirTiquet()
    } else {
        println("Gracias per la seva compra.")
    }
}
/**
 * Esta funcion lo que hara es que si el usuario si ha querido el ticket imprimira el ticketcon sus valores
 *
 * @author Marc Cuenca
 * @version 1.0
 * @param precio cogera el precio
 * @param return Devuelve el valor
 */

fun imprimirTiquet() {
    println("----------TICKET----------")
    for (x in 0 until tiposBilletes.size) {
        println("${tiposBilletes[x]}")
        println("Precio: ${imprimirPrecio(billeteEscogidoZonaPrecio[x])}€")
        println("--------------------------")
    }
    println("Gracias per la seva compra")
}

fun imprimirPrecio(precio: Double): String {
    val df = DecimalFormat("0.00")
    return df.format(precio)
}
