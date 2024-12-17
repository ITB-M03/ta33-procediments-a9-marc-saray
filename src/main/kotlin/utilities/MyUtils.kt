
package utilities
import java.util.*

fun abrirScanner() : Scanner {
    var scan = Scanner(System.`in`).useLocale(Locale.UK)

    return scan
}


fun cerrarScanner(scan : Scanner) {
    scan.close()
}

