package uk.co.bocuma.mailinator.test

import org.scalatest._

import scala.util.{Try, Success, Failure}
import scala.concurrent._

import uk.co.bocuma.mailinator._

import co.freeside.betamax.Betamax
import co.freeside.betamax.Recorder
import co.freeside.betamax.proxy.jetty.ProxyServer
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ FlatSpec, Matchers, ParallelTestExecution }
import scala.concurrent.ExecutionContext.Implicits.global
import concurrent.AsyncAssertions
import org.scalatest.time.SpanSugar._






class InboxSpec extends FlatSpec with Matchers with AsyncAssertions {
  it should "return return a success instance" in {
    val recorder = new Recorder
    val proxyServer = new ProxyServer(recorder)
    val w = new Waiter
    recorder.insertTape("Inbox.latestEmails")
    proxyServer.start()

    val mailbox = new Mailbox("test@mailinator.com")
    for ( emails <- mailbox.latestEmails) {
      emails shouldBe a [Success[_]]
      recorder.ejectTape()
      proxyServer.stop()
      w.dismiss

    }
    w.await(timeout(40000 millis))
  }
  it should "return return a list of emails" in {

    val mailbox = new Mailbox("test@mailinator.com")
    val w = new Waiter
    val recorder = new Recorder
    val proxyServer = new ProxyServer(recorder)
    recorder.insertTape("Inbox.latestEmails")
    proxyServer.start()
    for ( emails <- mailbox.latestEmails) {
      emails match {
        case Success(l::ls) => 
          w { l shouldBe a [Email] }
          recorder.ejectTape()
          proxyServer.stop()
          w.dismiss
      }
    }
    w.await(timeout(40000 millis))
  }

}
