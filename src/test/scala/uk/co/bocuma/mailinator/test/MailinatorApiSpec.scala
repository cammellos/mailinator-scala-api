
package uk.co.bocuma.mailinator.test

import org.scalatest._

import scala.util.{Try, Success, Failure}
import uk.co.bocuma.mailinator._

class MailinatorApiSpec extends FlatSpec with Matchers {
  class MailinatorTestApi extends MailinatorApi { 
    def extractObjectTest[T](json: String, path: String)(implicit man: Manifest[T] ): Try[T] = {
      extractObject[T](json,path)
    }
  }

  val sampleInboxResponse = "{\"messages\":[{\"seconds_ago\":12726,\"id\":\"1415541545-4745864-mark\",\"to\":\"mark@mailinator.com\",\"time\":1415541545826,\"subject\":\"3 Ways to Perfect your Persian Speaking & A New Feature!\",\"fromfull\":\"alerts@persianpod101.com\",\"been_read\":false,\"from\":\"PersianPod101.com\",\"ip\":\"65.98.250.177\"},{\"seconds_ago\":12638,\"id\":\"1415541633-4752404-mark\",\"to\":\"mark@mailinator.com\",\"time\":1415541633240,\"subject\":\"2 members have liked your profile since last time you checked!\",\"fromfull\":\"do-not-reply@farmersonly.com\",\"been_read\":false,\"from\":\"FarmersOnly Notification\",\"ip\":\"184.173.105.149\"},{\"seconds_ago\":12555,\"id\":\"1415541716-4757626-mark\",\"to\":\"mark@mailinator.com\",\"time\":1415541716753,\"subject\":\"10 Phrases Parents Should Not Say to their Toddlers | Your Brain on Political Outrage | Surprising Truth About Driving While High\",\"fromfull\":\"replies@alternet.org\",\"been_read\":false,\"from\":\"AlterNet Headlines\",\"ip\":\"198.202.148.68\"},{\"seconds_ago\":12330,\"id\":\"1415541941-4769349-mark\",\"to\":\"mark@mailinator.com\",\"time\":1415541941047,\"subject\":\"Make money online\",\"fromfull\":\"acmabogados@acmabogados.es\",\"been_read\":false,\"from\":\"mark@mailinator.com\",\"ip\":\"178.40.195.246\"},{\"seconds_ago\":12274,\"id\":\"1415541997-4771981-mark\",\"to\":\"mark@sogetthis.com\",\"time\":1415541997433,\"subject\":\"Our Best Selling 18 to 19 Movies\",\"fromfull\":\"customerservice@gamelink.com\",\"been_read\":false,\"from\":\"GameLink\",\"ip\":\"208.66.207.73\"},{\"seconds_ago\":12129,\"id\":\"1415542142-4779986-mark\",\"to\":\"mark@zippymail.info\",\"time\":1415542142982,\"subject\":\"Mark, you have 1 message and 1 friend request\",\"fromfull\":\"update+zrdo6ppcgr6z@facebookmail.com\",\"been_read\":false,\"from\":\"Facebook\",\"ip\":\"66.220.144.173\"},{\"seconds_ago\":12045,\"id\":\"1415542226-4784668-mark\",\"to\":\"mark@mailinator.com\",\"time\":1415542226864,\"subject\":\"want to win? share your 5B wish list!\",\"fromfull\":\"fivebelow@hotstuff.fivebelow.com\",\"been_read\":false,\"from\":\"five below\",\"ip\":\"199.7.204.50\"},{\"seconds_ago\":12043,\"id\":\"1415542228-4784739-mark\",\"to\":\"mark@mailinater.com\",\"time\":1415542228304,\"subject\":\"V, On the Love of Persons in authority with\",\"fromfull\":\"zijidelu@li598-203.members.linode.com\",\"been_read\":false,\"from\":\"zijidelu@li598-203.members.linode.com\",\"ip\":\"198.58.109.203\"},{\"seconds_ago\":10941,\"id\":\"1415543330-4848769-mark\",\"to\":\"mark@mailinater.com\",\"time\":1415543330937,\"subject\":\"- no subject given\",\"fromfull\":\"Lyndon_Poitier@ddlinc.com\",\"been_read\":false,\"from\":\"Lyndon Poitier\",\"ip\":\"24.214.235.158\"},{\"seconds_ago\":10277,\"id\":\"1415543994-4885996-mark\",\"to\":\"mark@mailtothis.com\",\"time\":1415543994315,\"subject\":\"Photographs from the hottest naked night party in a bamboo cafe for your enjoyment!\",\"fromfull\":\"gabriellehargrovey@firstview.com\",\"been_read\":false,\"from\":\"Elfreda Will\",\"ip\":\"95.173.183.230\"}]}"

