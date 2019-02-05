package com.vertxbank.ecs.bankvertx;

import com.vertxbank.ecs.bankvertx.featuresverticle.GetAccountsFromFileVerticle;
import com.vertxbank.ecs.bankvertx.featuresverticle.ManagerVerticle;

import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting Vertx Bank" );
        
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ManagerVerticle());
        vertx.deployVerticle(new GetAccountsFromFileVerticle());
        
    }
}
