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

import org.scalatest.funsuite.AnyFunSuite

import javascalautils.converters.s2j.Implicits._

/**
 * Test suite for Implicits scala.Either/Left/Right conversions.
 * @author Peter Nerg
 */
class ImplicitsEitherSuite extends AnyFunSuite {
  val expected = "Right is rarely Left"

  test("Scala Left as Java") {
    val jLeft = Left(expected).asJava
    assert(jLeft.isLeft())
    assertResult(expected)(jLeft.left().get)
  }

  test("Scala Right as Java") {
    val jright = Right(expected).asJava
    assert(jright.isRight)
    assertResult(expected)(jright.right.get)
  }

  test("Test Either-Left as Java") {
    val either: Either[String, Nothing] = Left(expected)
    val jLeft = either.asJava
    assert(jLeft.isLeft())
    assertResult(expected)(jLeft.left.get)
  }

  test("Test Either-Right as Java") {
    val either: Either[Nothing, String] = Right(expected)
    val jright = either.asJava
    assert(jright.isRight)
    assertResult(expected)(jright.right.get)
  }

}