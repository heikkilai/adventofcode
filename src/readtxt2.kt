package main.kotlin.directDatastoreAccess

import java.io.File

fun main() {
    doStuff()
}

private fun doStuff() {
    var gameIds=0
    val gameCubes = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )
    var power = 0
    File("input2.txt").readLines().map {line ->
        var valid = true
        val gameAndSets = line.split(":")
        val game = gameAndSets[0]
        val sets = gameAndSets[1].split(";")
        val minCubes = mutableMapOf(
            "red" to 0,
            "green" to 0,
            "blue" to 0
        )
        sets.forEach { set->
            val cubes = set.split(",")
            val setCubes = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )
            cubes.forEach { cube ->
                val amountAndColor = cube.split(" ").filter { it.isNotBlank() }
                setCubes.compute(amountAndColor[1]) { k, v -> v!!.plus(amountAndColor[0].toInt()) }
                minCubes.compute(amountAndColor[1]) { k, v -> if (v!! < amountAndColor[0].toInt()) amountAndColor[0].toInt() else v  }
            }
            println("$setCubes")

            gameCubes.forEach { key, value ->
                if (setCubes[key]!! > value) {
                    println("$key has more")
                    valid = false
                }
            }
        }
        if (valid) {
            gameIds += game.split(" ")[1].toInt()
            println("is valid $game")
            println("Min $minCubes")
        }
        var cubePower = 1
        minCubes.values.forEach {
            cubePower *= it
        }
        println("cube power: $cubePower")
        power += cubePower

    }
    println(gameIds)
    println(power)
}
