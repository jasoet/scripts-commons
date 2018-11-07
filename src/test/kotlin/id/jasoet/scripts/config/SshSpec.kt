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

package id.jasoet.scripts.config

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.FileReader
import java.util.Base64


object SshSpec : Spek({
    describe("Ssh Config Helper") {
        val configDir = "/tmp/sshdirtest"
        val configContent = "This is config default content!"
        val keyContent = "KEYCONTEXTSAMPLEFORTEST"
        val encodedKey = Base64.getEncoder().encodeToString(keyContent.toByteArray())
        context("Initialize Ssh Helper") {
            val (privateKeyPath, sshConfigPath) = Ssh(encodedKey, configContent, configDir)
            it("should produce correct privateKey file") {
                val privateKeyContent = FileReader(privateKeyPath).readText()
                privateKeyContent.shouldBeEqualTo(keyContent)
            }
            it("should produce correct config file") {
                val configFileContent = FileReader(sshConfigPath).readText()
                configFileContent.shouldBeEqualTo(configContent)
            }
        }
    }
})
