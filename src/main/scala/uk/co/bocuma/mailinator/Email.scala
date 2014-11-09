
package uk.co.bocuma.mailinator
import java.util.Date
case class Email(id: String, to: String, time: Date, subject: String, fromFull: String, beenRead: Boolean, from: String, ip: String) {

}
