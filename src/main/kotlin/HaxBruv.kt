

fun main() {
    //pits are from closest to stores to farthest
    var ava = ConsoleAvalanche(allyStore = 0, enemyStore = 0,
                               allyPits = intArrayOf(4, 4, 4, 4, 4, 4),
                               enemyPits = intArrayOf(4, 4, 4, 4, 4, 4))
    var ans = ava.helpMe(depth = 28)
//    var ans = ava.helpMeSlowAndPrimitive(depth = 8)

//    var cap = ConsoleCapture(allyStore = 0, enemyStore = 0,
//                             allyPits = intArrayOf(0, 0, 0, 0, 0, 0),
//                             enemyPits = intArrayOf(0, 0, 0, 0, 0, 0))
//    var ans = cap.helpMe()

    //todo: I wonder if it would be any better to have it prioritize getting more turns (ie reward it extra for an extra turn). If you want to try,
    // have a way for them to face off on the same random maps getting each to start and run a bunch and keep track of scores

//    println(ans.asSequence().map { it.first + 1 }.toList())

    println(intArrayOf(6, 3, 2, 5, 1, 6, 1, 6, 5, 6, 5, 2, 5, 1, 3, 4, 5, 4, 6, 6, 5, 6, 1, 6, 3, 5, 6).asSequence().map { 7 - it }.toList())

//    for (i in 1..30) {
//        ava = ConsoleAvalanche(allyStore = 0, enemyStore = 0,
//                               allyPits = intArrayOf(4, 4, 4, 4, 4, 4),
//                               enemyPits = intArrayOf(4, 4, 4, 4, 4, 4))
//        println("Depth: $i\nSuggestion: ${ava.helpMeSlowAndPrimitive(i).asSequence().map { it.first + 1 }.toList()}\n\n")
//    }

}


/*
Depth: 1
Suggestion: [3]


Depth: 2
Suggestion: [5, 5, 4, 6, 5, 4, 2]


Depth: 3
Suggestion: [5, 5, 4, 1, 6, 5, 4, 2]


Depth: 4
Suggestion: [5, 5, 4, 1, 6, 4, 5, 2, 4, 3]


Depth: 5
Suggestion: [1, 4, 1, 2, 3, 1, 2, 4, 3]


Depth: 6
Suggestion: [4, 2, 2, 2, 5, 2, 1, 6, 1, 3, 2, 1, 3, 1]


Depth: 7
Suggestion: [5, 6, 3, 1, 3, 1, 1, 6, 4, 1]


Depth: 8
Suggestion: [5, 6, 3, 1, 3, 1, 1, 2, 1, 6, 4, 1]


Depth: 9
Suggestion: [5, 5, 4, 1, 2, 1, 6, 4, 2, 1, 2, 1, 1, 4, 3, 6, 1, 3, 1, 2]


Depth: 10
Suggestion: [1, 1, 4, 4, 2, 5, 3, 5, 4, 2, 5, 3, 1, 1, 2, 1, 2, 1, 3]


Depth: 11
Suggestion: [5, 5, 4, 1, 2, 1, 6, 4, 2, 1, 2, 1, 1, 4, 3, 6, 1, 3, 1, 2]


Depth: 12
Suggestion: [1, 4, 5, 2, 6, 6, 2, 1, 2, 5, 5, 1, 2, 1, 2, 4, 1, 3, 5, 1, 5, 1, 1, 3]


Depth: 13
Suggestion: [1, 4, 5, 2, 6, 6, 2, 1, 2, 5, 5, 1, 2, 1, 2, 4, 1, 3, 5, 1, 5, 1, 1, 4]


Depth: 14
Suggestion: [1, 1, 4, 4, 2, 5, 2, 3, 1, 5, 1, 2, 4, 4, 2, 6, 2, 1, 1, 5, 1, 4, 2]


Answer: [1, 4, 5, 2, 6, 1, 6, 1, 2, 1, 2, 5, 2, 6, 4, 3, 2, 3, 1, 1, 2, 1, 6, 1, 4, 1, 2, 1]
 */