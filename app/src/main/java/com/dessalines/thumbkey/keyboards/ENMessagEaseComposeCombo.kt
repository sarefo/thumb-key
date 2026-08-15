@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.dessalines.thumbkey.keyboards

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.dessalines.thumbkey.textprocessors.ComposeComboProcessor
import com.dessalines.thumbkey.utils.*
import com.dessalines.thumbkey.utils.ColorVariant.*
import com.dessalines.thumbkey.utils.FontSizeVariant.*
import com.dessalines.thumbkey.utils.KeyAction.*
import com.dessalines.thumbkey.utils.SwipeNWay.*

// "english messagease compose", reworked around the compose key.
//
// The dead keys for ~ ^ ° " $ and ¿¡ are gone. Those characters are now plain keys that type
// themselves, because a dead key makes its own symbol awkward to reach: it only emits the bare
// character when followed by a space, which is a poor trade for a symbol people type directly.
//
// Eight diacritics remain as dead keys, since they are worth the keystroke saved. They are
// grouped along the top edge of A, N and I so they read as one block rather than as stray keys,
// with each row reading left to right as corner, top, corner. The cedilla is the exception and
// sits below A, since a cedilla hangs under its letter. Together they cover German, French,
// Spanish, Romanian and the caron languages.
//
// They are drawn with the spacing-modifier glyphs rather than plain ASCII, so the tilde and
// circumflex dead keys are visibly not the ordinary ~ and ^ that sit together on T. The cedilla
// uses a dotted circle, the standard way of showing a combining mark, because the bare glyph
// sits on the baseline and clips out of the key.
//
// They use NormalizeLastKey, which appends a combining mark and normalizes, so they work on any
// letter with a precomposed form rather than only the ones in a hand-written table.
//
// Everything else goes through the compose key (♫) on the top-left of A: ♫ o c for ©,
// ♫ ~ ~ for ≈, ♫ = = for ≡.

private val COMPOSE_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("♫"),
        action = StartComposeCombo,
        color = MUTED,
    )

private val GRAVE_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("`"),
        action = NormalizeLastKey("\u0300"),
        color = MUTED,
    )

private val ACUTE_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("´"),
        action = NormalizeLastKey("\u0301"),
        color = MUTED,
    )

private val DIAERESIS_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("¨"),
        action = NormalizeLastKey("\u0308"),
        color = MUTED,
    )

private val CEDILLA_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("◌̧"),
        action = NormalizeLastKey("\u0327"),
        color = MUTED,
    )

private val TILDE_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("˜"),
        action = NormalizeLastKey("\u0303"),
        color = MUTED,
    )

private val CARON_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("ˇ"),
        action = NormalizeLastKey("\u030c"),
        color = MUTED,
    )

private val CIRCUMFLEX_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("ˆ"),
        action = NormalizeLastKey("\u0302"),
        color = MUTED,
    )

private val BREVE_KEY =
    KeyC(
        display = KeyDisplay.TextDisplay("˘"),
        action = NormalizeLastKey("\u0306"),
        color = MUTED,
    )

