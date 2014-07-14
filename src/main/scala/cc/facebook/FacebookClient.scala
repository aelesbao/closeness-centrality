package cc.facebook

import com.restfb._
import com.restfb.json.JsonObject
import com.restfb.types.User

class FacebookClient(accessToken: String, appSecret: String)(implicit webRequestor: WebRequestor = new DefaultWebRequestor())
  extends DefaultFacebookClient(accessToken, appSecret, webRequestor, new DefaultJsonMapper()) {

  def getFriends(userId: String = "me") = {
    val params = Parameter.`with`("limit", 5000)
    fetchConnection(s"$userId/friends", classOf[User], params).getData
  }

  def getMutualFriends(userId: String) = {
    val params = Parameter.`with`("fields", "context.fields(mutual_friends.limit(5000))")
    val mutualFriends = fetchObject(s"$userId", classOf[JsonObject], params)
    val friendsJson = mutualFriends.getJsonObject("context").getJsonObject("mutual_friends").toString
    getJsonMapper.toJavaList(friendsJson, classOf[User])
  }
}
