
fun main() {
    val input = readInput("day4")
    
    val result = input.asSequence()
        .map { it.split(",") }
        .map { it[0].split("-") to it[1].split("-") }
        .map { (it.first[0].toInt()..it.first[1].toInt()) to  it.second[0].toInt()..it.second[1].toInt()}
        .count { it.first.intersect(it.second).isNotEmpty()  }
    
    print(result)
}
