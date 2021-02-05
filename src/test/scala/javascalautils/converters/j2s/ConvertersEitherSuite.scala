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

import org.scalatest.funsuite.AnyFunSuite

import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
import javascalautils.converters.j2s.Converters._

/**
 * Test suite for Converters Either/Left/Right conversions.
 * @author Peter Nerg
 */
class ConvertersEitherSuite extends AnyFunSuite {
  val expected = "Left is not Right"

  test("Test asScalaLeft") {
    val left = asScalaLeft(new JLeft[String,String](expected))
    assert(left.isLeft)
    assertResult(expected)(left.left.get)
  }

  test("Test asScalaRight") {
    val right = asScalaRight(new JRight[String,String](expected))
    assert(right.isRight)
    assertResult(expected)(right.right.get)
  }

  test("Test asScalaEither with Right") {
    val right:Either[String, String] = asScalaEither(new JRight[String,String](expected))
    assert(right.isRight)
    assertResult(expected)(right.right.get)
  }

  test("Test asScalaEither with Left") {
    val left:Either[String,String] = asScalaEither(new JLeft[String,String](expected))
    assert(left.isLeft)
    assertResult(expected)(left.left.get)
  }
}