package controller;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;
import model.User;
import service.AbstractService;
import service.Service;

public class UserController extends AbstractController<User>{
	 private final AbstractService<User> service;
	 private final ObjectMapper objectMapper;

	public UserController(AbstractService<User> service, ObjectMapper objectMapper) {
        super(service, objectMapper, User.class);
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
	    User model = service.findById(id);
        if (model == null) {
            context.status(404);
        } else {
            try {
            	if(checkRights(context))
            		context.result(objectMapper.writeValueAsString(model));
            	else 
            		context.status(404);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                context.status(500);
            }
        }
    }

    @Override
    public void patch(Context context, int id) {
        try {
            User model = objectMapper.readValue(context.body(), User.class);
            if(checkRights(context)) {
            	model.setId(id);
                service.update(model);
                context.status(200);
            } else {
            	context.status(404);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.status(400);
        }
    }

    @Override
    public void delete(Context context, int id) {
        User model = service.findById(id);
        if(checkRights(context)) {
        	service.delete(model);
        	context.status(204);
        } else {
        	context.status(404);
        }
    }
}