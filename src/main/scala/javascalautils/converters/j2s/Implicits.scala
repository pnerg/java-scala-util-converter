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

import javascalautils.{ Option => JOption, Some => JSome, None => JNone }
import javascalautils.{ Try => JTry, Success => JSuccess, Failure => JFailure }
import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
import javascalautils.concurrent.{ Future => JFuture }
import scala.concurrent.Future
import Converters._

/**
 * The object for all implicit Java -> Scala conversions.
 * @author Peter Nerg
 * @since 1.0
 */
object Implicits extends Implicits

/**
 * Aggregate of all traits for converting from javascalautil -> Scala. <br>
 * Example on usage:
 * {{{
 * import javascalautils.converters.j2s.Implicits._
 * import javascalautils.{Some => JSome}
 * val some = new JSome("Some is never None").asScala
 * }}}
 * @since 1.0
 */
trait Implicits extends OptionImplicits with TryImplicits with EitherImplicits with FutureImplicits

/**
 * Trait with all implicit definitions for the javascalautils.Option/Some/None -> scala.Option/Some/None conversions.
 * @since 1.0
 */
trait OptionImplicits {
  /**
   * The implicit definition for decorating the javascalautils.Option class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaOption[T](underlying: JOption[T]) = new OptionDecorator[T](underlying)

  /**
   * The implicit definition for decorating the javascalautils.None class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaNone[T](underlying: JNone[T]) = new NoneDecorator[T](underlying)

  /**
   * The implicit definition for decorating the javascalautils.Some class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaSome[T](underlying: JSome[T]) = new SomeDecorator[T](underlying)
}

/**
 * Trait with all implicit definitions for the javascalautils.Try/Success/Failure -> scala.util.Try/Success/Failure conversions.
 * @since 1.0
 */
trait TryImplicits {
  /**
   * The implicit definition for decorating the javascalautils.Failure class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaFailure[T](underlying: JFailure[T]) = new FailureDecorator[T](underlying)

  /**
   * The implicit definition for decorating the javascalautils.Success class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaSuccess[T](underlying: JSuccess[T]) = new SuccessDecorator[T](underlying)

  /**
   * The implicit definition for decorating the javascalautils.Try class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaTry[T](underlying: JTry[T]) = new TryDecorator[T](underlying)
}

/**
 * Trait with all implicit definitions for the javascalautils.Either/Left/Right -> scala.util.Either/Left/Right conversions.
 * @since 1.0
 */
trait EitherImplicits {
  /**
   * The implicit definition for decorating the javascalautils.Left class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaLeft[L, R](underlying: JLeft[L, R]) = new LeftDecorator[L, R](underlying)

  /**
   * The implicit definition for decorating the javascalautils.Right class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaRight[L, R](underlying: JRight[L, R]) = new RightDecorator[L, R](underlying)

  /**
   * The implicit definition for decorating the javascalautils.Either class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaEither[L, R](underlying: JEither[L, R]) = new EitherDecorator[L, R](underlying)
}

/**
 * Trait with all implicit definitions for the javascalautils.concurrent.Future -> scala.concurrent.Future conversions.
 * @since 1.0
 */
trait FutureImplicits {
  /**
   * The implicit definition for decorating the javascalautils.concurrent.Future class.
   * @param underlying The type to be converted
   * @return The decorator class
   * @since 1.0
   */
  implicit def asScalaFuture[T](underlying: JFuture[T]) = new FutureDecorator[T](underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Option class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class OptionDecorator[T](underlying: JOption[T]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[T]() = asScalaOption(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.None class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class NoneDecorator[T](underlying: JNone[T]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[T]() = asScalaNone(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Some class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class SomeDecorator[T](underlying: JSome[T]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[T]() = asScalaSome(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Failure class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class FailureDecorator[T](underlying: JFailure[T]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[T]() = asScalaFailure(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Success class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class SuccessDecorator[T](underlying: JSuccess[T]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[T]() = asScalaSuccess(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Try class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class TryDecorator[T](underlying: JTry[T]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[T]() = asScalaTry(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Left class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class LeftDecorator[L, R](underlying: JLeft[L, R]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[L, R]() = asScalaLeft(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Right class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class RightDecorator[L, R](underlying: JRight[L, R]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   */
  def asScala[L, R]() = asScalaRight(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Either class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class EitherDecorator[L, R](underlying: JEither[L, R]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @since 1.0
   * @return The converted type
   */
  def asScala[L, R]() = asScalaEither(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.concurrent.Future class.
 * @constructor creates an instance of the decorator for this type
 * @param underlying The type to be converted
 * @since 1.0
 */
class FutureDecorator[T](underlying: JFuture[T]) {
  /**
   * Converts the provided type to its Scala equivalence.
   * @return The converted type
   * @since 1.0
   */
  def asScala[T]() = asScalaFuture(underlying)
}