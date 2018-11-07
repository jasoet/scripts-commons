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

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File
import kotlin.test.assertTrue

object ProcessHelperSpec : Spek({
    describe("Process Helper") {
        context("Execute to File") {
            it("create stdout and stderr file") {
                val command = "ls -alh /home/"
                File(ProcessHelper.tmpStdOut).delete()
                File(ProcessHelper.tmpStdErr).delete()
                ProcessHelper.executeToTempFile(command)
                assertTrue(File(ProcessHelper.tmpStdOut).exists(), "${ProcessHelper.tmpStdOut} should be exists")
                assertTrue(File(ProcessHelper.tmpStdErr).exists(), "${ProcessHelper.tmpStdErr} should be exists")
            }
        }

        context("Output To File") {
            it("should create output file") {
                val outputPath = "/tmp/outputfilepath"
                File(outputPath).delete()
                ProcessHelper.outputToFile(outputPath) {
                    println("Some Content")
                    println("Some other content")
                    println("and some numbers")
                    println(43)
                    println(44)
                    println(24)
                }
                assertTrue(File(outputPath).exists(), "$outputPath should be exists")
            }
        }
    }
})
