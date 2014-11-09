package uk.co.bocuma.mailinator.exceptions

case class MailinatorRateLimitException(msg: String = MailinatorRateLimitException.RateLimitMessage) extends MailinatorBaseException(msg)
object MailinatorRateLimitException {
  val RateLimitMessage = "rate limiter reached"
}
