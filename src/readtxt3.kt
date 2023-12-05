package main.kotlin.directDatastoreAccess

import java.io.File

fun main() {
    doStuff()
}

private val tulemuseList = mutableListOf<Symbol>()
private fun doStuff() {
    val map = mutableMapOf<Int, List<Symbol>>()

    var tulemus = 0
    File("input3.txt").readLines().forEachIndexed { index, line ->
        map.put(index, line.toCharArray().map {
            if (it == '.') {
                Symbol(it.toString(), which = "punkt")
            } else if (it.isDigit()) {
                Symbol(it.toString(), which = "number")
            }
            else {
                Symbol(it.toString(), which = "symbol")
            }
        })
    }

    map.forEach { reaNumber, rida ->
        var number = ""
        var numberStart = 0
        rida.forEachIndexed { index, symbol ->
            if (symbol.which== "number") {
                number += symbol.value
                if (numberStart == 0) {
                    numberStart= index
                }
            }
            if ((number != "" && symbol.which!= "number") || (number != "" && index == rida.size - 1)) {
                val reaAlgus = if (numberStart > 0) numberStart - 1 else 0
                val reaLopp = index
                val eelmineRida = if (reaNumber > 0) reaNumber - 1 else 0
                val nextRida = if (reaNumber == map.keys.size - 1) reaNumber else reaNumber + 1
                if (otsi(reaAlgus, reaLopp, eelmineRida, nextRida, map, number.toInt())) {
                    tulemus += number.toInt()
                }
                number = ""
                numberStart = 0
            }

        }
    }
    println(tulemus)
    var tulemus2 = 0
    tulemuseList.filter { it.tulemus.size == 2 }.forEach {
        tulemus2 += it.tulemus[0] * it.tulemus[1]
    }
    println(tulemus2)
}

fun otsi(reaAlgus: Int, reaLopp: Int, eelmineRida: Int, nextRida: Int, map: MutableMap<Int, List<Symbol>>, leitud: Int): Boolean {
    for (i in eelmineRida..nextRida) {
        for (j in reaAlgus..reaLopp) {
            if (map.get(i)!!.get(j).which == "symbol") {
                println("leitud $leitud")
                if (map.get(i)!!.get(j).value == "*") {
                    map.get(i)!!.get(j).tulemus.add(leitud)
                    if (map.get(i)!!.get(j).tulemus.size == 2) {

                        tulemuseList.add(map.get(i)!!.get(j))
                    }
                }
                return true
            }
        }
    }
    return false
}

data class Symbol(
    val value: Any,
    val which: String
) {
    var tulemus= mutableListOf<Int>()
}
