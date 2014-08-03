import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DbClient {
	/*
	 * Player - No of words broken successfully
	 * Player - No of words failed
	 * Player - No of words contributed for guessing
	 * word - which player guessed in least number of trails
	 * word - easy, medium and hard words
	 *  
	 * 
	 */

	public static MongoClient mongoClient;
	public static DBCollection testCollection;
	public static DB myDB;

	public static void getDBConnection() throws UnknownHostException {
		mongoClient = new MongoClient();
		myDB = mongoClient.getDB("mydb");
		testCollection = myDB.getCollection("testColl");	
	}



	public static void main(String[] args) throws UnknownHostException{
		// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
		// if it's a member of a replica set:
		mongoClient = new MongoClient();
		//	// or
		//	MongoClient mongoClient = new MongoClient( "localhost" );
		//	// or
		//	MongoClient mongoClient = new MongoClient("localhost" , 27017);
		//	// or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
		//	MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
		//	                                      new ServerAddress("localhost", 27018),
		//	                                      new ServerAddress("localhost", 27019)));

		myDB = mongoClient.getDB("mydb");

		Set<String> colls = myDB.getCollectionNames();

		for (String s : colls) {
			System.out.println(s);
		}

		testCollection = myDB.getCollection("testColl");
		//
		//		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
		//		.append("type", "database")
		//		.append("count", 1)
		//		.append("info", new BasicDBObject("x", 203).append("y", 102));
		//		coll.insert(doc);
		//		
		//
		BasicDBObject doc = new BasicDBObject("x", 203)
		.append("y", 102)
		.append("z", 111);
		testCollection.insert(doc);

		DBObject myDoc = testCollection.findOne();
		System.out.println(myDoc);

		//		for (int i=0; i < 100; i++) {
		//		    coll.insert(new BasicDBObject("i", i));
		//		}
		//		

		System.out.println(testCollection.getCount());

		//removeAll(coll);

		BasicDBObject query = new BasicDBObject("x", 201);

		DBCursor cursor = testCollection.find(query);

		try {
			while(cursor.hasNext()) {
				//  System.out.println(cursor.next());
				DBObject o = cursor.next();
				for(String s : o.keySet()){
					System.out.println(s +": " + o.get(s));
				}
			}
		} finally {
			cursor.close();
		}


	}

	public static void insertInTestCollection(BasicDBObject doc){
		testCollection.insert(doc);
	}

	public static void removeAll(DBCollection coll){
		DBCursor cursor = coll.find();
		try {
			while(cursor.hasNext()) {
				//  System.out.println(cursor.next());
				coll.remove(cursor.next());
			}
		} finally {
			cursor.close();
		}


	}

}


