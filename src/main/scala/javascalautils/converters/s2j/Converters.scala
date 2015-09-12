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

import javascalautils.{ Option => JOption, Some => JSome, None => JNone }
import javascalautils.{ Try => JTry, Success => JSuccess, Failure => JFailure }
import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
import scala.util.{ Try, Failure, Success }
import scala.util.{ Either, Left, Right }

/**
 * Object implementing its trait
 * @author Peter Nerg
 */
object Converters extends Converters

/**
 * Provides the code for converting a class from Scala -> javascalautils
 * @author Peter Nerg
 */
trait Converters extends OptionConverters with TryConverters with EitherConverters

/**
 * Provides the code for converting scala.Option/Some/None -> javascalautils.Option/Some/None
 * @author Peter Nerg
 */
trait OptionConverters {
  /**
   * Converts a scala.None to a javascalautils.None.
   * @since 1.0
   */
  def asJavaNone[T](underlying: None.type) = new JNone

  /**
   * Converts a scala.Some to a javascalautils.Some.
   * @since 1.0
   */
  def asJavaSome[T](underlying: Some[T]) = new JSome(underlying.get)

  /**
   * Converts a scala.Option to a javascalautils.Option.
   * @since 1.0
   */
  def asJavaOption[T](underlying: Option[T]) = if (underlying.isDefined) new JSome(underlying.get) else new JNone
}

/**
 * Provides the code for converting scala.util.Try/Success/Failure -> javascalautils.Try/Success/Failure
 * @author Peter Nerg
 */
trait TryConverters {

  /**
   * Converts a scala.util.Failure to a javascalautils.Failure.
   * @since 1.0
   */
  def asJavaFailure[T](underlying: Failure[T]) = new JFailure(underlying.failed.get)

  /**
   * Converts a scala.util.Success to a javascalautils.Success.
   * @since 1.0
   */
  def asJavaSuccess[T](underlying: Success[T]) = new JSuccess(underlying.get)

  /**
   * Converts a scala.util.Try to a javascalautils.Try.
   * @since 1.0
   */
  def asJavaTry[T](underlying: Try[T]) = if (underlying.isSuccess) new JSuccess(underlying.get) else new JFailure(underlying.failed.get)
}

/**
 * Provides the code for converting scala.util.Either/Left/Right -> javascalautils.Either/Left/Right
 * @author Peter Nerg
 */
trait EitherConverters {
  /**
   * Converts a scala.util.Left to a javascalautils.Left.
   * @since 1.0
   */
  def asJavaLeft[L, R](underlying: Left[L, R]) = asLeft(underlying)

  /**
   * Converts a scala.util.Left to a javascalautils.Right.
   * @since 1.0
   */
  def asJavaRight[L, R](underlying: Right[L, R]) = asRight(underlying)

  /** Creates a javascalautils.Left out of the provided scala.util.Either. */
  private def asLeft[L, R](either: Either[L, R]) = new JLeft(either.left.get)

  /** Creates a javascalautils.Right out of the provided scala.util.Either. */
  private def asRight[L, R](either: Either[L, R]) = new JRight(either.right.get)
} 