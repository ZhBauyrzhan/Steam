package json.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import model.User;

public class UserDeserializer extends StdDeserializer<User>{
	public UserDeserializer() {
		super(User.class);
	}
	@Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException{
		JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        int id = root.get(User.FIELD_ID ).asInt();
        String firstName = root.get(User.FIELD_FIRST_NAME).asText();
        String lastName = root.get(User.FIELD_LAST_NAME).asText();
        String login = root.get(User.FIELD_LOGIN).asText();
        String phone = root.get(User.FIELD_PHONE).asText();
        String email = root.get(User.FIELD_EMAIL).asText();
        String password = root.get(User.FIELD_PASSWORD).asText();
        String birthDay= root.get(User.FIELD_BIRTH_DAY).asText();
        String role= root.get(User.FIELD_ROLE).asText();
        return new User(id, firstName, lastName, login, phone, email, password, birthDay, role);
	}
}