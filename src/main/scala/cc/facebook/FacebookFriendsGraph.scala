package cc.facebook

import cc.graph._

import scala.collection.JavaConversions._

case class UserNode(id: String, name: String) {
  def equals(other: UserNode) = this.id == other.id

  override def toString() = s"$name ($id)"
}

class FacebookFriendsGraph extends Graph[UserNode]

object FacebookFriendsGraph {
  def apply(implicit client: FacebookClient) = {
    val friends = client.getFriends()
    val edges = friends.map(f => collectEdges(f)).flatten
    new FacebookFriendsGraph ++ edges
  }

  protected def collectEdges(friend: UserNode)(implicit client: FacebookClient): Iterable[(UserNode, UserNode)] = {
    val mutualFriends: Iterable[UserNode] = client.getMutualFriends(friend.id).toIterable
    mutualFriends.map(mutualFriend => (friend, mutualFriend))
  }
}