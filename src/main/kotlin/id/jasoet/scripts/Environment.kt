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

import arrow.core.Try
import arrow.core.getOrElse
import java.io.File
import java.util.Base64

internal fun homeDir(): String {
    return System.getProperty("user.home")
}

internal fun String.base64Decode(): String {
    return String(Base64.getDecoder().decode(this))
}

internal fun String.createDirs(): Boolean {
    return File(this).mkdirs()
}

internal fun getSystemEnv(envKey: String): String? {
    return Try { System.getenv(envKey) }.getOrElse { null }
}

internal fun getSystemProperty(propertyKey: String): String? {
    return Try { System.getProperty(propertyKey) }.getOrElse { null }
}

fun optionalEnv(key: String): String? {
    return getSystemEnv(key) ?: getSystemProperty(key)
}

fun mandatoryEnv(key: String): String {
    return optionalEnv(key) ?: throw IllegalArgumentException("Env $key is Required!")
}

