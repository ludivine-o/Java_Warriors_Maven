package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	private static String url ="jdbc:mysql://localhost:3306/DnD?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "ludivineo";
	private static String passwd = "CN-9564";
	private static Connection connect;

	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}      
		return connect;
	}   

}



//	public void getHeroes() {
//		try {
//			Connection conn = DriverManager.getConnection(url, user, passwd);
//			// Création d'un objet Statement
//			Statement state = conn.createStatement();
//			// L'objet ResultSet contient le résultat de la requête SQL
//			ResultSet result = state.executeQuery("SELECT * FROM Hero");
//			// On récupère les MetaData
//			ResultSetMetaData resultMeta = result.getMetaData();
//
//			System.out.println("\n**********************************");
//			// On affiche le nom des colonnes
//			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
//				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
//
//			System.out.println("\n**********************************");
//
//			while (result.next()) {
//				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
//					System.out.print("\t" + result.getObject(i).toString() + "\t |");
//
//				System.out.println("\n---------------------------------");
//
//			}
//
//			result.close();
//			state.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void getHeroWithName(String nom) {
//		try {
//			Connection conn = DriverManager.getConnection(url, user, passwd);
//			// Création d'un objet Statement
//			Statement state = conn.createStatement();
//			// L'objet ResultSet contient le résultat de la requête SQL
//			ResultSet result = state.executeQuery("SELECT * FROM Hero WHERE Nom = '" + nom + "'");
//			// On récupère les MetaData
//			ResultSetMetaData resultMeta = result.getMetaData();
//
//			System.out.println("\n**********************************");
//			// On affiche le nom des colonnes
//			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
//				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
//
//			System.out.println("\n**********************************");
//
//			while (result.next()) {
//				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
//					System.out.print("\t" + result.getObject(i).toString() + "\t |");
//
//				System.out.println("\n---------------------------------");
//
//			}
//
//			result.close();
//			state.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void getHeroWithId(int id) {
//		try {
//			Connection conn = DriverManager.getConnection(url, user, passwd);
//			// Création d'un objet Statement
//			Statement state = conn.createStatement();
//			// L'objet ResultSet contient le résultat de la requête SQL
//			ResultSet result = state.executeQuery("SELECT * FROM Hero WHERE Id = '" + id + "'");
//			// On récupère les MetaData
//			ResultSetMetaData resultMeta = result.getMetaData();
//
//			System.out.println("\n**********************************");
//			// On affiche le nom des colonnes
//			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
//				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
//
//			System.out.println("\n**********************************");
//
//			while (result.next()) {
//				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
//					System.out.print("\t" + result.getObject(i).toString() + "\t |");
//
//				System.out.println("\n---------------------------------");
//
//			}
//
//			result.close();
//			state.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void createNewHero(String type, String nom, String url, int lifeLevel, int AttackLevel, String equipAttack, String equipDefense) {
//				
//		String query = "INSERT INTO Hero (Type, Nom, Image, NiveauDeVie, NiveaudAttaque, EquipementAttaque, EquipementDefense)   VALUES ('"+ type +"', '"+ nom +"', '"+ url +"', "+ lifeLevel +", "+ AttackLevel +", '"+equipAttack +"', '"+equipDefense+"')";
//		System.out.println(query);
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver"); 
//			Connection conn = DriverManager.getConnection(url, user, passwd);
//			//Création d'un objet Statement
//			Statement state = conn.createStatement();
////			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			int majNbr = state.executeUpdate(query);
//			System.out.println("Modif : " + majNbr);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}



