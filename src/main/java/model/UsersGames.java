package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usersGames")
public class UsersGames implements Model{
	@DatabaseField(id = true, columnName = "id")
	private int id;
	@DatabaseField(columnName = "userId", foreign = true, foreignAutoRefresh = true)
	private User userId;
	@DatabaseField(columnName = "gameId",foreign = true, foreignAutoRefresh = true)
	private Game gameId;
	public static final String FIELD_ID = "id";
	public static final String FIELD_USER_ID = "userId";
	public static final String FIELD_GAME_ID = "gameId";
	public UsersGames(int id,User userId, Game gameId) {
		setId(id);
		this.userId = userId;
		this.gameId = gameId;
	}
	public UsersGames() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setId(int id) {
		this.id = id; 
	}
	@Override
	public int getId() {
		return id;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Game getGameId() {
		return gameId;
	}
	public void setGameId(Game gameId) {
		this.gameId = gameId;
	}
	
	
}