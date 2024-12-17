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

fun main() {
    val scan = abrirScanner()
    val listaDeBilletes = preu_Billet()
    iniciarPrograma(listaDeBilletes, scan)
    cerrarScanner(scan)
}

fun iniciarPrograma(listabilletes: MutableList<Billetes>, scan: Scanner) {
    var precioTotal: Double = 0.0
    var seguir = true
    val zonas = 3
    var etapas = 1 // Contador para las etapas de la máquina
    var zonaSeleccionada = 1

    while (seguir && etapas < 3) {
        val billete = menu(scan) // Pedir tipo de billete
        if (billete == 0) {
            println("No pots tirar enrere des d'aquesta etapa inicial.")
            etapas++
        }

        val zona = zona(listabilletes, zonas, scan) // Pedir zona de billete
        if (zona == 0) { // Si el usuario selecciona "Tornar enrere"
            etapas--
        }

        zonaSeleccionada = zona // Actualizamos la última zona seleccionada
        precioTotal += precio_zona(listabilletes, zona, billete) // Calcular el precio total
        etapas++
        seguir = continua_o_no(scan) // Si quieres más billetes
    }

    precioFinal = precioTotal // Actualizamos el precio final antes del pago
    pago(scan, precioTotal)
    usuario_ticket(scan, tiposBilletes, zonaSeleccionada, precioTotal) // Imprimir ticket si el usuario lo desea
}

fun pedirNumero(scan: Scanner): Double {
    return scan.nextDouble()
}

fun pedirInt(scan: Scanner): Int {
    return scan.nextInt().apply { scan.nextLine() }
}

fun preu_Billet(): MutableList<Billetes> {
    return mutableListOf(
        Billetes("billete sencillo", 2.40),
        Billetes("TCasual", 11.35),
        Billetes("TUsual", 40.00),
        Billetes("TFamiliar", 10.00),
        Billetes("TJove", 80.00)
    )
}

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

        if (tipo_billete in 0..5) {
            continuar = true
        } else {
            println("Opció incorrecta, has d'agafar un numero del 1 al 5")
        }
    } while (!continuar)

    return tipo_billete
}

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

        if (zona in 0..3) {
            continuar = true
        } else {
            println("Opción incorrecta, por favor introduzca un número del 1 al 3.")
        }

    } while (!continuar)

    mostrarBillete(listabilletes, zona, zonas)
    return zona
}

fun mostrarBillete(listabilletes: MutableList<Billetes>, zona: Int, zonas: Int) {
    val opcion_billete = listabilletes[zona - 1].billete
    tiposBilletes.add(opcion_billete)
    println("Ha escollit la opcio: $opcion_billete, zona $zona")
}

fun continua_o_no(scan: Scanner): Boolean {
    var continua = false
    var error = true
    while (error) {
        println("Vol continuar comprant[S/N]")
        val usuario = scan.nextLine().uppercase()
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

fun dinero(scan: Scanner): Int {
    var resultado: Int
    var aceptado = false
    do {
        println("Introduexi el diners")
        resultado = pedirInt(scan)
        if (resultado in dineroaceptado) {
            aceptado = true
        } else {
            println("Aquest diners no son valids")
        }
    } while (!aceptado)
    return resultado
}

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
            println("Pago completado. Su cambio es: ${"%.2f".format(-precioRestante)}€")
        }
    }
}

fun usuario_ticket(scan: Scanner, tiposBilletes: MutableList<String>, zona: Int, precio: Double) {
    println("Vol el Ticket? (S/N)")
    val ticket_si_no = scan.nextLine().uppercase()

    if (ticket_si_no == "S") {
        imprimirTiquet()
    } else {
        println("Gracias por la compra.")
    }
}

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
