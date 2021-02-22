package controller;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;
import model.User;
import model.UsersGames;
import service.UsersGamesService;

import java.util.ArrayList;
import java.util.List;

public class UsersGamesController extends AbstractController<UsersGames>{
	private final UsersGamesService service;
	private final ObjectMapper objectMapper;
	public UsersGamesController(UsersGamesService service, ObjectMapper objectMapper) {
        super(service, objectMapper, UsersGames.class);
        this.service = service;
        this.objectMapper = objectMapper;
	}

	@Override
	public void getAll(Context context, int pageNumber, int pageSize) {
		try {
			if(checkRights(context)) {
				super.getAll(context, pageNumber, pageSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.status(500);
		}
	}
	@Override
	public void getOne(Context context, int id) {
		UsersGames model = service.findById(id);
		if(checkRights(context)) {
			super.getOne(context, id);
		} else {
			context.status(403);
		}
	}
	@Override
    public void delete(Context context, int id) {
        UsersGames model = service.findById(id);
        if(checkRights(context)) {
        	service.delete(model);
        	context.status(204);
        }
    }
	@Override
    public void post(Context context) {
        try {
            UsersGames model = objectMapper.readValue(context.body(), UsersGames.class);
            if(checkRights(context)) {
				service.save(model);
				UsersGames saved = service.findById(model.getId());
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
}