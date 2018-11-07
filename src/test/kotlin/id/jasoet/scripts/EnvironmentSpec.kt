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

import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldNotBeNullOrBlank
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertFailsWith

object EnvironmentSpec : Spek({
    describe("Environment Helper") {
        context("Decode Base64") {
            it("return correct value from base64 encoded string") {
                val encodedString = "U3RyaW5nIGZvciBUZXN0IFB1cnBvc2U="
                val expectedString = "String for Test Purpose"
                encodedString.base64Decode().shouldEqual(expectedString)
            }
        }
        context("Getting the Optional Environment") {
            val expectedValue = "SAMPLE_PROP_VALUE"
            System.setProperty("SAMPLE_PROPERTY", expectedValue)
            it("return correct value") {
                val envValue = optionalEnv("SAMPLE_PROPERTY")
                envValue.shouldNotBeNull()
            }
            it("return null for non existing") {
                val envValue = optionalEnv("SAMPLE_NON_EXISTING")
                envValue.shouldBeNull()
            }
        }

        context("Getting the Mandatory Environment") {
            val expectedValue = "SAMPLE_PROP_VALUE"
            System.setProperty("SAMPLE_PROPERTY", expectedValue)
            it("return correct value") {
                val envValue = mandatoryEnv("SAMPLE_PROPERTY")
                envValue.shouldNotBeNullOrBlank()
            }
            it("throws exception for non existing value") {
                assertFailsWith<IllegalArgumentException> {
                    mandatoryEnv("SAMPLE_NON_EXISTING")
                }
            }
        }
    }
})
