package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import boxes_maps.Board;
import warriors.contracts.Game;
import warriors.engine.HeroCharacter;

public class GameDAO extends DAO<Game>{

	public GameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Game obj) {

		//Game(String ID, String name, String heroName, String heroType, int heroLifeLevel, int heroAttackLevel, int currentCase)
		try {
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());

			String id = formatter.format(date);
			String gameName = "savedGame";
			String heroName = obj.getPlayerName();
			String heroType = obj.getHeroType();
			int heroLifeLevel = obj.getHeroLife();
			int heroAttackLevel = obj.getHeroAttack();
			int currentCase = obj.getCurrentCase();

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
			.executeUpdate("INSERT INTO Game (Id, Name, HeroName, HeroType, HeroLifeLevel, HeroAttackLevel, CurrentCase) "
					+ "VALUES ('"+id+"', '"+gameName+"', '"+heroName+"', '"+heroType+"', '"+heroLifeLevel+"', '"+heroAttackLevel+"' , '"+currentCase+"');");
			//this.findAll();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}




	@Override
	public boolean delete(Game obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Game obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
			.executeUpdate("UPDATE Game SET `HeroName`= '"+obj.getPlayerName() +"',`CurrentCase`="+obj.getCurrentCase()
			+",`HeroType`= '"+obj.getHeroType() +"',`HeroAttackLevel`= "+obj.getHeroAttack() +",`HeroLifeLevel`="+obj.getHeroLife() 
			+" WHERE `id`='"+obj.getGameId()+"';");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Game find(int id) {
		Game myGame = new Game();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Game WHERE Id = " + id);
			if (result.first()) {
				myGame = new Game(result.getString("IdGame"), "savedLoadMap", result.getString("HeroName"), 
						result.getString("HeroType"), result.getInt("HeroLife"), result.getInt("HeroAttack"), result.getInt("CurrentCase"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myGame;
		//TODO 20/05 : renvoyer null si pas d'objet trouvé
	}

	@Override
	public List<Game> findAll() {
		List<Game> myGames = new ArrayList<Game>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Hero");
			//int i = 0;
			while(result.next()) {
				myGames.add(new Game(result.getString("IdGame"), "savedLoadMap", result.getString("HeroName"), 
						result.getString("HeroType"), result.getInt("HeroLife"), result.getInt("HeroAttack"), result.getInt("CurrentCase")));
			}
			result.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return myGames;
		// https://www.jtips.info/Tutoriel_Java
	}

}
