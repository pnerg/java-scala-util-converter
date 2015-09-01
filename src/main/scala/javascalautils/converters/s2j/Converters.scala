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

/**
 * Object implementing its trait
 * @author Peter Nerg
 */
object Converters extends Converters

/**
 * Provides the code for converting a class from Scala -> javascalautils
 * @author Peter Nerg
 */
trait Converters extends OptionConverters

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

}