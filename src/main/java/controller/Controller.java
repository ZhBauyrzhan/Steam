package controller;

import io.javalin.http.Context;
import model.Model;

public interface Controller<T extends Model>{
    void getAll(Context context);
    void getOne(Context context, int id);
    void post(Context context);
    void patch(Context context, int id);
    void delete(Context context, int id);
    Boolean checkRights(T model, Context context);
}