package org.migdb.migdbclient.resources;

import org.migdb.migdbclient.controllers.dbconnector.MongoConnManager;

import com.mongodb.client.MongoDatabase;

public enum DatabaseResource {
	INSTANCE;
	private MongoDatabase database;
	private String databaseName;

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDB(String databaseName) {
		this.databaseName = databaseName;
		try {
			this.database = MongoConnManager.INSTANCE.connectToDatabase(databaseName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getDatabaseName() {
		return databaseName;
	}

}
