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
import java.io.File
import java.io.FileReader
import java.util.Base64


object ChefSpec : Spek({
    describe("Chef Config Helper") {
        val serverUrl = "http://chef.server.jasoet.vpc/organization/common"
        val serverIp = "10.11.12.13"
        val serverHost = "chef.server.jasoet.vpc"
        val adminUsername = "admin"
        val adminKey = "ADMINKEYTEST"
        val encodedAdminKey = Base64.getEncoder().encodeToString(adminKey.toByteArray())

        val chefConfigDirectory = "/tmp/chefconfigdir"
        val hostFile = "/tmp/hostsfile"

        context("Initialize Chef Helper") {
            File(hostFile).delete()
            val (adminKeyPath, knifeConfigPath) = Chef(
                    serverUrl,
                    serverIp,
                    serverHost,
                    encodedAdminKey,
                    adminUsername,
                    chefConfigDirectory,
                    hostFile
            )
            it("should produce correct admin key") {
                val adminKeyContent = FileReader(adminKeyPath).readText()
                adminKeyContent.shouldBeEqualTo(adminKey)
            }

            it("should produce correct knife config") {
                val knifeConfigExpected = """
                    current_dir = File.dirname(__FILE__)
                    log_level                :info
                    log_location             STDOUT
                    node_name                '$adminUsername'
                    client_key               '$adminKeyPath'
                    chef_server_url          '$serverUrl'
                    """.trimIndent()

                val knifeConfigContent = FileReader(knifeConfigPath).readText()
                knifeConfigContent.shouldBeEqualTo(knifeConfigExpected)
            }

            it("should produce correct host file ") {
                val hostFileContent = FileReader(hostFile).readText()
                hostFileContent.shouldBeEqualTo("$serverIp $serverHost")
            }
        }
//        context("Execute ssl fetch") {
//            it("should produce redirect output to file") {
//                File(ProcessHelper.tmpStdOut).delete()
//                File(ProcessHelper.tmpStdErr).delete()
//                Chef.executeSslFetch()
//                assertTrue(File(ProcessHelper.tmpStdOut).exists(), "${ProcessHelper.tmpStdOut} should be exists")
//                assertTrue(File(ProcessHelper.tmpStdErr).exists(), "${ProcessHelper.tmpStdErr} should be exists")
//            }
//        }
    }
})
