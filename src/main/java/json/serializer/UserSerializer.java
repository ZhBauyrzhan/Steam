package json.serializer;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import model.User;

public class UserSerializer extends StdSerializer<User>{
	public UserSerializer() {
		super(User.class);
	}
	@Override
	public void serialize(User user, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField(User.FIELD_ID, user.getId());
		gen.writeStringField(User.FIELD_FIRST_NAME, user.getFirstName());
		gen.writeStringField(User.FIELD_LAST_NAME, user.getLastName());
		gen.writeStringField(User.FIELD_LOGIN, user.getLogin());
		gen.writeStringField(User.FIELD_PHONE, user.getPhone());
		gen.writeStringField(User.FIELD_EMAIL, user.getEmail());
		gen.writeStringField(User.FIELD_BIRTH_DAY, user.getBirthName());
		gen.writeStringField(User.FIELD_ROLE, user.getRole());
		gen.writeEndObject();
	}
}