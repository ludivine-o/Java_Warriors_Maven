package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import warriors.engine.HeroCharacter;

public class HeroDAO extends DAO<HeroCharacter> {

	public Scanner scan = new Scanner(System.in);
	
	public HeroDAO(Connection conn) {
		super(conn);
	}
	
	
	
	public boolean create(HeroCharacter obj) {
		HeroCharacter myHero = new HeroCharacter();
		try {
//			System.out.println();
//			System.out.println("Entrez le nom du Hero:");
//			String playerName = scan.nextLine();
//			
//			System.out.println("Entrez la race du Hero:");
//			String typeName = scan.nextLine();
//			
//			System.out.println("Entrez l'url du Hero:");
//			String url = scan.nextLine();
			
			String playerName = obj.getName();
			String typeName = obj.getType();
			
			
//			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
//					.executeQuery("INSERT INTO Hero (Type, Nom, Image, NiveauDeVie, NiveaudAttaque, EquipementAttaque, EquipementDefense) "
//							+ "VALUES ('"+typeName+"', '"+playerName+"', '123', 5, 5, 'Aucun', 'Aucun');");
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
			.executeUpdate("INSERT INTO Hero (Type, Nom, Image, NiveauDeVie, NiveaudAttaque, EquipementAttaque, EquipementDefense) "
					+ "VALUES ('"+typeName+"', '"+playerName+"', '123', 5, 5, 'Aucun', 'Aucun');");
			this.findAll();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(HeroCharacter obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(HeroCharacter obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HeroCharacter find(int id) {
		HeroCharacter myHero = new HeroCharacter();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Hero WHERE Id = " + id);
			if (result.first()) {
				myHero = new HeroCharacter(id, result.getString("Type"), result.getString("Nom"), result.getString("Image"),
						result.getInt("NiveauDeVie"), result.getInt("NiveaudAttaque"),
						result.getString("EquipementAttaque"), result.getString("EquipementDefense"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myHero;
		//TODO 20/05 : renvoyer null si pas de Hero trouvé
	}

	@Override
	public List<HeroCharacter> findAll() {
		List<HeroCharacter> myHeroes = new ArrayList<HeroCharacter>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Hero");
			//int i = 0;
			while(result.next()) {
				myHeroes.add(new HeroCharacter(result.getInt("Id"), result.getString("Type"), result.getString("Nom"), result.getString("Image"),
						result.getInt("NiveauDeVie"), result.getInt("NiveaudAttaque"),
						result.getString("EquipementAttaque"), result.getString("EquipementDefense")));
			}
			result.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return myHeroes;
		// https://www.jtips.info/Tutoriel_Java
	}
}

