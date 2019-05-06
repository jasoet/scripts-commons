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

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.PrintStream
import java.nio.file.Files
import java.util.concurrent.Executors

data class ExecutionResult(val success: Boolean, val out: File, val err: File, val exception: Throwable? = null) {
    operator fun invoke(operation: (File, File, Throwable?) -> Unit) {
        operation(out, err, exception)
    }

    fun print() {
        out.print(System.out)
        err.print(System.err)
        exception?.printStackTrace(System.err)
    }

}

fun File.print(destination: PrintStream) {
    if(this.exists() && this.canRead()){
        destination.write(Files.readAllBytes(this.toPath()))
    }
}

object Execute {

    operator fun invoke(
            out: File = createTempFile("system", "out"),
            err: File = createTempFile("system", "err"),
            operation: suspend PrintStream.(PrintStream) -> Unit
    ): ExecutionResult {
        val dispatcher = Executors.newWorkStealingPool().asCoroutineDispatcher()

        var success = true
        var exception: Throwable? = null

        with(CoroutineScope(dispatcher)) {

            val handler = CoroutineExceptionHandler { _, ex ->
                resetStream()
                success = false
                exception = ex
            }

            val deferred = launch(handler) {
                PrintStream(out).use { o ->
                    PrintStream(err).use { e ->
                        System.setOut(o)
                        System.setErr(e)
                        operation(o, e)
                    }
                }
                resetStream()
            }

            runBlocking(dispatcher) {
                deferred.join()
            }
        }

        return ExecutionResult(success, out, err, exception)
    }

    private fun resetStream() {
        System.setOut(PrintStream(FileOutputStream(FileDescriptor.out)))
        System.setErr(PrintStream(FileOutputStream(FileDescriptor.err)))
    }
}

