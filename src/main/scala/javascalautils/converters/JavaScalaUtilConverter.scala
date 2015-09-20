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
 * Allows for implicit conversion of the supported data types. <br>
 * Is an aggregate of the [[j2s.Implicits]] and [[s2j.Implicits]].
 * @author Peter Nerg
 * @since 1.0
 */
object JavaScalaUtilImplicits extends j2s.Implicits with s2j.Implicits

/**
 * Allows for explicit conversion of the supported data types. <br>
 * Is an aggregate of the [[j2s.Converters]] and [[s2j.Converters]]. <br>
 * Refer to the various methods for code examples.
 * @author Peter Nerg
 * @since 1.0
 */
object JavaScalaUtilConverters extends j2s.Converters with s2j.Converters