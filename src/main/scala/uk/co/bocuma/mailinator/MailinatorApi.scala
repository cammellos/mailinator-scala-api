
package uk.co.bocuma.mailinator
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.util.{Try, Success, Failure}

import uk.co.bocuma.mailinator.exceptions._


import dispatch._, Defaults._

trait MailinatorApi {
  implicit val formats =  DefaultFormats 
  protected def extractObject[T](json: String, path: String)(implicit man: Manifest[T] ): Try[T] = {
    val parsedJson = parseJSON(json)
    parsedJson match {
      case Success(pJson) =>
        val r = (pJson \ path) transformField {
          case ("been_read",x) => ("beenRead", x)
          case ("fromfull", x) => ("fromFull", x)
          case ("time",x) =>
            val format = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            ("time", JString(format.format(new java.util.Date(x.extract[Long]))))
        }
        Try(r.extract[T])
        case Failure(e) =>
          Try(throw e)
    }
  }
  protected def callApi(stringUrl: String) : Future[String] = {
    Http(url(stringUrl) OK as.String)
  }
  private def parseJSON(json: String) : Try[JValue] = {
    Try(checkError(json))
  }

  private def checkError(json: String) : JValue = {
    val j = parse(json)
    (j \ "error") match {
      case JNothing => j
      case any =>
        any.extract[String]  match { 
          case MailinatorRateLimitException.RateLimitMessage => throw new MailinatorRateLimitException()
          case any => throw new MailinatorException(any)
      }
    }
  }

}
