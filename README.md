mailinator-scala-api
====================

### Examples

```
package uk.co.bocuma.mailinator.examples
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import scala.concurrent._
import scala.concurrent.duration._
import uk.co.bocuma.mailinator._

object Start {
  def main(args: Array[String]) {
    val mailbox = Mailbox("mark@mailinator.com")
    val email  = mailbox.getEmail("1415552990-5430203-mark")
    Await.ready(email, 15 minutes)
    email onComplete {
      case Success(email) => 
        println(email)
      case Failure(e) => println(e.getMessage())
    }

  }
}
```
