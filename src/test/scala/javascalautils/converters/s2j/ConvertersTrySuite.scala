/**
 *  Copyright 2015 Peter Nerg
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package javascalautils.converters.s2j

import org.scalatest.FunSuite
import javascalautils.converters.s2j.Converters._
import scala.util.{Try,Success,Failure}

/**
 * Test suite for Converters scala.Try/Success/Failure conversions.
 * @author Peter Nerg
 */
class ConvertersTrySuite extends FunSuite {
    val expected = "Success is not Failure"

    test("Test asJavaFailure") {
        val jfailure = asJavaFailure(Failure(new Exception(expected)))
        assert(jfailure.isFailure)
        assertResult(expected)(jfailure.failed().get.getMessage)
    }

    test("Test asJavaSuccess") {
        val jSuccess = asJavaSuccess(Success(expected))
        assert(jSuccess.isSuccess)
        assertResult(expected)(jSuccess.get)
    }

    test("Test asJavaTry with Success") {
        val success:Try[String] = Success(expected)
        val jSuccess = asJavaTry(success)
        assert(jSuccess.isSuccess)
        assertResult(expected)(jSuccess.get)
    }

    test("Test asJavaTry with Failure") {
        val failure:Try[String] = Failure(new Exception(expected))
        val jfailure = asJavaTry(failure)
        assert(jfailure.isFailure)
        assertResult(expected)(jfailure.failed().get.getMessage)
    }
}