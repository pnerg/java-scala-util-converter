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

import javascalautils.{Option => JOption, None => JNone, Some => JSome}

/**
 * Test suite for JavaScalaUtilConverter.
 * @author Peter Nerg
 */
class JavaScalaUtilConverterSuite extends FunSuite {
  
   test("Java None as Scala") {
      import javascalautils.converters.JavaScalaUtilConverter._
      val none = new JNone[String]().asScala()
      assert(none.isEmpty)
  }

  test("Java Some as Scala") {
      import javascalautils.converters.JavaScalaUtilConverter._
      val expected = "Peter was here"
      
      val some = new JSome(expected).asScala()
      
      assert(some.isDefined)
      assertResult(expected)(some.get)
  }

}