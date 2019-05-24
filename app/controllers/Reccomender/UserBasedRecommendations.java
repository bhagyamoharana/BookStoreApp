package controllers.Reccomender;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import play.mvc.Controller;
import views.html.recommendation.userrecom;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Hashtable;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.google.common.collect.ImmutableMap;
import controllers.dao.ConnectionHelper;
/**
 * Created by Bhagya .
 */
import play.Play;

import play.mvc.Security;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
import play.mvc.Result;
import javax.inject.Inject;

import java.io.File;
import java.util.List;
import java.io.File;
import java.util.List;
import java.sql.ResultSet;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;


public class UserBasedRecommendations extends Controller {

    @Inject
    ConnectionHelper connhelper;

    Connection connection = null;

    public void doUserBasedRecomendations(String country2, Integer age1, Integer age2) {

        String country = country2;
        Integer ager1 = age1 - 3;
        Integer ager2 = age2 + 3;
        Long n[] = new Long[2];
        int i = 0;

        connection = connhelper.database();
        System.out.println("Connection created for UserBasedRecommend Class");

        try {

            String selectSQL2 = "select user_id from playdb.userprofile where " +
                    "age between " + ager1 + " and " + ager2 + " and " +
                    "country_code = '" + country + "';";

            System.out.println(selectSQL2);

            PreparedStatement ps2 = connection.prepareStatement(selectSQL2);

            ResultSet rs = ps2.executeQuery(selectSQL2);

            while (rs.next()) {

                RandomUtils.useTestSeed(); // to randomize the evaluation result

                DataModel model = new FileDataModel(new File("app/assets/data/ratings.csv"));
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

                UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);

                Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);


                    List<RecommendedItem> recommendations = recommender.recommend(rs.getInt(1), 2);
                    System.out.println("Recommending for user : " + rs.getInt(1));
                    for (RecommendedItem recommendation : recommendations) {
                        System.out.println("isbn recommendation based on User based recommendation is :" + recommendation.getItemID());
                        n[i] = recommendation.getItemID();
                        i++;
                    }

                String selectSQL = "INSERT INTO LIVETABLE (bookrating,age,country_code,title,author,coverimage, tags, url)\n" +
                        "SELECT isbn,isbn,isbn,title, author,coverimage, tags, url FROM booksdata\n" +
                        "WHERE isbn in (" + n[0] + "," + n[1] + ");";

                System.out.println(selectSQL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.executeUpdate(selectSQL);

                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }

    }
}

/*
      try{
            RandomUtils.useTestSeed(); // to randomize the evaluation result
           // DataModel model = new FileDataModel(new File("app/assets/data/Location2.csv"));
            DataModel model = new FileDataModel(new File("app/assets/data/dataset-recsys.csv"));

            System.out.println("One");

            RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
                public Recommender buildRecommender(DataModel model) throws TasteException {
                    System.out.println("two");
                    UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
                    //SpearmanCorrelationSimilarity similarity = new SpearmanCorrelationSimilarity(model);

                    // neighborhood size = 100
                    UserNeighborhood neighborhood = new NearestNUserNeighborhood (100, similarity, model);
                    return new GenericUserBasedRecommender(model, neighborhood, similarity);
                }
            };
            System.out.println("three");
            // Recommend certain number of items for a particular user
            // Here, recommending 5 items to user_id = 9
            Recommender recommender = recommenderBuilder.buildRecommender(model);
            List<RecommendedItem> recomendations = recommender.recommend(9, 5);
            for (RecommendedItem recommendedItem : recomendations) {
                System.out.println(recommendedItem);
            }

            RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
            double score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
            System.out.println("RMSE: " + score);

            RecommenderIRStatsEvaluator statsEvaluator = new GenericRecommenderIRStatsEvaluator();
            IRStatistics stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 4, 0.7); // evaluate precision recall at 10

            System.out.println("Precision: " + stats.getPrecision());
            System.out.println("Recall: " + stats.getRecall());
            System.out.println("F1 Score: " + stats.getF1Measure());


        }catch (Exception e){
            e.printStackTrace();
        }
        return ok(userrecom.render());
    }
 */