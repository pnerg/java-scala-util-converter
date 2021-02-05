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
import org.scalatest.concurrent.ScalaFutures
import javascalautils.concurrent.{ Future => JFuture }
import javascalautils.converters.j2s.Converters._
import javascalautils.ThrowableFunction0
import scala.concurrent.duration._
import scala.concurrent.Await

/**
 * Test suite for Converters Future conversions.
 * @author Peter Nerg
 */
class ConvertersFutureSuite extends AnyFunSuite with ScalaFutures {
  val expected = "The Future is here"
  val errorMessage = "Error in the Future"
  
  test("Test asScalaFuture with completed successful Java Future") {
    //create a completed Future
    val jfuture = JFuture.successful(expected)
    val future = asScalaFuture(jfuture)

    //should be completed
    assert(future.isCompleted)
    assertResult(expected)(future.futureValue)
  }

  test("Test asScalaFuture with completed failed Java Future") {
    //create a completed Future
    val jfuture:JFuture[String] = JFuture.failed(new Exception(errorMessage))
    val future = asScalaFuture(jfuture)

    //should be completed
    assert(future.isCompleted)
    assertResult(errorMessage)(future.failed.futureValue.getMessage)
  }

  test("Test asScalaFuture with Java Future what will be successful") {
    val jfuture = JFuture.apply(new ThrowableFunction0[String]() {
      def apply() = {
        //simulates some execution time
        Thread.sleep(50)
        expected
      }
    })
    val future = asScalaFuture(jfuture)

    assertResult(expected)(future.futureValue)
  }
  
    test("Test asScalaFuture with Java Future what will be failure") {
    val jfuture = JFuture.apply(new ThrowableFunction0[String]() {
      def apply() = {
        //simulates some execution time
        Thread.sleep(50)
        throw new Exception(errorMessage)
      }
    })
    
    val future = asScalaFuture(jfuture)
    assertResult(errorMessage)(future.failed.futureValue.getMessage)
  }
}