fun shiftEncrypt(string: String, key: Int, verbose: Boolean = false): String {
    val encryptedStringBuilder = StringBuilder()
    for (unencryptedChar in string) {
        val letter = unencryptedChar in 'A'..'Z' || unencryptedChar in 'a'..'z'

        if (letter) {
            val capital = unencryptedChar in 'A'..'Z'

            val referenceCode = (if (capital) 'A' else 'a').code

            val unencryptedNumber = unencryptedChar.code - referenceCode + 1
            val encryptedNumber = (unencryptedNumber + key - 1) % 26 + 1
            val encryptedCharacter = (encryptedNumber + referenceCode - 1).toChar()
            encryptedStringBuilder.append(encryptedCharacter)

            if (verbose) {
                println("'$unencryptedChar' --\uD83D\uDD12-> $encryptedCharacter")
            }
        } else {
            if (verbose) {
                println("'$unencryptedChar' is not a letter and was not ignored.")
            }
        }
    }
    println()
    val encryptedString = encryptedStringBuilder.toString()
    if (verbose) println("$string --\uD83D\uDD12-> $encryptedString")
    return encryptedString
}