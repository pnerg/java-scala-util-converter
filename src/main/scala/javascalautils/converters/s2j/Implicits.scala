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

import javascalautils.{Option => JOption, Some => JSome, None => JNone}

/**
 * The object for all implicit Scala -> Java conversions.
 * @author Peter Nerg
 * @since 1.0
 */
object Implicits extends Implicits {
}

/**
 * Trait with all implicit definitions for Scala -> Java conversions
 */
trait Implicits extends OptionImplicits

/**
 * Trait with all implicit definitions for scala.Option/Some/None conversions -> javascalautils.Option/Some/None .
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
