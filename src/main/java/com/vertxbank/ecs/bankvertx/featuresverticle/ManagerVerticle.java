package com.vertxbank.ecs.bankvertx.featuresverticle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.vertxbank.ecs.bankvertx.featuresverticle.handlers.WelcomeHandler;
import com.vertxbank.ecs.bankvertx.featuresverticle.handlers.getAccountByIDHandler;
import com.vertxbank.ecs.bankvertx.featuresverticle.handlers.getAccountsHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import io.vertx.ext.web.Router;

public class ManagerVerticle extends AbstractVerticle{
	
	private Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	String port = null;
	@Override
	public void start(Future<Void> future) {
		
		Properties prop = new Properties();

		InputStream input;
		
		try {
			input = new FileInputStream("src/main/java/config/config.properties");
			prop.load(input);
			port = prop.getProperty("port");
			System.out.println(port);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Router router = Router.router(vertx);
		String accounts = prop.getProperty("getaccounts");
		router.route(accounts+"/:id").handler(new getAccountByIDHandler());
		router.route(accounts).handler(new getAccountsHandler());
		router.route().handler(new WelcomeHandler());
		

		
		vertx.createHttpServer().requestHandler(router).listen(Integer.parseInt(port), clientRequest -> {
			if (clientRequest.succeeded()) {
				logger.info("Successfully started Server verticle: " +port);
				future.complete();
			} else {
				logger.warning("Failed to start Server verticle");
				future.fail(clientRequest.cause());
			}
		});
		
	}

}