  val sampleEmailResponse = """{"apiInboxFetchesLeft":62,"apiEmailFetchesLeft":10,"data":{"headers":{"content-type":"multipart\/alternative; charset=\"UTF-8\"; boundary=\"b1_7708462a3253388032fd88349ca325b0\"","to":"mark@mailinator.com","x-mailer-sent-by":"1","x-mailer-sid":"45","domainkey-signature":"a=rsa-sha1; c=nofws; q=dns; s=dkim; d=esp955.info;\r\n b=kHkSfunBgaUuay\/8J9ItKAps6NJTIt37g+S70hNW3j8Tv2255ddK6BK0E8D++s7\/dVeWfIXsLWWY\r\n   JbIzUVtvMZrdzeRRllIBIjAfBSiVbbpKcJ+Xkku0fbNNXSxnjamZ\/Pza9Qsk38VqSkvjn8ybleMi\r\n   cQRWcHPviUfpO1pgAg8=;","subject":"ExclusiveOffer From JackPotGrand $20FREE","content-transfer-encoding":"8bit","mime-version":"1.0","x-received-time":"1415546677786","received":"from cha41.esp955.info ()\r\n        by mail.mailinator.com with SMTP (Postfix)\r\n        for mark@mailinator.com;\r\n        Sun, 09 Nov 2014 15:24:37 +0000 (UTC)","dkim-signature":"v=1; a=rsa-sha1; c=relaxed\/relaxed; s=dkim; d=esp955.info;\r\n h=To:Subject:Message-ID:Date:From:Reply-To:MIME-Version:List-Unsubscribe:Content-Type:Content-Transfer-Encoding; i=bounce@esp955.info;\r\n bh=HIhT9rVZM5KcDrRIcQ0f0UiIDUk=;\r\n b=TxrMxEurr9cQBLwHoKBLJ7JjM0hM7NddtDx1Dwr7iSlOHMmmde7tmWS+rt74urrigc1Pq7o1huSE\r\n   nGagYcXK6KdsiO\/95QyA7AMGT7Rg8CYTDexgx1mdQbZJyUlhUTt7xBtFznPUFNbUAPksJEMGmzrh\r\n   5NwIHX6t7OxxVfM9wYg=","from":"\"Support\" <info@esp955.info>","list-unsubscribe":"<http:\/\/esp955.info\/unsubscribe.php?M=223505&C=93e0deda4af911033c2c684cf801beb9&L=5&N=45>","date":"Sat, 08 Nov 2014 14:11:33 +0530","x-mailer-lid":"1,2,3,4,5,6,7","sender":"bounce@esp955.info","x-connecting-ip":"176.10.123.41","x-mailer-recptid":"223505","reply-to":"info@esp955.info","return-path":"bounce@esp955.info"},"seconds_ago":12708,"id":"1415546677-5056174-mark","to":"mark@mailinator.com","time":1415546677786,"subject":"ExclusiveOffer From JackPotGrand $20FREE","fromfull":"info@esp955.info","parts":[{"headers":{"content-type":"text\/plain; format=flowed; charset=\"UTF-8\"","content-transfer-encoding":"8bit"},"body":"Your email client cannot read this email.\r\nTo view it online, please go here:\r\nhttp:\/\/esp955.info\/display.php?M=223505&C=93e0deda4af911033c2c684cf801beb9&S=45&L=5&N=39\r\n\r\nExclusiveOffer From JackPotGrand $20FREE\r\n\r\n\r\nhttp:\/\/esp955.info\/link.php?M=223505&N=45&L=44&F=T\r\n\r\n\r\nTo stop receiving these\r\nemails:http:\/\/esp955.info\/unsubscribe.php?M=223505&C=93e0deda4af911033c2c684cf801beb9&L=5&N=45\r\n"},{"headers":{"content-type":"text\/html; charset=\"UTF-8\"","content-transfer-encoding":"8bit"},"body":"<html>\r\n<head>\r\n<\/head>\r\n<body>\r\n<pre><span style=\"color: #ff0000; background-color: #ffff00;\"><strong><span\r\nstyle=\"font-size: x-large;\">JackPotGrandCasino<\/span><\/strong><\/span><br\r\n\/><br \/><br \/><span style=\"font-size: x-large;\">ExclusiveOffer From <a\r\nhref=\"http:\/\/esp955.info\/link.php?M=223505&N=45&L=45&F=H\">JackPotGrand<\/a><\/span><br\r\n\/><br \/><strong><span style=\"font-size:\r\nx-large;\">$20FREE<\/span><\/strong><br \/><br \/><span style=\"text-decoration:\r\nunderline; background-color: #00ff00;\"><strong><span style=\"font-size:\r\nx-large;\">UseBonusCode: CHIPSFREE20<\/span><\/strong><\/span><br \/><br \/><br\r\n\/><span style=\"font-size: x-large;\">&bull; HighPayoutRate - Cashouts are\r\nquick &amp; easy!<\/span><br \/><span style=\"font-size: x-large;\">&bull; Best\r\nGameSelection - play all your favorites!<\/span><br \/><span\r\nstyle=\"font-size: x-large;\">&bull; Amazing WeeklyBonuses -\r\nslots&amp;tables!<\/span><br \/><span style=\"font-size: x-large;\">&bull; 24\/7\r\nSupport - always available to help!<br \/><br \/><a\r\nhref=\"http:\/\/esp955.info\/link.php?M=223505&N=45&L=45&F=H\">Join Now<\/a><br\r\n\/><br \/><br \/><a\r\nhref=\"http:\/\/esp955.info\/unsubscribe.php?M=223505&C=93e0deda4af911033c2c684cf801beb9&L=5&N=45\">Unsubscribe\r\nme from this list<\/a>&nbsp;<\/span><\/pre>\r\n<img src=\"http:\/\/esp955.info\/open.php?M=223505&L=5&N=45&F=H&image=.jpg\"\r\nheight=\"1\" width=\"10\"><\/body>\r\n<\/html>\r\n"}],"been_read":false,"from":"Support","ip":"176.10.123.41"},"forwardsLeft":10}"""

