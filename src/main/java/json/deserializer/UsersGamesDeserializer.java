package json.deserializer;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import model.UsersGames;
import service.UsersGamesService;

public class UsersGamesDeserializer extends StdDeserializer<UsersGames>{
	private UsersGamesService service;
	public UsersGamesDeserializer( UsersGamesService service ) {
		super(UsersGames.class);
		this.service = service;
	}
	@Override
    public UsersGames deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException{
		JsonNode root = jsonParser.getCodec().readTree(jsonParser);
		int id = root.get( UsersGames.FIELD_ID ).asInt();
		int userId = root.get(UsersGames.FIELD_USER_ID).asInt();
		int gameId = root.get(UsersGames.FIELD_GAME_ID).asInt();
        return new UsersGames(id,service.findUserById(userId), service.findGameById(gameId));
	}
}