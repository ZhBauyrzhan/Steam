package json.deserializer;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import model.Game;

public class GameDeserializer extends StdDeserializer<Game>{
	public GameDeserializer() {
		super(Game.class);
	}
	@Override
    public Game deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException{
		JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        int id = root.get(Game.FIELD_ID ).asInt();
        String name = root.get(Game.FIELD_NAME).asText();
        String description = root.get(Game.FIELD_DESCRIPTION).asText();
        int price = root.get(Game.FIELD_PRICE).asInt();
        return new Game( id, name,  description, price);
	}	
}