  it should "fail if error is returned" in {
    val testApi = new MailinatorTestApi()
    testApi.extractObjectTest[Email]("{\"error\":\"rate limiter reached\"}","data") shouldBe a [Failure[Email]]
  }

  it should "fail if error is returned and return custom message" in {
    val testApi = new MailinatorTestApi()
    val customMessage = "custom message"
    val json = "{\"error\":\"" + customMessage + "\"}"
    val result = testApi.extractObjectTest[Email](json,"data")
    result shouldBe a [Failure[Email]]
    result match {
      case Failure(e) => e.getMessage should equal(customMessage)
    }
  }
  it should "correctly return a list of messages" in {
    val testApi = new MailinatorTestApi()
    val result = testApi.extractObjectTest[List[Email]](sampleInboxResponse,"messages")
    result shouldBe a [Success[List[Email]]]
    result match {
      case Success(emails) => emails.length should equal(10)
    }
  }

  it should "parse correctly a list of messages" in {
    val testApi = new MailinatorTestApi()
    val result = testApi.extractObjectTest[List[Email]](sampleInboxResponse,"messages")
    result shouldBe a [Success[List[Email]]]
    result match {
      case Success(emails) => 
        val email = emails.head
        email.id should equal("1415541545-4745864-mark")
        email.to should equal("mark@mailinator.com")
        email.subject should equal("3 Ways to Perfect your Persian Speaking & A New Feature!")
        email.ip should equal("65.98.250.177")
        email.time.getTime() should equal(new java.util.Date((1415541545826L) / 1000 * 1000).getTime())
        email.from should equal("PersianPod101.com")
        email.fromFull should equal("alerts@persianpod101.com")
        email.beenRead should equal(false)

    }
  }

  it should "parse correctly an email" in {
    val testApi = new MailinatorTestApi()
    val result = testApi.extractObjectTest[Email](sampleEmailResponse,"data")
    result shouldBe a [Success[Email]]
    result match {
      case Success(email) => 
        email.id should equal("1415546677-5056174-mark")
        email.to should equal("mark@mailinator.com")
        email.subject should equal("ExclusiveOffer From JackPotGrand $20FREE")
        email.ip should equal("176.10.123.41")
        email.time.getTime() should equal(new java.util.Date((1415546677786L) / 1000 * 1000).getTime())
        email.from should equal("Support")
        email.fromFull should equal("info@esp955.info")
        email.beenRead should equal(false)

    }
  }





}
