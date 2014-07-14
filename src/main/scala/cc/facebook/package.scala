package cc

import com.restfb.types.User

package object facebook {
  import scala.language.implicitConversions

  implicit def facebookUserToUserNode(user: User) = UserNode(user.getId, user.getName)
  implicit def facebookUserListToUserNodes(users: Iterable[User]) = users.map { facebookUserToUserNode }
}
