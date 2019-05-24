package controllers.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import play.mvc.Controller;
import play.db.NamedDatabase;
import play.db.Database;

// inject "orders" database instead of "default"
@javax.inject.Singleton
public class JavaNamedDatabase {
    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public JavaNamedDatabase(
            @NamedDatabase("playdb") Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;

    }

    // do whatever you need with the db using supplyAsync(() -> { ... }, executionContext);
}