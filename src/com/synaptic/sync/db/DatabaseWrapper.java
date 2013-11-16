package com.synaptic.sync.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import cpw.mods.fml.common.FMLLog;

public class DatabaseWrapper {

	private static Connection con;

	private static void connect() {
		String url = "jdbc:mysql://localhost:3306/Minecraft";
		String user = "synapticserver";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			FMLLog.severe("%s", ex.getMessage());
		}
	}

	private static void disconnect() {
		try {
			con.close();
		} catch (SQLException ex) {
			FMLLog.severe("%s", ex.getMessage());
		}
	}
	
	//Simple operations (Like playerLoggedIn/playerLoggedOut) don't need to be handled here for now. 
	// Will eventually integrate them for simplicity.
	// crudOP(s) will be generated in the "API" call (submitMods), then passed here for queue'd execution.
	// Perhaps have a thread exclusively for execution?
	// Try to avoid Selects? Shouldn't need to be bidirectional information exchange - the database SHOULD just
	// represent the server, so that any number of users can view the information without directly polling
	// the minecraft instance
	private static void queueDatabaseTransaction(/* DatabaseOperation crudOP */){
		connect();
		try {
			Statement stmt = con.createStatement();
			//Pass stmt to particular operation to handle
		} catch (SQLException e) {
			FMLLog.severe("%s", e.getMessage());
		}
		finally{
			disconnect();
		}
	}

	public static void submitMods(Map<String, String> mods) {
		
	}

	public static void submitVersions(Map<String, String> versions) {
		
	}

	public static void updateServerVersions(Map<String, String> versions) {
		//Keep internal cache of server versions? Or perhaps have a
		// stored proc which handles parsing server versions. 
		//Generate Insert statement
		//queueDatabaseTransaction();
	}
	
	public static void playerLoggedIn(String userName){
		connect();
		try {
			Statement stmt = con.createStatement();
			ResultSet userResult= stmt.executeQuery("SELECT id FROM user WHERE username = \"" + userName + "\"");
			if(userResult.next()){
				stmt.execute("INSERT INTO online(user_id) VALUES (" + userResult.getInt("id") + ")");
			}
		} catch (SQLException e) {
			FMLLog.severe("%s", e.getMessage());
		}
		finally{
			disconnect();
		}
	}
	
	public static void playerLoggedOut(String userName){
		connect();
		try {
			Statement stmt = con.createStatement();
			ResultSet userResult= stmt.executeQuery("SELECT id FROM user WHERE username = \"" + userName + "\"");
			if(userResult.next()){
				stmt.execute("DELETE from online WHERE user_id = " + userResult.getInt("id"));
			}
		} catch (SQLException e) {
			FMLLog.severe("%s", e.getMessage());
		}
		finally{
			disconnect();
		}
	}

}
