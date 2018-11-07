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

import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object StringColorSpec : Spek({
    describe("String Color Helper") {
        val ANSI_RESET = "\u001B[0m"
        val ANSI_RED = "\u001B[31m"
        val ANSI_GREEN = "\u001B[32m"

        val ANSI_BLACK = "\u001B[30m"
        val ANSI_YELLOW = "\u001B[33m"
        val ANSI_BLUE = "\u001B[34m"
        val ANSI_PURPLE = "\u001B[35m"
        val ANSI_CYAN = "\u001B[36m"
        val ANSI_WHITE = "\u001B[37m"

        context("print color") {
            it("red should return correct string") {
                val value = "Test"
                value.red().shouldEqual("$ANSI_RED$value$ANSI_RESET")
            }
            it("green should return correct string") {
                val value = "Test"
                value.green().shouldEqual("$ANSI_GREEN$value$ANSI_RESET")
            }
            it("BLACK should return correct string") {
                val value = "Test"
                value.black().shouldEqual("$ANSI_BLACK$value$ANSI_RESET")
            }
            it("yellow should return correct string") {
                val value = "Test"
                value.yellow().shouldEqual("$ANSI_YELLOW$value$ANSI_RESET")
            }
            it("blue should return correct string") {
                val value = "Test"
                value.blue().shouldEqual("$ANSI_BLUE$value$ANSI_RESET")
            }
            it("purple should return correct string") {
                val value = "Test"
                value.purple().shouldEqual("$ANSI_PURPLE$value$ANSI_RESET")
            }
            it("CYAN should return correct string") {
                val value = "Test"
                value.cyan().shouldEqual("$ANSI_CYAN$value$ANSI_RESET")
            }
            it("white should return correct string") {
                val value = "Test"
                value.white().shouldEqual("$ANSI_WHITE$value$ANSI_RESET")
            }
        }
    }
})
