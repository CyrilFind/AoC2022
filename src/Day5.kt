import java.util.Stack

fun main() {
    val input = readInput("day5")
    val stacks = List(9) { Stack<String>() }
    val cratesRegex =
        """(?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) ) (?:\[(\w)\]| ( ) )""".toRegex()
    val moveRegex = """^move (\d+) from (\d+) to (\d+)""".toRegex()
    fun printStacks() {
        stacks.forEachIndexed { index, element -> println("${index + 1}: $element") }
        println("--------------------------------")
    }

    input.filter { it.matches(cratesRegex) }.map {
        cratesRegex.find(it)!!.destructured.toList()
            .mapIndexed { index, s -> if (index % 2 == 1) null else s }
            .filterNotNull()
    }.reversed().forEach { line ->
        line.forEachIndexed { index, element ->
            if (element.isNotEmpty()) stacks[index].add(element)
        }
    }
    println("init")
    printStacks()
//    part 1
//    input.filter { it.matches(moveRegex) }.forEach {
//        val (quantity, origin, destination) = moveRegex.find(it)!!.destructured
//        repeat(quantity.toInt()) {
//            println("$origin -> $destination")
//            if (stacks[origin.toInt() - 1].isEmpty()) return@repeat
//            val toMove = stacks[origin.toInt() - 1].pop()
//            stacks[destination.toInt() - 1].push(toMove)
//            printStacks()
//        }
//    }
    
    // part 2
    input.filter { it.matches(moveRegex) }.forEach { input ->
        val (quantity, origin, destination) = moveRegex.find(input)!!.destructured
        val toMove = mutableListOf<String>()
        repeat(quantity.toInt()) {
            println("$origin -> $destination")
            if (stacks[origin.toInt() - 1].isEmpty()) return@repeat
            toMove.add(stacks[origin.toInt() - 1].pop())
        }
        toMove.reversed().forEach { stacks[destination.toInt() - 1].push(it) }
        printStacks()
    }

    stacks.forEach { print(if(it.isEmpty()) "-" else it.peek()) }
}
