import slick.driver.MySQLDriver.api._
import slick.lifted.{ProvenShape, ForeignKeyQuery}
import java.sql.Timestamp

// A Suppliers table with 6 columns: id, name, street, city, state, zip
class Suppliers(tag: Tag)
  extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("SUP_ID", O.PrimaryKey)
  def name: Rep[String] = column[String]("SUP_NAME")
  def street: Rep[String] = column[String]("STREET")
  def city: Rep[String] = column[String]("CITY")
  def state: Rep[String] = column[String]("STATE")
  def zip: Rep[String] = column[String]("ZIP")
  
  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, String, String, String, String)] =
    (id, name, street, city, state, zip)
}

// A Coffees table with 5 columns: name, supplier id, price, sales, total
class Coffees(tag: Tag)
  extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {

  def name: Rep[String] = column[String]("COF_NAME", O.PrimaryKey, O.DBType("varchar(100)"))
  def supID: Rep[Int] = column[Int]("SUP_ID")
  def price: Rep[Double] = column[Double]("PRICE")
  def sales: Rep[Int] = column[Int]("SALES")
  def total: Rep[Int] = column[Int]("TOTAL")
  
  def * : ProvenShape[(String, Int, Double, Int, Int)] =
    (name, supID, price, sales, total)
  
  // A reified foreign key relation that can be navigated to create a join
  def supplier: ForeignKeyQuery[Suppliers, (Int, String, String, String, String, String)] = 
    foreignKey("SUP_FK", supID, TableQuery[Suppliers])(_.id)
}

class Posts(tag: Tag) extends Table[(Int,Int,Option[Int],Option[Int],Timestamp,Int,Int,String,Int,Int,String,Timestamp,Timestamp,Timestamp,Option[String],Option[String],Int,Int,Int)](tag, "POSTS") {
        def id = column[Int]("Id", O.PrimaryKey)
        def postTypeId = column[Int]("PostTypeId")
        def acceptedAnswerId = column[Option[Int]]("AcceptedAnswerId")
        def parentId = column[Option[Int]]("ParentId")
        def creationDate = column[Timestamp]("CreationTimestamp")
        def score = column[Int]("Score")
        def viewCount = column[Int]("ViewCount")
        def body = column[String]("Body")
        def ownerUserId = column[Int]("OwnerUserId")
        def lastEditorUserId = column[Int]("LastEditorUserId")
        def lastEditorDisplayName = column[String]("LastEditorDisplayName")
        def lastEditDate = column[Timestamp]("LastEditTimestamp")
        def lastActivityDate = column[Timestamp]("LastActivityTimestamp")
	def closedDate = column[Timestamp]("ClosedDate")
        def title = column[Option[String]]("Title")
        def tags = column[Option[String]]("Tags")
        def answerCount = column[Int]("AnswerCount")
        def commentCount = column[Int]("CommentCount")
        def favoriteCount = column[Int]("FavoriteCount")

	/* CreationDate, Id, LastActivityDate, Title, LastEditDate, LastEditorUserId, PostTypeId, Body, AnswerCount, ViewCount, Score, Tags, AcceptedAnswerId, LastEditorDisplayName, OwnerUserId, CommentCount, CommunityOwnedDate, FavoriteCount, ParentId, OwnerDisplayName, ClosedDate */

        def * = (id,postTypeId,acceptedAnswerId,parentId,
                creationDate, score,viewCount,body,ownerUserId,lastEditorUserId,lastEditorDisplayName,
                lastEditDate, lastActivityDate, closedDate, title,tags,answerCount,commentCount,favoriteCount)
}


