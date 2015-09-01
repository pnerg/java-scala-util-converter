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
import Converters._

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

}

/**
 * Class containing the asScala method that will decorate the javascalautils.Option class.
 * @since 1.0
 */
class NoneDecorator[T](underlying: None.type) {
  def asJava[T]() = asJavaNone(underlying)
}
