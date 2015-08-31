package javascalautils.converters.j2s

import javascalautils.{ Option => JOption, Some => JSome, None => JNone }
import javascalautils.{ Try => JTry, Success => JSuccess, Failure => JFailure }
import scala.util.{ Try, Failure, Success }

/**
 * Object implementing its trait
 * @author Peter Nerg
 */
object Converters extends Converters

/**
 * Provides the code for converting a class from javascalautils -> Scala
 * @author Peter Nerg
 */
trait Converters extends OptionConverters with TryConverters

/**
 * Provides the code for converting a javascalautils.Option/Some/None -> scala.Option/Some/None
 * @author Peter Nerg
 */
trait OptionConverters {
  /**
   * Converts a javascalautils.Option to a scala.Option.
   * @since 1.0
   */
  def asScalaOption[T](underlying: JOption[T]) = if (underlying.isDefined()) Some(underlying.get) else None

  /**
   * Converts a javascalautils.None to a scala.None.
   * @since 1.0
   */
  def asScalaNone[T](underlying: JNone[T]) = None

  /**
   * Converts a javascalautils.Some to a scala.Some.
   * @since 1.0
   */
  def asScalaSome[T](underlying: JSome[T]) = Some(underlying.get)
}

/**
 * Provides the code for converting a javascalautils.Try/Success/Failure -> scala.util.Try/Success/Failure
 * @author Peter Nerg
 */
trait TryConverters {
  /**
   * Converts a javascalautils.Failure to a scala.util.Failure.
   * @since 1.0
   */
  def asScalaFailure[T](underlying: JFailure[T]) = Failure(underlying.failed().get)

  /**
   * Converts a javascalautils.Success to a scala.util.Success.
   * @since 1.0
   */
  def asScalaSuccess[T](underlying: JSuccess[T]) = Success(underlying.get)

    /**
   * Converts a javascalautils.Try to a scala.util.Try.
   * @since 1.0
   */
  def asScalaTry[T](underlying: JTry[T]) = if(underlying.isSuccess()) Success(underlying.get) else Failure(underlying.failed().get)
}