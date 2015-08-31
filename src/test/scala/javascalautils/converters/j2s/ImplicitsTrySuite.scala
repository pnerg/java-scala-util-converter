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

import javascalautils.{ Try => JTry, Success => JSuccess, Failure => JFailure }
import javascalautils.converters.j2s.Implicits._

/**
 * Test suite for Implicits Try/Success/Failure conversions.
 * @author Peter Nerg
 */
class ImplicitsTrySuite extends FunSuite {
  val expected = "Success is never a Failure"

  test("Java Failure as Scala") {
    val failure = new JFailure(new Exception("Error, terror")).asScala
    assert(failure.isFailure)
    assertResult("Error, terror")(failure.failed.get.getMessage)
  }

  test("Java Success as Scala") {
    val success = new JSuccess(expected).asScala
    assert(success.isSuccess)
    assertResult(expected)(success.get)
  }


  test("Java Try-Success as Scala") {
    val jsuccess:JTry[String] = new JSuccess(expected)
    val success = jsuccess.asScala
    assert(success.isSuccess)
    assertResult(expected)(success.get)
  }
}