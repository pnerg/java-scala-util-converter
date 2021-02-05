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

import javascalautils.{ None => JNone, Option => JOption, Some => JSome }
import javascalautils.converters.s2j.Implicits._

/**
 * Test suite for Implicits scala.Option/Some/None conversions.
 * @author Peter Nerg
 */
class ImplicitsOptionSuite extends AnyFunSuite {
  val expected = "Some is never None"

  test("Scala None as Java") {
    val jnone = None.asJava
    assert(jnone.isEmpty)
  }

  test("Scala Some as Java") {
    val jsome = Some(expected).asJava
    assert(jsome.isDefined)
    assertResult(expected)(jsome.get)
  }

  test("Scala Option-Some as Java") {
    val option:Option[String] = Some(expected)
    val joption = option.asJava
    assert(joption.isDefined)
    assertResult(expected)(joption.get)
  }

  test("Scala Option-None as Java") {
    val option = None
    val joption = option.asJava
    assert(joption.isEmpty())
  }

}