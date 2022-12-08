import java.awt.geom.Rectangle2D.intersect

fun main() {
    val input = readInput("day3")
    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

//    val result = input
//        .map { it.substring(0, it.length / 2) to it.substring((it.length / 2)) }
//        .map { (first, second) ->
//            first.toCharArray().toSet().intersect(second.toCharArray().toSet()).first()
//        }
//        .sumOf { letters.toCharArray().indexOf(it) + 1 }

    val result = input.chunked(3)
        .map { it.map { it.toCharArray().toSet() } }
        .map { it[0].intersect(it[1].intersect(it[2])).first() }
        .sumOf { letters.toCharArray().indexOf(it) + 1 }

    print(result)
}
