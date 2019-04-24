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

import id.jasoet.scripts.base64Decode
import id.jasoet.scripts.createDirs
import id.jasoet.scripts.homeDir
import id.jasoet.scripts.writeToFile

object Ssh {
    private const val defaultConfig = "Host *\nStrictHostKeyChecking no\nUserKnownHostsFile=/dev/null"
    private val defaultDir = "${homeDir()}/.ssh"

    operator fun invoke(
            encodedCreds: String,
            config: String = defaultConfig,
            directory: String = defaultDir
    ): Pair<String, String> {
        directory.createDirs()
        val creds = encodedCreds.base64Decode()
        val fileName = "$directory/id_rsa"
        creds.writeToFile(fileName)

        val configFile = "$directory/config"
        config.writeToFile(configFile)

        return fileName to configFile
    }
}
