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
 package javascalautils
 
 /**
 * Contains the main objects for the converters.
 * ==Overview==
 * This library provides the mechanisms to convert between type provided by javascalautils to their Scala equivalence and vice-versa. <br>
 * One can either perform explicit conversions by using a specific converter method or do implicit conversions using the decorator pattern provided by Scalas implicit method declaration.
 * ===Explicit Conversion===
 * This is the mechanism for performing the conversion by invoking a specific method taking the type you want to and converting it to its Scala or Java equivalence. <br>
 * There's converters for Java -> Scala, [[j2s.Converters]] as well as from Scala -> Java, [[s2j.Converters]]. There's also an aggregate converter containing both of these [[JavaScalaUtilConverters]].
 * Either import the converters you need:
 * {{{
 * import javascalautils.converters.j2s.Converters._
 * import javascalautils.converters.s2j.Converters._
 * }}}
 * or use the aggregate object composed of both above converters
 * {{{
 * import javascalautils.converters.JavaScalaUtilConverters._
 * }}}
 * After that it's a matter of using the right converter method to do the job. <br>
 * E.g.
 * {{{
 * import javascalautils.{Some => JSome}
 * 
 * val optionSome = asScalaOption(new JSome("Some is never None"))
 * }}}
 *  
 * Refer to the aggregate object [[JavaScalaUtilConverters]] for a list of all converter methods and examples on usage.
 * ===Implicit Conversion===
 * This utilizes the implicit mechanism in Scala to decorate any given class with new methods. <br>
 * More precisely this library provides ''asScala'' methods on all supported Java types and ''asJava'' methods on all supported Scala types. <br>
 * This ''magic'' is enabled by having right imports in scope. <br>
 * Just as with the explicit converters the implicit ones are divided into Java -> Scala, [[j2s.Implicits]] as well as from Scala -> Java, [[s2j.Implicits]]. <br>
 * Or one can choose to use the aggregate implicit [[JavaScalaUtilImplicits]]. <br>
 * It's again a matter of having the right imports in scope.
 * {{{
 * import javascalautils.converters.j2s.Implicits._
 * import javascalautils.converters.s2j.Implicits._
 * }}}
 * or use the aggregate object composed of both above implicits.
 * {{{
 * import javascalautils.converters.JavaScalaUtilImplicits._
 * }}}
 * Now your instances should be decorated with either a ''asScala'' or ''asJava'' method.
 * {{{
 * import javascalautils.{Some => JSome}
 * 
 * val some = new JSome("Some is never None").asScala
 * }}}
 * @author Peter Nerg
 */
package object converters {}