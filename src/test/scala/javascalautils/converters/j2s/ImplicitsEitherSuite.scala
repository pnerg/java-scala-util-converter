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

import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
import javascalautils.converters.j2s.Implicits._

/**
 * Test suite for Implicits Either/Left/Right conversions.
 * @author Peter Nerg
 */
class ImplicitsEitherSuite extends FunSuite {
  val expected = "Left is not Right"

  test("Java Left as Scala") {
    val left = new JLeft[String, String](expected).asScala
    assert(left.isLeft)
    assertResult(expected)(left.left.get)
  }

  test("Java Right as Scala") {
    val right = new JRight[String, String](expected).asScala
    assert(right.isRight)
    assertResult(expected)(right.right.get)
  }

  test("Java Either-Left as Scala") {
    val jleft:JEither[String, String] = new JLeft[String, String](expected) 
    val left = jleft.asScala
    assert(left.isLeft)
    assertResult(expected)(left.left.get)
  }

  test("Java Either-Right as Scala") {
    val jright:JRight[String,String] = new JRight[String, String](expected)
    val right = jright.asScala
    assert(right.isRight)
    assertResult(expected)(right.right.get)
  }
}