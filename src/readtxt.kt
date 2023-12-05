package main.kotlin.directDatastoreAccess

import java.io.File

fun main() {
    doStuff()
}

val numbers = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)

private fun doStuff() {
    var i=0
    File("input.txt").readLines().map {
        var first = ""
        var last = ""
        var replace = it

        for (i in replace.toCharArray().indices) {
            numbers.forEach { (key, value) ->
                if (replace.startsWith(key, i)) {
                    replace = replace.replaceRange(i, i+1, value)
                }
            }
        }
        replace.toCharArray().forEach {
            if (it.isDigit()){
                if (first == "") {
                    first = it.toString()
                }
                last = it.toString()
            }
        }
        println(it)
        println(replace)
        println("$first $last")
        i += (first + last).toInt()
    }
    println(i)
}
