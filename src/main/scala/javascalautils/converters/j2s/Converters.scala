package javascalautils.converters.j2s

import javascalautils.{ Option => JOption, Some => JSome, None => JNone }
import javascalautils.{ Try => JTry, Success => JSuccess, Failure => JFailure }
import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
import scala.util.{ Try, Failure, Success }
import scala.util.{ Either, Left, Right }

/**
 * Object implementing its trait
 * @author Peter Nerg
 */
object Converters extends Converters

/**
 * Provides the code for converting a class from javascalautils -> Scala
 * @author Peter Nerg
 */
trait Converters extends OptionConverters  with TryConverters with EitherConverters

/**
 * Provides the code for converting javascalautils.Option/Some/None -> scala.Option/Some/None
 * @author Peter Nerg
 */
trait OptionConverters {
  /**
   * Converts a javascalautils.Option to a scala.Option.
   * @since 1.0
   */
  def asScalaOption[T](underlying: JOption[T]) = if (underlying.isDefined) Some(underlying.get) else None

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
 * Provides the code for converting javascalautils.Try/Success/Failure -> scala.util.Try/Success/Failure
 * @author Peter Nerg
 */
trait TryConverters {
  /**
   * Converts a javascalautils.Failure to a scala.util.Failure.
   * @since 1.0
   */
  def asScalaFailure[T](underlying: JFailure[T]) = Failure(underlying.failed.get)

  /**
   * Converts a javascalautils.Success to a scala.util.Success.
   * @since 1.0
   */
  def asScalaSuccess[T](underlying: JSuccess[T]) = Success(underlying.get)

    /**
   * Converts a javascalautils.Try to a scala.util.Try.
   * @since 1.0
   */
  def asScalaTry[T](underlying: JTry[T]) = if(underlying.isSuccess()) Success(underlying.get) else Failure(underlying.failed.get)
}

/**
 * Provides the code for converting a javascalautils.Either/Left/Right -> scala.util.Either/Left/Right
 * @author Peter Nerg
 */
trait EitherConverters {
  /**
   * Converts a javascalautils.Left to a scala.util.Left.
   * @since 1.0
   */
  def asScalaLeft[L, R](underlying: JLeft[L, R]) = asLeft(underlying)

  /**
   * Converts a javascalautils.Right to a scala.util.Right.
   * @since 1.0
   */
  def asScalaRight[L, R](underlying: JRight[L, R]) = asRight(underlying)

  /**
   * Converts a javascalautils.Either to a scala.util.Either.
   * @since 1.0
   */
  def asScalaEither[L, R](underlying: JEither[L, R]) = if(underlying.isRight()) asRight(underlying) else asLeft(underlying)
  
  /** Creates a scala.util.Right out of the provided javascalautils.Either. */
  private def asRight[L,R](either:JEither[L,R]) = Right(either.right.get)

  /** Creates a scala.util.Left out of the provided javascalautils.Either. */
  private def asLeft[L,R](either:JEither[L,R]) = Left(either.left.get)
}