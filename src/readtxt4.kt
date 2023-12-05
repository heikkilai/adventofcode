package main.kotlin.directDatastoreAccess

import java.io.File

fun main() {
    doStuff()
}

private fun doStuff() {

    val setCubes = mutableMapOf<Int, Int>()
    val readLines = File("input4.txt").readLines()
    readLines.mapIndexed { index, line ->
        val gameAndSets = line.split(":")
        val sets = gameAndSets[1].split("|")

        val winningList = sets[0].split(" ").filter { it.isNotBlank() }
        val checkList = sets[1].split(" ").filter { it.isNotBlank() }
        var checks = 0
        checkList.forEach {
            if (winningList.contains(it)) {
                checks +=1
            }
        }
        setCubes.putIfAbsent(index, 1)
        for (i in 1..checks) {
            val next = index + i
            if (next < readLines.size) {
                setCubes.putIfAbsent(next, 1)
                setCubes.compute(next)  { k, v -> v!!.plus(setCubes[index]!!) }
            }
        }
        println(checks)
    }
    var power = 0
    setCubes.values.forEach { power+=it }
    println(power)
}
