package com.vertxbank.ecs.bankvertx.featuresverticle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpServerResponse;

public class GetAccountsFromFileVerticle extends AbstractVerticle{

	private final Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	
	public void start(Future<Void> startFuture) throws Exception {
		vertx.eventBus().consumer(GetAccountsFromFileVerticle.class.getSimpleName(),message ->{
			System.out.println(message.body() + " at " + LocalDateTime.now());
			String accountString = null;
			try {
				accountString = readFile("src/main/java/data/accountholders.json",StandardCharsets.UTF_8);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			accountString.replace("\n", "");
			message.reply(accountString);
		});
	}
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	

}
