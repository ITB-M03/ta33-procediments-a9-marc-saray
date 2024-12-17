package controllers

import utilities.abrirScanner
import utilities.cerrarScanner
import java.util.*

data class Billetes(
    val billete : String,
    val precio : Double
)

var dineroaceptado = listOf(0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20 , 50)
var tiposBilletes = mutableListOf<String>()
var billeteEscogidoZonaPrecio = mutableListOf<Double>()
var precioFinal = 0.0

fun main(){
    val scan = abrirScanner()
    val listaDeBilletes = preu_Billet()
    iniciarPrograma(listaDeBilletes, scan)
    cerrarScanner(scan)

}
//Creamos la funcion que iniciara todas las funciones
fun iniciarPrograma(listabilletes: MutableList<Billetes>, scan: Scanner) {
    val password = 4321
    var precioTotal: Double = 0.0
    var seguir = true
    val zonas = 3  // Definir la cantidad de zonas disponibles, puedes cambiar este valor
    var etapas = 1 // Contador para las etapas de la maquina

    while(seguir && etapas < 3){
        val billete = menu(scan) // Pedir tipo Billete
        val zona = zona(listabilletes, zonas, scan) //Pedir zona de billete
        etapas++
        precioTotal += precio_zona(listabilletes, zona, billete) //Calcular Precio
        seguir = continua_o_no(scan) //Si quieres mas Billetes
    }
    pago(scan, precioTotal)
    //usuario_ticket(scan, tiposBilletes, zona, precioFinal)
}


fun pedirNumero(scan : Scanner) : Double {
    var numero : Double

    numero = scan.nextDouble()

    return numero
}
//
fun pedirInt(scan : Scanner) : Int {
    var numero : Int

    numero = scan.nextInt()
    scan.nextLine()

    return numero
}

//Creem la funcion del precio del billete
//La creamos con el precio base para luego poder multiplicarlos
fun preu_Billet (): MutableList<Billetes>{
    var listabilletes = mutableListOf<Billetes>()
    listabilletes.add(Billetes("billete sencillo", 2.40))
    listabilletes.add(Billetes("TCasual", 11.35))
    listabilletes.add(Billetes("TUsual", 40.00))
    listabilletes.add(Billetes("TFamiliar", 10.00))
    listabilletes.add(Billetes("TJove", 80.00))

    return listabilletes
}

//Creamos la funcion del menu
fun menu (scan: Scanner): Int{

    var tipo_billete : Int

    println("""
        Quin billet desitja adquirir? 
        1. Bitllet senzill 
        2. TCasual 
        3. TUsual  
        4. TFamiliar  
        5. TJove"""
        .trimIndent()
    )


    tipo_billete = pedirInt(scan)
    return tipo_billete
}

//Creamos la funcion de pedir zona
fun zona(listabilletes: MutableList<Billetes>, zonas: Int, scan: Scanner): Int{
    //Le damos un valor al resultado
    var zona : Int

    println("""
        Quina zona vol viajar 
        1 
        2 
        3"""
        .trimIndent()
    )

    zona = pedirInt(scan)
    mostrarBillete(listabilletes,zona,zonas)

    return zona
}

//Creamos la funcion para mostrar el billete
fun mostrarBillete(listabilletes: MutableList<Billetes>, zona: Int, zonas: Int)
{
    var opcion_billete = listabilletes[zona - 1].billete
    tiposBilletes.add(opcion_billete)
    println("Ha escollit la opcio: $opcion_billete, zona $zona")

}

//Tenemos que crear una funcion por si el usuario [S/N]
fun continua_o_no(scan: Scanner): Boolean{
    //Le damos el valor al resultado
    var continua = false

    //Le pedimos informacion al usuario
    println("Vol continuar comprant[S/N]")
    var usuario = scan.nextLine().uppercase()

    if (usuario == "S"){
        continua = true
    }else if (usuario == "N"){
        continua = false
    }
    return continua
}

//Debemos crear la funcion que hara que se multipliquen los billetes dependiendo de la zona
fun precio_zona (listabilletes: MutableList<Billetes>, zonas: Int, tipoBilleteSeleccionado: Int  ): Double{

    //Le damos valor al resultado
    var zona_precio : Double

    var lista = tipoBilleteSeleccionado

    //Les damos los valores a las zonas
    var multi_zona_2 = 1.3125
    var multi_zona_3 = 1.8443


    //Hacemos los calculos
    zona_precio = listabilletes[lista -1].precio
    //Zona 2
    if (zonas == 2){
        zona_precio *= multi_zona_2
        //Zona 3
    } else if (zonas == 3){
        zona_precio *= multi_zona_3
    } //Zona 1
    else{
        zona_precio = zona_precio
        billeteEscogidoZonaPrecio.add(zona_precio)
        //etapas++
    }
    return zona_precio
}

//Creamos la funcion de comprobar si el dinero que ingresa el usuario es aceptado o no
fun dinero (scan: Scanner): Int {
    //Le damos valor al resultado
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

//Creamos la funcion de que el usuario tiene que pagar
// Creamos la función de que el usuario tiene que pagar
fun pago(scan: Scanner, precio: Double) {
    var precioRestante = precio
    var pagoCompletado = false

    //El bucle no acabara hasta que se haya completado el pago
    while (!pagoCompletado) {
        println("Ha de pagar ${"%.2f".format(precioRestante)}€") // Mostramos el precio restante
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

//Creamos la funcion por si el usuario quiere ticket o no
fun usuario_ticket(scan: Scanner, tiposBilletes: MutableList<String>, zona: Int, precioFinal: Double) {
    // Le pedimos al usuario si quiere un ticket
    println("Vol el Ticket? (S/N)")
    val ticket_si_no = scan.nextLine().uppercase()  //Leemos la respuesta del usuario

    if (ticket_si_no == "S") {
        // Llamamos a la función para imprimir el ticket
        imprimirTiquet(tiposBilletes, zona, precioFinal)
    } else {
        println("Gracias por la compra.")
    }
}

// Creamos la función para imprimir el ticket
fun imprimirTiquet(tiposBilletes: MutableList<String>, zona: Int, precioFinal: Double) {
    println("-----------TICKET------------")
    // Imprime los tipos de billetes
    println("Tipo de billete: ${tiposBilletes.joinToString(", ")}")
    // Imprime la zona seleccionada
    println("Zona: $zona")
    // Imprime el precio final formateado
    println("Precio total: ${"%.2f".format(precioFinal)}€")
    println("----------------------------")
    println("¡Gracias por su compra!")
}