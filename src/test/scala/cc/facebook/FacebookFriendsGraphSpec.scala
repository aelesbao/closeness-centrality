package cc.facebook

import java.net.HttpURLConnection

import com.restfb._
import org.scalatest.{ FlatSpec, Matchers }

class FacebookFriendsGraphSpec extends FlatSpec with Matchers {
  val friendsJson =
    """
      |{
      |  "data": [
      |    {
      |      "id": "1234567890",
      |      "name": "John Doe"
      |    },
      |  ],
      |  "paging": {
      |    "next": "https://graph.facebook.com/v2.0/123"
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
      |          "id": "9012345678",
      |          "name": "Mary Doe"
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

  "Graph" should "fetch data from Facebook Graph API" in {
    val graph = FacebookFriendsGraph(client)
    graph should have size(2)
    graph.vertices should contain allOf(UserNode("1234567890", "John Doe"), UserNode("9012345678", "Mary Doe"))
  }
}
