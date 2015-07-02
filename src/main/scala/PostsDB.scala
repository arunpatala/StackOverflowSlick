import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import slick.backend.DatabasePublisher
import slick.driver.MySQLDriver.api._
//import slick.driver.MySQLDriver.simple._

// The main application
object PostsDB{
  val db = Database.forConfig("mysql")
//  try {

    // The query interface for the Suppliers table
    val posts  = TableQuery[Posts]

    def create = Await.result(db.run((posts.schema).create), Duration.Inf)
    
    def load = {
	val cmd = sqlu"LOAD XML LOCAL INFILE '/home/arun/code2vec/Posts.xml' INTO TABLE POSTS"
	Await.result(db.run(cmd), Duration.Inf)
    }

    def delete = Await.result(db.run(posts.delete), Duration.Inf)
//  } finally db.close
}
