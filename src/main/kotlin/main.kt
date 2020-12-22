import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


fun main() {
    comenzar()

}
val mutex = Mutex()
class Reserva(var ramas: Int,var comida: Int)
fun comenzar(){

    val CUBOS_NECESARIOS = 4
    val LENA_NECESARIA  = 2
    val RAMA_NECESARIA  = 1
    val COMIDA_NECESARIA  = 1

    var agua :Deferred<Int>
    var lena :Deferred<Int>
    var reservas:Deferred<Reserva>

        GlobalScope.launch {
            agua=AmigoA()
            lena=AmigoB()
            reservas=AmigoC()
            delay(2000)
            if (agua.await() == CUBOS_NECESARIOS && lena.await() == LENA_NECESARIA && reservas.await().ramas == RAMA_NECESARIA && reservas.await().comida == COMIDA_NECESARIA) {
                println("Barca construida y aprovisionada con exito")
            } else {
                println("Error")
            }
        }


    Thread.sleep(50000)

}
suspend fun AmigoA() : Deferred<Int> {
    return GlobalScope.async() {
        var cont=0
        for(i in 0..3) {
            println("El amigo A va a por un cubo de agua")
            delay(4000)
            println("El amigo A a vuelto con un cubo de agua")
            cont++
            println("El amigo A quiere descansar")
            hamaca("Amigo A")
            println("El amigo A deja de descansar")
        }
        cont
    }

}
suspend fun AmigoB(): Deferred<Int> {
    val lenaActual = GlobalScope.async() {
        var cont = 0
        for (i in 0..1) {
            println("El amigo B va a por leña")
            hacha("Amigo B")
            println("El amigo B vuelve con leña")
            cont += 1
            println("El amigo B quiere descansar")
            hamaca("Amigo B")
            println("El amigo B deja de descansar")
        }
        cont
    }
    return lenaActual
}


fun AmigoC(): Deferred<Reserva> {
    return GlobalScope.async(){
        var contramas=0
        var contcomida=0
        println("El amigo C va por ramas")
        delay(3000)
        println("El amigo C vuelve con ramas")
        contramas+=1
        println("El amigo C va a cazar")
        hacha("Amigo C")
        println("El amigo C vuelve de cazar")
        contcomida+=1
        var reserva= Reserva(contramas,contcomida)
        reserva
    }
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