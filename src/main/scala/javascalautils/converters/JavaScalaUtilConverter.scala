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

package javascalautils.converters

/**
 * Allows for implicit conversion of the supported data types.
 * @author Peter Nerg
 * @since 1.0
 */
object JavaScalaUtilImplicits extends Implicits

/**
 * Is an aggregate of the j2s.Implicits and s2j.Implicits.
 * @author Peter Nerg
 * @since 1.0
 */
trait Implicits extends j2s.Implicits with s2j.Implicits

/**
 * Allows for explicit conversion of the supported data types. <br>
 * ==Code Examples==
 * ===Java -> Scala===
 * Using the javascalutils.Option/Some/None classes as example
 * {{{
 * import javascalautils.{ None => JNone, Option => JOption, Some => JSome }
 * import javascalautils.converters.JavaScalaUtilConverters._
 * 
 * val jnone: JOption[String] = new JNone()
 * val option = asScalaOption(jnone)
 * 
 * val none = asScalaNone(new JNone[String]())
 * 
 * val some = asScalaSome(new JSome("Some is never None"))
 * }}}
 * ===Scala -> Java===
 * Using the scala.Option/Some/None classes as example
 * {{{
 * import javascalautils.converters.JavaScalaUtilConverters._
 * 
 * val jnone = asJavaNone(None)
 * 
 * val jsome = asJavaSome(Some("Some is never None"))
 * 
 * val joption = asJavaOption(Some("Some is never None"))
 * }}}
 * <br>
 * For further examples refer to the various Java -> Scala converters: <br>
 * [[javascalautils.converters.j2s.OptionConverters]] <br>
 * [[javascalautils.converters.j2s.TryConverters]] <br>
 * [[javascalautils.converters.j2s.EitherConverters]] <br>
 * [[javascalautils.converters.j2s.FutureConverters]] <br>
 * @author Peter Nerg
 * @since 1.0
 */
object JavaScalaUtilConverters extends Converters

/**
 * Is an aggregate of the j2s.Converters and s2j.Converters.
 * @author Peter Nerg
 * @since 1.0
 */
trait Converters extends j2s.Converters with s2j.Converters