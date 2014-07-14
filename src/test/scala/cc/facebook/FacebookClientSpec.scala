package cc.facebook

import java.net.HttpURLConnection

import com.restfb._
import org.scalatest.{ FlatSpec, Matchers }

import scala.collection.JavaConversions._

class FacebookClientSpec extends FlatSpec with Matchers {
  val friendsJson =
    """
      |{
      |  "data": [
      |    {
      |      "id": "123456789",
      |      "name": "John Doe"
      |    }
      |  ],
      |  "paging": {
      |    "next": "https://graph.facebook.com/v2.0/9012345678"
      |  }
      |}
    """.stripMargin

  val mutualFriendsJson =
    """
      |{
      |  "context": {
      |    "mutual_friends": {
      |      "data": [
      |        {
      |          "id": "123456789",
      |          "name": "John Doe"
      |        }
      |      ],
      |      "summary": {
      |        "total_count": 1
      |      }
      |    }
      |  }
      |}
    """.stripMargin

  implicit val mockRequestor = new DefaultWebRequestor() {
    override def executeGet(url: String) = {
      val MutualFriendsUrl = """(^.*fields\=context\.fields.*mutual_friends.*$)""".r
      val responseJson = url match {
        case MutualFriendsUrl(_) => mutualFriendsJson
        case _                   => friendsJson
      }
      new WebRequestor.Response(HttpURLConnection.HTTP_OK, responseJson)
    }
  }

  val client = new FacebookClient("", "")

  "Client" should "get user friends" in {
    val friends = client.getFriends()
    friends.head should have('id("123456789"))
    friends.head should have('name("John Doe"))
  }

  it should "get mutual friends" in {
    val mutualFriends = client.getMutualFriends("123456789")
    mutualFriends.head should have('id("123456789"))
    mutualFriends.head should have('name("John Doe"))
  }
}
