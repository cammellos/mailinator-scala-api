package uk.co.bocuma.mailinator

import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import java.text.SimpleDateFormat
import scala.util.{Try, Success, Failure}


case class Mailbox(address: String) extends MailinatorApi {
  val inboxUrl = s"${Mailinator.baseUrl}inbox?to=${address}&token=${Mailinator.appKey}"

  def getEmail (emailId: String) : Future[Try[Email]] = {
    val emailUrl = s"${Mailinator.baseUrl}email?msgid=${emailId}&token=${Mailinator.appKey}"
    callApi(emailUrl) map  { json =>
      extractObject[Email](json,"data")
    }
  }
  def latestEmails: Future[Try[List[Email]]] = {
    callApi(inboxUrl) map { json =>
      extractObject[List[Email]](json,"messages")
    }
  }
}
