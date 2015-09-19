package javascalautils.converters.j2s

import javascalautils.{ Option => JOption, Some => JSome, None => JNone }
import javascalautils.{ Try => JTry, Success => JSuccess, Failure => JFailure }
import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
import javascalautils.concurrent.{ Future => JFuture }
import scala.util.{ Try, Failure, Success }
import scala.util.{ Either, Left, Right }
import scala.concurrent.{ Future, Promise }
import java.util.function.Consumer

/**
 * Object implementing all converter traits
 * @author Peter Nerg
 */
object Converters extends Converters

/**
 * Aggregates the various traits for converting from javascalautils -> Scala. <br>
 * Refer to the various methods for code examples.
 * @author Peter Nerg
 */
trait Converters extends OptionConverters with TryConverters with EitherConverters with FutureConverters

/**
 * Provides the code for converting javascalautils.Option/Some/None -> scala.Option/Some/None. <br>
 * Refer to the various methods for code examples.
 * @author Peter Nerg
 */
trait OptionConverters {
  /**
   * Converts a javascalautils.Option to a scala.Option.
   * {{{
   * import javascalautils.{ None => JNone, Option => JOption, Some => JSome }
   *
   * val optionNone = asScalaOption(new JNone())
   * val optionSome = asScalaOption(new JSome("Some is never None"))
   *
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaOption[T](underlying: JOption[T]) = if (underlying.isDefined) Some(underlying.get) else None

  /**
   * Converts a javascalautils.None to a scala.None.
   * {{{
   * import javascalautils.{ None => JNone, Option => JOption, Some => JSome }
   *
   * val optionNone = asScalaOption(new JNone())
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaNone[T](underlying: JNone[T]) = None

  /**
   * Converts a javascalautils.Some to a scala.Some.
   * {{{
   * import javascalautils.{ None => JNone, Option => JOption, Some => JSome }
   *
   * val optionSome = asScalaOption(new JSome("Some is never None"))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaSome[T](underlying: JSome[T]) = Some(underlying.get)
}

/**
 * Provides the code for converting javascalautils.Try/Success/Failure -> scala.util.Try/Success/Failure.
 * Refer to the various methods for code examples.
 * @author Peter Nerg
 */
trait TryConverters {
  /**
   * Converts a javascalautils.Failure to a scala.util.Failure.
   * {{{
   * import javascalautils.{Try => JTry, Success => JSuccess, Failure => JFailure}
   *
   * val failure = asScalaFailure(new JFailure(new Exception("Error, terror")))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaFailure[T](underlying: JFailure[T]) = Failure(underlying.failed.get)

  /**
   * Converts a javascalautils.Success to a scala.util.Success.
   * {{{
   * import javascalautils.{Try => JTry, Success => JSuccess, Failure => JFailure}
   *
   * val success = asScalaSuccess(new JSuccess("Success is never a Failure"))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaSuccess[T](underlying: JSuccess[T]) = Success(underlying.get)

  /**
   * Converts a javascalautils.Try to a scala.util.Try.
   * {{{
   * import javascalautils.{Try => JTry, Success => JSuccess, Failure => JFailure}
   *
   * val trySuccess = asScalaTry(new JSuccess("Success is never a Failure"))
   * val tryFailure = asScalaTry(new JFailure(new Exception("Error, terror")))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaTry[T](underlying: JTry[T]) = if (underlying.isSuccess()) Success(underlying.get) else Failure(underlying.failed.get)
}

/**
 * Provides the code for converting a javascalautils.Either/Left/Right -> scala.util.Either/Left/Right. <br>
 * Refer to the various methods for code examples.
 * @author Peter Nerg
 */
trait EitherConverters {
  /**
   * Converts a javascalautils.Left to a scala.util.Left.
   * {{{
   * import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
   *
   * val left = asScalaLeft(new JLeft[String,String]("Left is not Right"))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaLeft[L, R](underlying: JLeft[L, R]) = asLeft(underlying)

  /**
   * Converts a javascalautils.Right to a scala.util.Right.
   * {{{
   * import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
   *
   * val right = asScalaRight(new JRight[String,String]("Left is not Right"))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaRight[L, R](underlying: JRight[L, R]) = asRight(underlying)

  /**
   * Converts a javascalautils.Either to a scala.util.Either.
   * {{{
   * import javascalautils.{ Either => JEither, Left => JLeft, Right => JRight }
   *
   * val eitherRight = asScalaEither(new JRight[String,String]("Left is not Right"))
   * val eitherLeft = asScalaEither(new JLeft[String,String]("Left is not Right"))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaEither[L, R](underlying: JEither[L, R]) = if (underlying.isRight()) asRight(underlying) else asLeft(underlying)

  /** Creates a scala.util.Right out of the provided javascalautils.Either. */
  private def asRight[L, R](either: JEither[L, R]) = Right(either.right.get)

  /** Creates a scala.util.Left out of the provided javascalautils.Either. */
  private def asLeft[L, R](either: JEither[L, R]) = Left(either.left.get)
}

/**
 * Provides the code for converting a javascalautils.concurrent.Future -> scala.concurrent.Future. <br>
 * Refer to the various methods for code examples.
 * @author Peter Nerg
 * @since 1.0
 */
trait FutureConverters {

  /**
   * Converts a javascalautils.concurrent.Future to a scala.concurrent.Future.
   * {{{
   * import javascalautils.concurrent.{ Future => JFuture }
   * //just illustrating a Java Future by creating an already fulfilled one
   * val future = asScalaFuture(JFuture.successful("The Future is here"))
   * }}}
   * @param underlying The type to be converted
   * @return The converted type
   * @since 1.0
   */
  def asScalaFuture[T](underlying: JFuture[T]) = {
    val promise = Promise[T]
    underlying.onComplete(new Consumer[JTry[T]]() {
      def accept(result: JTry[T]) {
        promise.complete(Converters.asScalaTry(result))
      }
    })
    promise.future
  }
}