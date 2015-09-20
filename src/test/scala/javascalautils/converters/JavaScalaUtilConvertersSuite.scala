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
package javascalautils.converters

import org.scalatest.FunSuite

import javascalautils.{ None => JNone, Some => JSome, Option => JOption }
import javascalautils.converters.JavaScalaUtilConverters._

/**
 * Test suite for JavaScalaUtilConverters. <br>
 * This will only test a few use cases to verify the import.
 * @author Peter Nerg
 */
class JavaScalaUtilConvertersSuite extends FunSuite {
  val expected = "Some is never None"

  test("Test asScalaOption with Some") {
    val option = asScalaOption(new JSome(expected))
    assert(option.isDefined)
    assertResult(expected)(option.get)
  }

  test("Test asJavaOption with Some") {
    val joption = asJavaOption(Some(expected))
    assert(joption.isDefined)
    assertResult(expected)(joption.get)
  }
}