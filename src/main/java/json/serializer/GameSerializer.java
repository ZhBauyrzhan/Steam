package json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import model.Game;

public class GameSerializer extends StdSerializer<Game>{
	public GameSerializer() {
		super(Game.class);
	}
	@Override
	public void serialize(Game game, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField(Game.FIELD_ID, game.getId());
		gen.writeStringField(Game.FIELD_NAME, game.getName());
		gen.writeStringField(Game.FIELD_DESCRIPTION, game.getDescription());
		gen.writeNumberField(Game.FIELD_PRICE, game.getPrice());
		gen.writeEndObject();
	}
}