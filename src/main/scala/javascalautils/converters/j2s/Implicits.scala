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

import javascalautils.{Option => JOption, Some => JSome, None => JNone}
import javascalautils.{Try => JTry, Success => JSuccess, Failure => JFailure}
import scala.util.Failure
import Converters._

/**
 * The object for all implicit Java -> Scala conversions.
 * @author Peter Nerg
 */
object Implicits extends Implicits {
}

/**
 * Trait with all definitions for the Java -> Scala conversions.
 */
trait Implicits extends OptionImplicits with TryImplicits

trait OptionImplicits {
  
  /** 
   * The implicit definition for decorating the javascalautils.None class.
   * @since 1.0
   */
  implicit def asScalaOption[T](underlying: JOption[T]) = new OptionDecorator[T](underlying)
  
  /** 
   * The implicit definition for decorating the javascalautils.None class.
   * @since 1.0
   */
  implicit def asScalaNone[T](underlying: JNone[T]) = new NoneDecorator[T](underlying)

  /** 
   * The implicit definition for decorating the javascalautils.None class.
   * @since 1.0
   */
  implicit def asScalaSome[T](underlying: JSome[T]) = new SomeDecorator[T](underlying)
}

/**
 * Trait with all definitions for the Java -> Scala conversions.
 */
trait TryImplicits {
  /** 
   * The implicit definition for decorating the javascalautils.Failure class.
   * @since 1.0
   */
  implicit def asScalaFailure[T](underlying: JFailure[T]) = new FailureDecorator[T](underlying)

  /** 
   * The implicit definition for decorating the javascalautils.Success class.
   * @since 1.0
   */
  implicit def asScalaSuccess[T](underlying: JSuccess[T]) = new SuccessDecorator[T](underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Option class.
 * @since 1.0
 */
class OptionDecorator[T](underlying: JOption[T]) {
  def asScala[T]() = asScalaOption(underlying) 
}

/**
 * Class containing the asScala method that will decorate the javascalautils.None class.
 * @since 1.0
 */
class NoneDecorator[T](underlying: JNone[T]) {
  def asScala[T]() = asScalaNone(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Some class.
 * @since 1.0
 */
class SomeDecorator[T](underlying: JSome[T]) {
  def asScala[T]() = asScalaSome(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Failure class.
 * @since 1.0
 */
class FailureDecorator[T](underlying: JFailure[T]) {
  def asScala[T]() = asScalaFailure(underlying)
}

/**
 * Class containing the asScala method that will decorate the javascalautils.Success class.
 * @since 1.0
 */
class SuccessDecorator[T](underlying: JSuccess[T]) {
  def asScala[T]() = asScalaSuccess(underlying)
}