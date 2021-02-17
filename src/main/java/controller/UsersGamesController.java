package controller;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;
import model.User;
import model.UsersGames;
import service.UsersGamesService;

public class UsersGamesController extends AbstractController<UsersGames>{
	private final UsersGamesService service;
	private final ObjectMapper objectMapper;
	public UsersGamesController(UsersGamesService service, ObjectMapper objectMapper) {
        super(service, objectMapper, UsersGames.class);
        this.service = service;
        this.objectMapper = objectMapper;
	}
	@Override
	public Boolean checkRights(UsersGames model, Context context) {
		String senderLogin = context.basicAuthCredentials().getUsername();
		String senderPassword= context.basicAuthCredentials().getPassword();
		User user = service.findUserByLogin(senderLogin);
		return (BCrypt.checkpw(senderPassword, model.getUserId().getPassword()) && model.getUserId().getLogin().equals(senderLogin)
			|| user.getRole().equals(User.FIELD_ADMIN));
	}
	@Override
	public void getOne(Context context, int id) {
		UsersGames model = service.findById(id);
		if(checkRights(model, context)) {
			super.getOne(context, id);
		} else {
			context.status(403);
		}
	}
	@Override
    public void delete(Context context, int id) {
        UsersGames model = service.findById(id);
        if(checkRights(model, context)) {
        	service.delete(model);
        	context.status(204);
        }
    }
	@Override
    public void post(Context context) {
        try {
            UsersGames model = objectMapper.readValue(context.body(), UsersGames.class);
            if(checkRights(model, context)) {
	            context.result(objectMapper.writeValueAsString(model));
	            context.status(201);
            } else {
            	context.status(404);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.status(400);
        }
    }
}