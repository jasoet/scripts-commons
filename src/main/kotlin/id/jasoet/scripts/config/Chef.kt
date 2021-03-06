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

import id.jasoet.funkommand.execute
import id.jasoet.scripts.base64Decode
import id.jasoet.scripts.createDirs
import id.jasoet.scripts.homeDir
import id.jasoet.scripts.writeToFile

object Chef {
    operator fun invoke(
            serverUrl: String,
            serverIp: String,
            serverHost: String,
            encodedAdminKey: String,
            adminUsername: String,
            directory: String = "${homeDir()}/.chef",
            hostsFile: String = "/etc/hosts"
    ): Pair<String, String> {
        directory.createDirs()
        val adminKey = encodedAdminKey.base64Decode()
        val adminKeyPath = "$directory/$adminUsername.pem"
        adminKey.writeToFile(adminKeyPath)

        val knifeConfig = """
        current_dir = File.dirname(__FILE__)
        log_level                :info
        log_location             STDOUT
        node_name                '$adminUsername'
        client_key               '$adminKeyPath'
        chef_server_url          '$serverUrl'
        """.trimIndent()

        val knifeConfigPath = "$directory/knife.rb"
        knifeConfig.writeToFile(knifeConfigPath)

        val hostsContent = "$serverIp $serverHost"
        hostsContent.writeToFile(hostsFile, true)

        return adminKeyPath to knifeConfigPath
    }

    fun executeSslFetch() {
        val (statusCode, inputStream) = "knife ssl fetch".execute(output = System.out)
        if (statusCode != 0 && inputStream != null) {
            inputStream.use {
                it.copyTo(System.err)
            }
        }
    }
}
