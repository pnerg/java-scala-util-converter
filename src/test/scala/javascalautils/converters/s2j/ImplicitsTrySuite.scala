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

import javascalautils.{ None => JNone, Option => JOption, Some => JSome }
import javascalautils.converters.s2j.Implicits._
import scala.util.{ Try, Success, Failure }

/**
 * Test suite for Implicits scala.Try/Success/Failure conversions.
 * @author Peter Nerg
 */
class ImplicitsTrySuite extends FunSuite {
  val expected = "Failure is not an Option"

  test("Scala Failure as Java") {
    val jfailure = Failure(new Exception(expected)).asJava
    assert(jfailure.isFailure)
    assertResult(expected)(jfailure.failed().get.getMessage)
  }

  test("Scala Success as Java") {
    val jSuccess = Success(expected).asJava
    assert(jSuccess.isSuccess)
    assertResult(expected)(jSuccess.get)
  }

  test("Scala Try-Success as Java") {
    val success: Try[String] = Success(expected)
    val jSuccess = success.asJava
    assert(jSuccess.isSuccess)
    assertResult(expected)(jSuccess.get)
  }

  test("Test Try-Failure with Failure") {
    val failure: Try[String] = Failure(new Exception(expected))
    val jfailure = failure.asJava
    assert(jfailure.isFailure)
    assertResult(expected)(jfailure.failed().get.getMessage)
  }

}