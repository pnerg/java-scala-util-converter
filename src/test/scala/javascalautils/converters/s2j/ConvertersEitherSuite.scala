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
import javascalautils.converters.s2j.Converters._
import scala.util.{Try,Success,Failure}

/**
 * Test suite for Converters scala.Try/Success/Failure conversions.
 * @author Peter Nerg
 */
class ConvertersEitherSuite extends FunSuite {
    val expected = "Left is not to the Right"

    test("Test asJavaLeft") {
        val jLeft = asJavaLeft(Left(expected))
        assert(jLeft.isLeft())
        assertResult(expected)(jLeft.left().get)
    }

}