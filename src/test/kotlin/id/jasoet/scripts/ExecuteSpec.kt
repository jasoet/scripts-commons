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

import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ExecuteSpec : Spek({
    describe("Process Helper") {
        context("Execute to File") {
            it("create stdout and stderr file") {
                val result = Execute {
                    println("TESTXXXXXXXXXXXXXXXXX")
                    println("LAKDHAKAIDKDNAKAO")
                    println("LAKJKSJKAJSA")
                    throw IllegalAccessException("Hore")
                }

                result.success.shouldBeFalse()
                result.exception.shouldBeNull()
            }
        }
    }
})
