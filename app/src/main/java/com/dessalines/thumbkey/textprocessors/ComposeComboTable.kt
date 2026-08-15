package com.dessalines.thumbkey.textprocessors

/**
 * Sequence table for the "compose combo" system, modeled on the X11 / Linux compose key.
 *
 * Unlike [com.dessalines.thumbkey.utils.KeyAction.ComposeLastKey], which rewrites the single
 * character before the cursor, these sequences are typed *after* a compose key and may be any
 * length. That is what makes characters such as © reachable: they are not a diacritic applied
 * to a base letter, so a dead key cannot express them.
 *
 * The table must stay **prefix-free**: no sequence may be a proper prefix of another, because
 * a match is committed as soon as it is found. `--` is therefore not a sequence of its own,
 * even though `---` and `--.` both exist.
 *
 * Sequences follow the X11 `Compose` file where one exists, so muscle memory carries over from
 * desktop Linux. See
 * https://gitlab.freedesktop.org/xorg/lib/libx11/-/blob/master/nls/en_US.UTF-8/Compose.pre
 */
object ComposeComboTable {
    val sequences: Map<String, String> =
        buildMap {
            // Legal and typographic symbols
            put("oc", "©")
            put("or", "®")
            put("tm", "™")
            put("so", "§")
            put("p!", "¶")
            put("%o", "‰")
            put("...", "…")
            put("---", "—")
            put("--.", "–")
            put("<<", "«")
            put(">>", "»")
            put("!!", "¡")
            put("??", "¿")

            // Mathematics
            put("+-", "±")
            put("-:", "÷")
            put("xx", "×")
            put("!=", "≠")
            put("<=", "≤")
            put(">=", "≥")
            put("~~", "≈")
            put("00", "∞")
            put("oo", "°")

            // Arrows
            put("->", "→")
            put("<-", "←")
            put("<>", "↔")

            // Fractions
            put("12", "½")
            put("13", "⅓")
            put("23", "⅔")
            put("14", "¼")
            put("34", "¾")

            // Super- and subscripts
            put("^0", "⁰")
            put("^1", "¹")
            put("^2", "²")
            put("^3", "³")
            put("^n", "ⁿ")
            put("_1", "₁")
            put("_2", "₂")

            // Currency
            put("e=", "€")
            put("E=", "€")
            put("l-", "£")
            put("L-", "£")
            put("y=", "¥")
            put("Y=", "¥")
            put("c/", "¢")
            put("C/", "¢")

            // Ligatures and standalone letters
            put("ss", "ß")
            put("sz", "ß")
            put("SS", "ẞ")
            put("ae", "æ")
            put("AE", "Æ")
            put("oe", "œ")
            put("OE", "Œ")
            put("o/", "ø")
            put("O/", "Ø")
            put("aa", "å")
            put("AA", "Å")
            put(",c", "ç")
            put(",C", "Ç")

            // Diacritics, mark first, as on a desktop compose key. The dead keys already on this
            // layout produce the same letters in the opposite order (`n` then `~`), so both
            // habits work side by side.
            putDiacritic("\"", "aäAÄeëEËiïIÏoöOÖuüUÜyÿ")
            putDiacritic("'", "aáAÁeéEÉiíIÍoóOÓuúUÚyýYÝ")
            putDiacritic("`", "aàAÀeèEÈiìIÌoòOÒuùUÙ")
            putDiacritic("^", "aâAÂeêEÊiîIÎoôOÔuûUÛ")
            putDiacritic("~", "aãAÃnñNÑoõOÕ")
        }

    /** Every proper prefix of every sequence, used to decide whether to keep buffering. */
    private val prefixes: Set<String> =
        buildSet {
            sequences.keys.forEach { seq ->
                for (i in 1 until seq.length) {
                    add(seq.substring(0, i))
                }
            }
        }

    /** True when [buffer] could still grow into a valid sequence. */
    fun isPrefix(buffer: String): Boolean = prefixes.contains(buffer)

    /** The character [buffer] resolves to, or null if it is not a complete sequence. */
    fun match(buffer: String): String? = sequences[buffer]
}

/**
 * Adds one entry per base/accented pair, given as a flat "aäAÄ" style string. Purely to keep the
 * diacritic block readable.
 */
private fun MutableMap<String, String>.putDiacritic(
    mark: String,
    pairs: String,
) {
    pairs.chunked(2).forEach { pair ->
        put(mark + pair[0], pair[1].toString())
    }
}
