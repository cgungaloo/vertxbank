package com.vertxbank.ecs.bankvertx.featuresverticle.handlers;

import com.vertxbank.ecs.bankvertx.featuresverticle.GetAccountsFromFileVerticle;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class getAccountsHandler implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext context) {
		Vertx vertx = Vertx.currentContext().owner();
		// TODO Auto-generated method stub
		String message ="Get account order";
		
		vertx.eventBus().send(GetAccountsFromFileVerticle.class.getSimpleName(), message, reply->{
			if (reply.succeeded()) {
		          System.out.println("Success event ");
		          context.response().end(reply.result().body().toString());

		      } else {
		    	  System.out.println("Unsuccessful event "+ message);
		      }
		});
		
	}

}