val KB_EN_MESSAGEASE_COMPOSE_COMBO_MAIN =
    KeyboardC(
        listOf(
            listOf(
                KeyItemC(
                    center = KeyC("a", size = LARGE),
                    topLeft = COMPOSE_KEY,
                    top = DIAERESIS_KEY,
                    topRight = TILDE_KEY,
                    bottom = CEDILLA_KEY,
                    bottomRight = KeyC("v"),
                    right = KeyC("-", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("n", size = LARGE),
                    bottom = KeyC("l"),
                    topLeft = GRAVE_KEY,
                    top = CIRCUMFLEX_KEY,
                    topRight = ACUTE_KEY,
                    right = KeyC("!", color = MUTED),
                    bottomRight = KeyC("\\", color = MUTED),
                    bottomLeft = KeyC("/", color = MUTED),
                    left = KeyC("+", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("i", size = LARGE),
                    topLeft = BREVE_KEY,
                    top = CARON_KEY,
                    bottomLeft = KeyC("x"),
                    left = KeyC("?", color = MUTED),
                    topRight = KeyC("$", color = MUTED),
                    bottomRight = KeyC("€", color = MUTED),
                    bottom = KeyC("=", color = MUTED),
                ),
                EMOJI_KEY_ITEM,
            ),
            listOf(
                KeyItemC(
                    center = KeyC("h", size = LARGE),
                    right = KeyC("k"),
                    topLeft = KeyC("{", color = MUTED),
                    topRight = KeyC("%", color = MUTED),
                    bottomRight = KeyC("_", color = MUTED),
                    bottomLeft = KeyC("[", color = MUTED),
                    left = KeyC("(", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("o", size = LARGE),
                    topLeft = KeyC("q"),
                    top = KeyC("u"),
                    topRight = KeyC("p"),
                    right = KeyC("b"),
                    bottomRight = KeyC("j"),
                    bottom = KeyC("d"),
                    bottomLeft = KeyC("g"),
                    left = KeyC("c"),
                ),
                KeyItemC(
                    center = KeyC("r", size = LARGE),
                    left = KeyC("m"),
                    top =
                        KeyC(
                            display = KeyDisplay.IconDisplay(Icons.Outlined.ArrowDropUp),
                            action = ToggleShiftMode(true),
                            swipeReturnAction = ToggleCurrentWordCapitalization(true),
                            color = MUTED,
                        ),
                    topLeft = KeyC("|", color = MUTED),
                    topRight = KeyC("}", color = MUTED),
                    right = KeyC(")", color = MUTED),
                    bottom =
                        KeyC(
                            ToggleShiftMode(false),
                            swipeReturnAction = ToggleCurrentWordCapitalization(false),
                        ),
                    bottomRight = KeyC("]", color = MUTED),
                    bottomLeft = KeyC("@", color = MUTED),
                ),
                NUMERIC_KEY_ITEM,
            ),
            listOf(
                KeyItemC(
                    center = KeyC("t", size = LARGE),
                    topRight = KeyC("y"),
                    topLeft = KeyC("~", color = MUTED),
                    top = KeyC("^", color = MUTED),
                    right = KeyC("*", color = MUTED),
                    bottomRight = KeyC("\t", displayText = "⇥", color = MUTED),
                    left = KeyC("<", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("e", size = LARGE),
                    topLeft = KeyC("\"", color = MUTED),
                    top = KeyC("w"),
                    topRight = KeyC("'", color = MUTED),
                    right = KeyC("z"),
                    bottomRight = KeyC(":", color = MUTED),
                    bottom = KeyC(".", color = MUTED),
                    bottomLeft = KeyC(",", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("s", size = LARGE),
                    topLeft = KeyC("f"),
                    top = KeyC("&", color = MUTED),
                    topRight = KeyC("°", color = MUTED),
                    right = KeyC(">", color = MUTED),
                    bottomLeft = KeyC(";", color = MUTED),
                    left = KeyC("#", color = MUTED),
                ),
                BACKSPACE_KEY_ITEM,
            ),
            listOf(
                SPACEBAR_KEY_ITEM,
                RETURN_KEY_ITEM,
            ),
        ),
    )

val KB_EN_MESSAGEASE_COMPOSE_COMBO_SHIFTED =
    KeyboardC(
        listOf(
            listOf(
                KeyItemC(
                    center = KeyC("A", size = LARGE),
                    topLeft = COMPOSE_KEY,
                    top = DIAERESIS_KEY,
                    topRight = TILDE_KEY,
                    bottom = CEDILLA_KEY,
                    bottomRight = KeyC("V"),
                    right = KeyC("-", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("N", size = LARGE),
                    bottom = KeyC("L"),
                    topLeft = GRAVE_KEY,
                    top = CIRCUMFLEX_KEY,
                    topRight = ACUTE_KEY,
                    right = KeyC("!", color = MUTED),
                    bottomRight = KeyC("\\", color = MUTED),
                    bottomLeft = KeyC("/", color = MUTED),
                    left = KeyC("+", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("I", size = LARGE),
                    topLeft = BREVE_KEY,
                    top = CARON_KEY,
                    bottomLeft = KeyC("X"),
                    left = KeyC("?", color = MUTED),
                    topRight = KeyC("$", color = MUTED),
                    bottomRight = KeyC("€", color = MUTED),
                    bottom = KeyC("=", color = MUTED),
                ),
                EMOJI_KEY_ITEM,
            ),
            listOf(
                KeyItemC(
                    center = KeyC("H", size = LARGE),
                    right = KeyC("K"),
                    topLeft = KeyC("{", color = MUTED),
                    topRight = KeyC("%", color = MUTED),
                    bottomRight = KeyC("_", color = MUTED),
                    bottomLeft = KeyC("[", color = MUTED),
                    left = KeyC("(", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("O", size = LARGE),
                    topLeft = KeyC("Q"),
                    top = KeyC("U"),
                    topRight = KeyC("P"),
                    right = KeyC("B"),
                    bottomRight = KeyC("J"),
                    bottom = KeyC("D"),
                    bottomLeft = KeyC("G"),
                    left = KeyC("C"),
                ),
                KeyItemC(
                    center = KeyC("R", size = LARGE),
                    left = KeyC("M"),
                    bottom =
                        KeyC(
                            display = KeyDisplay.IconDisplay(Icons.Outlined.ArrowDropDown),
                            action = ToggleShiftMode(false),
                            swipeReturnAction = ToggleCurrentWordCapitalization(false),
                            color = MUTED,
                        ),
                    top =
                        KeyC(
                            display = KeyDisplay.IconDisplay(Icons.Outlined.KeyboardCapslock),
                            capsModeDisplay = KeyDisplay.IconDisplay(Icons.Outlined.Copyright),
                            action = ToggleCapsLock,
                            swipeReturnAction = ToggleCurrentWordCapitalization(true),
                            color = MUTED,
                        ),
                    topLeft = KeyC("|", color = MUTED),
                    topRight = KeyC("}", color = MUTED),
                    right = KeyC(")", color = MUTED),
                    bottomRight = KeyC("]", color = MUTED),
                    bottomLeft = KeyC("@", color = MUTED),
                ),
                NUMERIC_KEY_ITEM,
            ),
            listOf(
                KeyItemC(
                    center = KeyC("T", size = LARGE),
                    topRight = KeyC("Y"),
                    topLeft = KeyC("~", color = MUTED),
                    top = KeyC("^", color = MUTED),
                    right = KeyC("*", color = MUTED),
                    left = KeyC("<", color = MUTED),
                    bottomRight = KeyC("\t", displayText = "⇥", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("E", size = LARGE),
                    topLeft = KeyC("\"", color = MUTED),
                    top = KeyC("W"),
                    topRight = KeyC("'", color = MUTED),
                    right = KeyC("Z"),
                    bottomRight = KeyC(":", color = MUTED),
                    bottom = KeyC(".", color = MUTED),
                    bottomLeft = KeyC(",", color = MUTED),
                ),
                KeyItemC(
                    center = KeyC("S", size = LARGE),
                    topLeft = KeyC("F"),
                    top = KeyC("&", color = MUTED),
                    topRight = KeyC("°", color = MUTED),
                    right = KeyC(">", color = MUTED),
                    bottomLeft = KeyC(";", color = MUTED),
                    left = KeyC("#", color = MUTED),
                ),
                BACKSPACE_KEY_ITEM,
            ),
            listOf(
                SPACEBAR_KEY_ITEM,
                RETURN_KEY_ITEM,
            ),
        ),
    )

val KB_EN_MESSAGEASE_COMPOSE_COMBO: KeyboardDefinition =
    KeyboardDefinition(
        title = "english messagease compose combo",
        modes =
            KeyboardDefinitionModes(
                main = KB_EN_MESSAGEASE_COMPOSE_COMBO_MAIN,
                shifted = KB_EN_MESSAGEASE_COMPOSE_COMBO_SHIFTED,
                numeric = KB_EN_MESSAGEASE_NUMERIC,
            ),
        settings =
            KeyboardDefinitionSettings(
                autoCapitalizers = arrayOf(::autoCapitalizeI, ::autoCapitalizeIApostrophe),
                textProcessor = ComposeComboProcessor(),
            ),
    )
