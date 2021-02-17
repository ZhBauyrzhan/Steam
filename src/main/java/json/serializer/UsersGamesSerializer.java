package json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import model.Game;
import model.User;
import model.UsersGames;
import service.UsersGamesService;

public class UsersGamesSerializer extends StdSerializer<UsersGames>{
	private UsersGamesService service;
	public UsersGamesSerializer(UsersGamesService service) {
		super(UsersGames.class);
		this.service = service;
	}
	@Override
	public void serialize(UsersGames usersGames, JsonGenerator gen, SerializerProvider provider) throws IOException {
		User user = service.findUserById(usersGames.getUserId().getId());
		Game game = service.findGameById(usersGames.getGameId().getId());
		gen.writeStartObject();
		gen.writeNumberField(UsersGames.FIELD_ID, usersGames.getId());
		gen.writeObjectField(UsersGames.FIELD_USER_ID,user);
		gen.writeObjectField(UsersGames.FIELD_GAME_ID,game);
		gen.writeEndObject();
	}
}