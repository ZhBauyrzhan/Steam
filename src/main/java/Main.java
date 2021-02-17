import configuration.JdbcDataBaseConfiguration;



import controller.UserController;
import controller.UsersGamesController;

import static io.javalin.apibuilder.ApiBuilder.*;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import controller.Controller;
import controller.GameController;
import configuration.DataBaseConfiguration;
import io.javalin.Javalin;
import json.deserializer.GameDeserializer;
import json.deserializer.UserDeserializer;
import json.deserializer.UsersGamesDeserializer;
import json.serializer.GameSerializer;
import json.serializer.UserSerializer;
import json.serializer.UsersGamesSerializer;
import model.Game;
import model.User;
import model.UsersGames;
import service.GameService;
import service.Service;
import service.UserService;
import service.UsersGamesService;

public class Main {	
	public static void main(String[] args) throws SQLException{
		DataBaseConfiguration configuration = new JdbcDataBaseConfiguration("jdbc:sqlite:base.db");
		
		Dao<User, Integer> userDao = DaoManager.createDao(configuration.connectionSource(), User.class);
        Dao<Game, Integer> gameDao = DaoManager.createDao(configuration.connectionSource(), Game.class);
		Dao<UsersGames, Integer> usersGamesDao = DaoManager.createDao(configuration.connectionSource(), UsersGames.class);
        
        Service<User> userService = new UserService(userDao);
        GameService gameService = new GameService(gameDao, userDao);
        UsersGamesService usersGamesService = new UsersGamesService(usersGamesDao, userDao,gameDao);     
        
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.prefer405over404 = true;
            javalinConfig.enableCorsForAllOrigins();
            javalinConfig.enableDevLogging();
        });
        
        SimpleModule simpleModule = new SimpleModule()
        		.addSerializer(User.class, new UserSerializer()).addDeserializer(User.class, new UserDeserializer())
        		.addSerializer(Game.class, new GameSerializer()).addDeserializer(Game.class, new GameDeserializer())
        		.addDeserializer(UsersGames.class, new UsersGamesDeserializer(usersGamesService))
        		.addSerializer(UsersGames.class, new UsersGamesSerializer(usersGamesService));
        ObjectMapper objectMapper = new ObjectMapper().registerModule(simpleModule);
        
        Controller<User> userController = new UserController(userService, objectMapper);
        Controller<Game> gameController = new GameController(gameService, objectMapper);
        Controller<UsersGames> usersGamesController = new UsersGamesController(usersGamesService, objectMapper);
        app.routes( () -> {
        	path("user", () -> {
                post(userController::post);
                path(":id", () -> {
                    get(ctx -> userController.getOne(ctx, ctx.pathParam("id", Integer.class).get()));
                    patch(ctx -> userController.patch(ctx, ctx.pathParam("id", Integer.class).get()));
                    delete(ctx -> userController.delete(ctx, ctx.pathParam("id", Integer.class).get()));
                });
            });
        	path("game", () -> {
                post(gameController::post);
                path(":id", () -> {
                    get(ctx -> gameController.getOne(ctx, ctx.pathParam("id", Integer.class).get()));
                    patch(ctx -> gameController.patch(ctx, ctx.pathParam("id", Integer.class).get()));
                    delete(ctx -> gameController.delete(ctx, ctx.pathParam("id", Integer.class).get()));
                });
            });
        	path("usersGames", () -> {
                post(usersGamesController::post);
                path(":id", () -> {
                
                    get(ctx -> usersGamesController.getOne(ctx, ctx.pathParam("id", Integer.class).get()));
                    patch(ctx -> usersGamesController.patch(ctx, ctx.pathParam("id", Integer.class).get()));
                    delete(ctx -> usersGamesController.delete(ctx, ctx.pathParam("id", Integer.class).get()));
                });
            });
        });
        app.start(7777); 
    }
}
