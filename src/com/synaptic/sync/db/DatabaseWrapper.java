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

	public static void connect() {
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

	public static void disconnect() {
		try {
			con.close();
		} catch (SQLException ex) {
			FMLLog.severe("%s", ex.getMessage());
		}
	}

	public static void submitMods(Map<String, String> mods) {
		for (String key : mods.keySet()) {
			if (con != null) {
				try {
					Statement stmt = con.createStatement();
					stmt.execute("INSERT INTO mod(mod_id, name) VALUES (\""
							+ key + "\",\"" + mods.get(key)
							+ "\") ON DUPLICATE KEY UPDATE name = VALUES(name)");
				} catch (SQLException e) {
					FMLLog.severe("%s", e.getMessage());
				}
			}
		}
	}

	public static void submitVersions(Map<String, String> versions) {
		for (String key : versions.keySet()) {
			if (con != null) {
				try {
					Statement stmt = con.createStatement();
					stmt.execute("INSERT INTO version(mod_id, version) VALUES (\""
							+ key
							+ "\","
							+ versions.get(key)
							+ "\") ON DUPLICATE KEY UPDATE version = VALUES(version)");
				} catch (SQLException e) {
					FMLLog.severe("%s", e.getMessage());
				}
			}
		}
	}

	public static void updateServerVersions(Map<String, String> versions) {
		String clearServerVersions = "DELETE FROM server_versions";
		String selectTemplate = "SELECT id from version WHERE version.mod_id = %s AND version.version = %s";
		String insertTemplate = "INSERT INTO server_versions(version_id) VALUES %s ON DUPLICATE KEY UPDATE version_id = VALUES(version_id)";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(clearServerVersions);
			for (String mod_id : versions.keySet()) {
				ResultSet result = stmt.executeQuery(String.format(
						selectTemplate, mod_id, versions.get(mod_id)));
				if (result.next()) {
					stmt.execute(String.format(insertTemplate,
							result.getString("id")));
				}
			}
		} catch (SQLException e) {
			FMLLog.severe("%s", e.getMessage());
		}
	}
	
	public static void userLoggedIn(String userName){
		try {
			Statement stmt = con.createStatement();
			ResultSet userResult= stmt.executeQuery("SELECT id FROM user WHERE username = " + userName);
			if(userResult.next()){
				stmt.execute("INSERT INTO online(user_id) VALUES (" + userResult.getInt("id") + ")");
			}
		} catch (SQLException e) {
			FMLLog.severe("%s", e.getMessage());
		}
	}
}
