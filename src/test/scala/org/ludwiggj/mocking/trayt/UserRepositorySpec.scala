package org.ludwiggj.mocking.trayt

import org.ludwiggj.mocking.{User, UserId}
import org.mockito.Mockito._
import org.scalatest.{FlatSpec, Matchers}

class UserRepositorySpec extends FlatSpec with Matchers {

  it should "always add user take 1" in {
    // Using simple in memory implementation
    val db = new InMemDB
    val userId = UserRepository(db).add(User("Joe"))

    userId shouldBe ("Joe")
    db.retrieve(userId).get.id shouldBe ("Joe")
  }

  it should "always add user take 2" in {
    // Using a mock
    val db = mock(classOf[DB[User, UserId]])


    when(db.store(User("Joe"))).thenReturn("Joe")

    val user = UserRepository(db).add(User("Joe"))

    user shouldBe "Joe"
  }

  it should "select user with short name take 1" in {

    // Using a real instance, implementing where necessary
    val db = new DB[User, String] {
      val users = Seq("Joe", "Nicolas", "Ruth", "Doe", "Maria")

      // Potentially need to implement everything here
      override def all(): Seq[String] = users
      override def store(a: User): String = ???
      override def retrieve(id: String): Option[User] = if (users.contains(id)) Some(User(id)) else None
    }

    val usersWithShortName = UserRepository(db).select(_.id.length <= 3)

    usersWithShortName should contain(User("Joe"))
    usersWithShortName should contain(User("Doe"))
  }

  // Now user trait to fill in the base case
  trait UserDBForTesting extends DB[User, String] {

    override def all(): Seq[String] = ???

    override def store(a: User): String = ???

    override def retrieve(id: String): Option[User] = ???
  }

  it should "select user with short name take 2" in {

    val db = new UserDBForTesting {
      val users = Seq("Joe", "Nicolas", "Ruth", "Doe", "Maria")
      override def all(): Seq[String] = users
      override def retrieve(id: String): Option[User] = if (users.contains(id)) Some(User(id)) else None
    }

    val usersWithShortName = UserRepository(db).select(_.id.length <= 3)

    usersWithShortName should contain(User("Joe"))
    usersWithShortName should contain(User("Doe"))
  }
}