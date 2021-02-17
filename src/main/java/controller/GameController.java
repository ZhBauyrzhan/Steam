package controller;

import org.mindrot.jbcrypt.BCrypt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;
import model.Game;
import model.User;
import service.GameService;

public class GameController extends AbstractController<Game>{
	private final GameService service;
	private final ObjectMapper objectMapper;

	public GameController(GameService service, ObjectMapper objectMapper) {
        super(service, objectMapper, Game.class);
        this.objectMapper = objectMapper;
        this.service = service;
	} 
	@Override
	public Boolean checkRights(Game model, Context context) {
		String senderLogin = context.basicAuthCredentials().getUsername();
		String senderPassword= context.basicAuthCredentials().getPassword();
		User user = service.findUserByLogin(senderLogin);
		return user.getRole().equals(User.FIELD_ADMIN) 
				&& (BCrypt.checkpw(senderPassword, user.getPassword()));
	}
	@Override
    public void getOne(Context context, int id) {
        Game model = service.findById(id);
        if (model == null) {
            context.status(404);
        } else {
            try {
            	if(checkRights(model, context)) {
            		context.result(objectMapper.writeValueAsString(model));
            	} else {
            		context.status(404);
            	}
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                context.status(500);
            }
        }
    }

    @Override
    public void post(Context context) {
        try {
            Game model = objectMapper.readValue(context.body(), Game.class);
            if(checkRights(model, context)) {
                service.save(model);
                Game saved = service.findById(model.getId());
                context.result(objectMapper.writeValueAsString(saved));
                context.status(201);
            } else {
            	context.status(404);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.status(400);
        }
    }

    @Override
    public void patch(Context context, int id) {
        try {
            Game model = objectMapper.readValue(context.body(), Game.class);
            if(checkRights(model, context)) {
	            model.setId(id);
	            service.update(model);
	            context.status(200);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.status(400);
        }
    }

    @Override
    public void delete(Context context, int id) {
        Game model = service.findById(id);
        if(checkRights(model, context))
        {
        	service.delete(model);
        	context.status(204);
        }
    }
}