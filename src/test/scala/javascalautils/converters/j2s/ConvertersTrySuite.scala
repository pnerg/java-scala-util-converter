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
package javascalautils.converters.j2s

import org.scalatest.FunSuite

import javascalautils.converters.j2s.Converters._
import javascalautils.{Try => JTry, Success => JSuccess, Failure => JFailure}

/**
 * Test suite for Converters Try/Success/Failure conversions.
 * @author Peter Nerg
 */
class ConvertersTrySuite extends FunSuite {
  val expected = "Success is never a Failure"
  
  test("Test asScalaFailure") {
    val failure = asScalaFailure(new JFailure(new Exception("Error, terror")))
    assert(failure.isFailure)
    assertResult("Error, terror")(failure.failed.get.getMessage)
  }

  test("Test asScalaSuccess") {
    val success = asScalaSuccess(new JSuccess(expected))
    assert(success.isSuccess)
    assertResult(expected)(success.get)
  }

  test("Test asScalaTry with Success") {
    val success = asScalaTry(new JSuccess(expected))
    assert(success.isSuccess)
    assertResult(expected)(success.get)
  }

  test("Test asScalaTry with Failure") {
    val failure = asScalaTry(new JFailure(new Exception("Error, terror")))
    assert(failure.isFailure)
    assertResult("Error, terror")(failure.failed.get.getMessage)
  }
}