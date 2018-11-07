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

object GCloud {
    private val defaultDir = "${homeDir()}/.gcloud"

    operator fun invoke(encodedCreds: String, directory: String = defaultDir): String {
        directory.createDirs()
        val creds = encodedCreds.base64Decode()
        val fileName = "$directory/gcloud-svc-acc-creds"
        creds.writeToFile(fileName)

        return fileName
    }
}

