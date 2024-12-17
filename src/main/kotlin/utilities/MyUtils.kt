package utilities
import java.util.*

fun abrirScanner() : Scanner {
    var scan = Scanner(System.`in`).useLocale(Locale.UK)

    return scan
}

/**
 *Cierre del Scanner
 * @author Angel Sardinha
 * @param scan --> la funcion que contiene el scanner
 */
fun cerrarScanner(scan : Scanner) {
    scan.close()
}