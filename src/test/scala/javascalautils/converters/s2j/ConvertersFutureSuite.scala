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
import scala.concurrent.{ Future, ExecutionContext }
import javascalautils.concurrent.{ Future => JFuture }
import java.util.concurrent.TimeUnit

/**
 * Test suite for Converters scala.concurrent.Future conversions.
 * @author Peter Nerg
 */
class ConvertersFutureSuite extends FunSuite {
  implicit val ec = ExecutionContext.global
  val expected = "The Future is right here!"
  val errorMessage = "Ooops the Future did not happen"

  test("Test asJavaFuture with completed successful Scala Future") {
    val future = Future.successful(expected)
    val jfuture = asJavaFuture(future)
    assertResult(expected)(jfuture.result(1, TimeUnit.SECONDS))
  }

  test("Test asJavaFuture with completed failed Scala Future") {
    val future = Future.failed(new Exception(errorMessage))
    val jfuture: JFuture[String] = asJavaFuture(future)
    val recoveredFuture = jfuture.recover(new java.util.function.Function[Throwable, String]() {
      def apply(t: Throwable) = t.getMessage
    })
    assertResult(errorMessage)(recoveredFuture.result(1, TimeUnit.SECONDS))
  }

  test("Test asJavaFuture with Scala Future that will be successful") {
    val future = Future {
      Thread.sleep(50)
      expected
    }

    val jfuture = asJavaFuture(future)
    assertResult(expected)(jfuture.result(1, TimeUnit.SECONDS))
  }

  test("Test asJavaFuture with Scala Future that will be failure") {
    val future = Future {
      Thread.sleep(50)
      throw new Exception(errorMessage)
    }

    val jfuture: JFuture[String] = asJavaFuture(future)
    val recoveredFuture = jfuture.recover(new java.util.function.Function[Throwable, String]() {
      def apply(t: Throwable) = t.getMessage
    })
    assertResult(errorMessage)(recoveredFuture.result(1, TimeUnit.SECONDS))
  }

}