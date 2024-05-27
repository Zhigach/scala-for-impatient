class Email(val username: String, val domainName: String)

object Email:
  def fromString(emailString: String): Option[Email] =
    emailString.split('@') match
      case Array(a, b) => Some(Email(a, b))
      case _ => None

val scalaCenterEmail = Email.fromString("scala.center@epfl.ch")
scalaCenterEmail match
  case Some(email) => println(
    s"""Registered an email
       |Username: ${email.username}
       |Domain name: ${email.domainName}
     """.stripMargin)
  case None => println("Error: could not parse email")