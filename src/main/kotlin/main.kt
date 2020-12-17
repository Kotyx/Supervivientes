import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() {
    comenzar()
    Thread.sleep(80000)
}
val mutex = Mutex()
fun comenzar(){

    val CUBOS_NECESARIOS = 4
    val LENA_NECESARIA  = 2
    val RAMA_NECESARIA  = 1
    val COMIDA_NECESARIA  = 1

    var agua=AmigoA()
    var lena=AmigoB()
    var reservas=AmigoC()
    if(agua==CUBOS_NECESARIOS && lena==LENA_NECESARIA && reservas.ramas==RAMA_NECESARIA && reservas.comida==COMIDA_NECESARIA){
        println("Barca construida y aprovisionada con exito")
    }else{
        println("Error")
    }

}
fun AmigoA(): Int {
    var cubosActuales = 0
    GlobalScope.launch {
        for(i in 0..3){
            println("El amigo A va a por un cubo de agua")
            delay(4000)
            println("El amigo A a vuelto con un cubo de agua")
            cubosActuales++
            println("El amigo A quiere descansar")
            hamaca("Amigo A")
            println("El amigo A deja de descansar")
        }
    }
    return cubosActuales
}
fun AmigoB(): Int {
    var lenaActual = 0
    GlobalScope.launch {
        for(i in 0..1){
            println("El amigo B va a por leña")
            hacha("Amigo B")
            println("El amigo B vuelve con leña")
            lenaActual++
            println("El amigo B quiere descansar")
            hamaca("Amigo B")
            println("El amigo B deja de descansar")
        }
    }
    return lenaActual
}
class Reserva(var ramas: Int,var comida: Int)

fun AmigoC(): Reserva {
    var ramasActuales = 0
    var comidaActual = 0
    GlobalScope.launch {
        println("El amigo C va por ramas")
        delay(3000)
        println("El amigo C vuelve con ramas")
        ramasActuales++
        println("El amigo C va a cazar")
        hacha("Amigo C")
        println("El amigo C vuelve de cazar")
        comidaActual++
    }
    val reserva= Reserva(ramasActuales,comidaActual)
    return reserva
}
suspend fun hamaca(amigo:String){
    mutex.withLock {
        if(amigo.compareTo("Amigo A")==0) {
            println("El " + amigo + " se tumba en la hamaca")
            delay(1000)
            println("El "+ amigo +" se levanta de la hamaca")
        }else{
            println("El " + amigo + " se tumba en la hamaca")
            delay(3000)
            println("El "+ amigo +" se levanta de la hamaca")
        }
    }
}
suspend fun hacha(amigo:String){
    mutex.withLock {
        if(amigo.compareTo("Amigo B")==0) {
            println("El " + amigo + " coge el hacha")
            delay(5000)
            println("El "+ amigo +" deja el hacha")
        }else{
            println("El " + amigo + " coge el hacha")
            delay(4000)
            println("El "+ amigo +" deja el hacha")
        }
    }
}