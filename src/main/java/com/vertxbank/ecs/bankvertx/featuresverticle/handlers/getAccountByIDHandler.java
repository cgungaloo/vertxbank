package com.vertxbank.ecs.bankvertx.featuresverticle.handlers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.vertxbank.ecs.bankvertx.featuresverticle.GetAccountsFromFileVerticle;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import pojos.Account;

public class getAccountByIDHandler implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext context) {
		// TODO Auto-generated method stub
		String id =context.request().getParam("id");
		System.out.println("id is : "+ id);
		Vertx vertx = Vertx.currentContext().owner();
		String message ="Get account order for id";
		
		vertx.eventBus().send(GetAccountsFromFileVerticle.class.getSimpleName(), message, reply->{
			if (reply.succeeded()) {
		          System.out.println("Success event ");
		          String accountsStr =reply.result().body().toString();
//		          System.out.println(accountsStr);
		          
		          Gson gson = new Gson();
		          TypeToken<List<Account>> token = new TypeToken<List<Account>>() {};
		          List<Account> accounts = gson.fromJson(accountsStr, token.getType());
		          
		          try {
		        	  List<String> filtered = new ArrayList<>();
		        	  JsonElement element = new JsonParser().parse(accountsStr);
//		        	  JsonElement element = gson.toJsonTree(accounts, new TypeToken<List<Account>>() {}.getType());
		        	  JsonArray jsonArray = element.getAsJsonArray();
		        	  Account acc = new Account();
		        	  for(JsonElement e : jsonArray) {
		        		  JsonObject j = e.getAsJsonObject();
		        		  String j2m =j.get("id").getAsString();

		        		  System.out.println(j2m);
		        		  if(j2m.equals(id) == true) {
		        			  acc.setId(j.get("id").getAsString());
		        			  acc.setName(j.get("name").getAsString());
		        			  acc.setBalance(j.get("Balance").getAsString());
		        		  }
		        	  }
		        	  context.response().end(gson.toJson(acc));
		          }catch(Exception e) {
		        	  System.out.println(e);
		          }

		      }
		});
	}

}
