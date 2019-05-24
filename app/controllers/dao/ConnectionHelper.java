package controllers.dao;

import models.Booksdata;
import models.NewLoggersReccomendTable;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.db.*;
import com.google.common.collect.ImmutableMap;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import views.html.bookdataset.*;
import views.html.errors.*;
import views.html.recommendation.userrecom;
import java.sql.DriverManager;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import play.db.*;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import javax.inject.Inject;

public class ConnectionHelper {
    Connection connection = null;
    public Connection database() {
        try {
            Database database = Databases.createFrom(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost/playdb",
                    ImmutableMap.of(
                            "username", "root",
                            "password", "admin"
                    )
            );
            connection = database.getConnection();
            return connection;
        } catch (Exception e) {

        }
return connection;
    }
}