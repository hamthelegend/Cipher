import java.util.*

fun transpositionEncrypt(string: String, verbose: Boolean = false): String {
    val unspacedString = string.replace(Regex("[^a-zA-Z]"), "")
        .map { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }.joinToString("")

    val unencryptedChunks = unspacedString.chunked(4) { chunk ->
        chunk.padEnd(4, 'Z')
    }

    if (verbose) {
        println("4-character blocks:")
        println(unencryptedChunks.joinToString(" "))
        println()

        println("1 -> 3")
        println("2 -> 1")
        println("3 -> 4")
        println("4 -> 2")
    }

    val encryptedChunks = unencryptedChunks.map { unencryptedChunk ->
        val encryptedChunk = Array(4) { ' ' }
        encryptedChunk[0] = unencryptedChunk[1]
        encryptedChunk[1] = unencryptedChunk[3]
        encryptedChunk[2] = unencryptedChunk[0]
        encryptedChunk[3] = unencryptedChunk[2]
        encryptedChunk.joinToString("")
    }

    if (verbose) {
        println(encryptedChunks.joinToString(" "))
        println()
    }

    val cyphertext = encryptedChunks.joinToString("")

    if (verbose) println("$string --\uD83D\uDD12-> $cyphertext")
    return cyphertext
}