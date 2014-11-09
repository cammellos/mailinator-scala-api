package uk.co.bocuma.mailinator

import net.ceedubs.ficus.Ficus._
import com.typesafe.config.ConfigFactory


object Mailinator extends MailinatorImpl



trait MailinatorImpl {

  val config = ConfigFactory.load()
  val environment = Option(System.getenv("SCALA_ENV")).getOrElse("development")
  val baseUrl = "https://api.mailinator.com/api/"

  val appKey = Option(System.getenv("TFL_API_KEY")) match {
    case None => config.as[Option[String]]( s"$environment.appKey" ) match {
      case Some(value) => value
      case _ => throw new ConfigurationException("appKey")
      }
   case Some(value) => value
  }

  class ConfigurationException(method: String) extends Throwable {
    override def toString = "You must supply "+ method +" in the configuration file"
  }
}
