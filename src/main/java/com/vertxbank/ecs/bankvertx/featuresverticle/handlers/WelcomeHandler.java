package com.vertxbank.ecs.bankvertx.featuresverticle.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class WelcomeHandler implements Handler<RoutingContext>{

	@Override
	public void handle(RoutingContext context) {
		// TODO Auto-generated method stub

		context.response().end("Welcome to Bank Vertx");
		
	}
	

}
