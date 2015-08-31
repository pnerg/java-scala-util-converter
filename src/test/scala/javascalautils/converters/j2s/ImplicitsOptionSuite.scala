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

import javascalautils.{ None => JNone, Option => JOption, Some => JSome }
import javascalautils.converters.j2s.Implicits.{ asScalaNone, asScalaOption, asScalaSome }

/**
 * Test suite for Implicits Option/Some/None conversions.
 * @author Peter Nerg
 */
class ImplicitsOptionSuite extends FunSuite {
  val expected = "Peter was here"

  test("Java Option-None as Scala") {
    val jnone: JOption[String] = new JNone()
    val option = jnone.asScala()
    assert(option.isEmpty)
  }

  test("Java Option-Some as Scala") {
    val jsome: JOption[String] = new JSome(expected)
    val option = jsome.asScala()
    assert(option.isDefined)
    assertResult(expected)(option.get)
  }

  test("Java None as Scala") {
    val none = new JNone[String]().asScala()
    assert(none.isEmpty)
  }

  test("Java Some as Scala") {
    val some = new JSome(expected).asScala()

    assert(some.isDefined)
    assertResult(expected)(some.get)
  }
}