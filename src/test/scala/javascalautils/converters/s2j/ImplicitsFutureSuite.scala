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

import javascalautils.ThrowableFunction1
import javascalautils.converters.s2j.Implicits._
import org.scalatest.funsuite.AnyFunSuite

import java.util.concurrent.TimeUnit
import scala.concurrent.{ExecutionContext, Future}

/**
 * Test suite for Implicits scala.Try/Success/Failure conversions.
 * @author Peter Nerg
 */
class ImplicitsFutureSuite extends AnyFunSuite {
  
  implicit val ec = ExecutionContext.global
  val expected = "The Future is right here!"
  val errorMessage = "Ooops the Future did not happen"

  test("Scala Failure as Java with completed successful Future") {
    val future = Future.successful(expected)
    val jfuture = future.asJava
    assertResult(expected)(jfuture.result(1, TimeUnit.SECONDS))
  }

  test("Scala Failure as Java with completed failed Future") {
    val future:Future[String] = Future.failed(new Exception(errorMessage))
    val jfuture = future.asJava
    val recoveredFuture = jfuture.recover(new ThrowableFunction1[Throwable, String]() {
      def apply(t: Throwable) = t.getMessage
    })
    assertResult(errorMessage)(recoveredFuture.result(1, TimeUnit.SECONDS))
  }

  test("Scala Failure as Java with Future that will be successful") {
    val future = Future {
      Thread.sleep(50)
      expected
    }

    val jfuture = future.asJava
    assertResult(expected)(jfuture.result(1, TimeUnit.SECONDS))
  }

  test("Scala Failure as Java with Future that will be failure") {
    val future:Future[String] = Future {
      Thread.sleep(50)
      throw new Exception(errorMessage)
    }

    val jfuture = future.asJava
    val recoveredFuture = jfuture.recover(new ThrowableFunction1[Throwable, String]() {
      def apply(t: Throwable) = t.getMessage
    })
    assertResult(errorMessage)(recoveredFuture.result(1, TimeUnit.SECONDS))
  }
}