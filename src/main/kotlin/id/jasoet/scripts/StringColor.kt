/*
 * Copyright (C)2018 - Deny Prasetyo <jasoet87@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package id.jasoet.scripts

private const val ANSI_RESET = "\u001B[0m"
private const val ANSI_RED = "\u001B[31m"
private const val ANSI_GREEN = "\u001B[32m"

private const val ANSI_BLACK = "\u001B[30m"
private const val ANSI_YELLOW = "\u001B[33m"
private const val ANSI_BLUE = "\u001B[34m"
private const val ANSI_PURPLE = "\u001B[35m"
private const val ANSI_CYAN = "\u001B[36m"
private const val ANSI_WHITE = "\u001B[37m"

internal fun String.color(code: String): String {
    return "$code$this$ANSI_RESET"
}

fun String.red(): String {
    return this.color(ANSI_RED)
}

fun String.green(): String {
    return this.color(ANSI_GREEN)
}

fun String.black(): String {
    return this.color(ANSI_BLACK)
}

fun String.yellow(): String {
    return this.color(ANSI_YELLOW)
}

fun String.blue(): String {
    return this.color(ANSI_BLUE)
}

fun String.purple(): String {
    return this.color(ANSI_PURPLE)
}

fun String.cyan(): String {
    return this.color(ANSI_CYAN)
}

fun String.white(): String {
    return this.color(ANSI_WHITE)
}
