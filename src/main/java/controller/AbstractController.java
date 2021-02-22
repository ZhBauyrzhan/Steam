package controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;
import model.Model;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import service.AbstractService;
import service.Service;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController<T extends Model> implements Controller<T> {
    private final AbstractService<T> service;
    private final ObjectMapper objectMapper;
    private final Class<T> clazz;

    public AbstractController(AbstractService<T> service, ObjectMapper objectMapper, Class<T> clazz) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.clazz = clazz;
    }
    public Boolean checkRights(Context context) {
        String senderLogin = context.basicAuthCredentials().getUsername();
        String senderPassword= context.basicAuthCredentials().getPassword();
        User user = service.findUserByLogin(senderLogin);
        System.out.println();
        return ((BCrypt.checkpw(senderPassword, user.getPassword()))
                || user.getRole().equals(User.FIELD_ADMIN));
    }
    @Override
    public void getAll(Context context, int pageNumber, int pageSize) {
        try {
            List<T> returnedModels = service.findAll(pageNumber, pageSize);
            context.result(objectMapper.writeValueAsString(returnedModels));
        } catch (Exception e) {
            e.printStackTrace();
            context.status(500);
        }
    }

    @Override
    public void getOne(Context context, int id) {
        T model = service.findById(id);
        if (model == null) {
            context.status(404);
        } else {
            try {
                context.result(objectMapper.writeValueAsString(model));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                context.status(500);
            }
        }
    }

    @Override
    public void post(Context context) {
        try {
            T model = objectMapper.readValue(context.body(), clazz);
            System.out.println(model);
            service.save(model);
            T saved = service.findById(model.getId());
            context.result(objectMapper.writeValueAsString(saved));
            context.status(201);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.status(400);
        }
    }

    @Override
    public void patch(Context context, int id) {
        try {
            T model = objectMapper.readValue(context.body(), clazz);
            model.setId(id);
            service.update(model);
            context.status(200);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.status(400);
        }
    }

    @Override
    public void delete(Context context, int id) {
        T model = service.findById(id);
        service.delete(model);
        context.status(204);
    }
}