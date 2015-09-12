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
 * The object for all implicit Scala -> Java conversions.
 * @author Peter Nerg
 * @since 1.0
 */
object Implicits extends Implicits

/**
 * Trait with all implicit definitions for Scala -> Java conversions
 */
trait Implicits extends OptionImplicits with TryImplicits with EitherImplicits

/**
 * Trait with all implicit definitions for scala.Option/Some/None conversions -> javascalautils.Option/Some/None.
 */
trait OptionImplicits {
  /**
   * The implicit definition for decorating the scala.None class.
   * @since 1.0
   */
  implicit def asJavaNone[T](underlying: None.type) = new NoneDecorator[T](underlying)

  /**
   * The implicit definition for decorating the scala.Some class.
   * @since 1.0
   */
  implicit def asJavaSome[T](underlying: Some[T]) = new SomeDecorator[T](underlying)

  /**
   * The implicit definition for decorating the scala.Option class.
   * @since 1.0
   */
  implicit def asJavaOption[T](underlying: Option[T]) = new OptionDecorator[T](underlying)
}

/**
 * Trait with all implicit definitions for scala.util.Try/Success/Failure conversions -> javascalautils.Try/Success/Failure.
 */
trait TryImplicits {

  /**
   * The implicit definition for decorating the scala.util.Failure class.
   * @since 1.0
   */
  implicit def asJavaFailure[T](underlying: Failure[T]) = new FailureDecorator(underlying)

  /**
   * The implicit definition for decorating the scala.util.Success class.
   * @since 1.0
   */
  implicit def asJavaSuccess[T](underlying: Success[T]) = new SuccessDecorator(underlying)

  /**
   * The implicit definition for decorating the scala.util.Try class.
   * @since 1.0
   */
  implicit def asJavaTry[T](underlying: Try[T]) = new TryDecorator(underlying)
}

/**
 * Trait with all implicit definitions for scala.Either/Left/Right conversions -> javascalautils.Either/Left/Right.
 * @since 1.0
 */
trait EitherImplicits {

  /**
   * The implicit definition for decorating the scala.util.Left class.
   * @since 1.0
   */
  implicit def asJavaLeft[L, R](underlying: Left[L, R]) = new LeftDecorator(underlying)
}

/**
 * Class containing the asScala method that will decorate the scala.None class.
 * @since 1.0
 */
class NoneDecorator[T](underlying: None.type) {
  def asJava[T]() = Converters.asJavaNone(underlying)
}

/**
 * Class containing the asScala method that will decorate the scala.Some class.
 * @since 1.0
 */
class SomeDecorator[T](underlying: Some[T]) {
  def asJava[T]() = Converters.asJavaSome(underlying)
}

/**
 * Class containing the asScala method that will decorate the scala.Option class.
 * @since 1.0
 */
class OptionDecorator[T](underlying: Option[T]) {
  def asJava[T]() = Converters.asJavaOption(underlying)
}

/**
 * Class containing the asScala method that will decorate the scala.util.Failure class.
 * @since 1.0
 */
class FailureDecorator[T](underlying: Failure[T]) {
  def asJava[T]() = Converters.asJavaFailure(underlying)
}

/**
 * Class containing the asScala method that will decorate the scala.util.Success class.
 * @since 1.0
 */
class SuccessDecorator[T](underlying: Success[T]) {
  def asJava[T]() = Converters.asJavaSuccess(underlying)
}

/**
 * Class containing the asScala method that will decorate the scala.util.Try class.
 * @since 1.0
 */
class TryDecorator[T](underlying: Try[T]) {
  def asJava[T]() = Converters.asJavaTry(underlying)
}

/**
 * Class containing the asScala method that will decorate the scala.util.Left class.
 * @since 1.0
 */
class LeftDecorator[L, R](underlying: Left[L, R]) {
  def asJava[L, R]() = Converters.asJavaLeft(underlying)
}
