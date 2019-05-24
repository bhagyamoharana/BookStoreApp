package controllers.Reccomender;

import controllers.dao.ConnectionHelper;
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
import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Inject;
/**
 * Created by Bhagya .
 */
import play.Play;

import play.mvc.Security;
import play.mvc.Result;
import java.sql.ResultSet;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.common.RandomUtils;
import play.db.*;
import com.google.common.collect.ImmutableMap;
import controllers.dao.ConnectionHelper;


public class ItemBasedRecommend extends Controller {

    @Inject
    ConnectionHelper connhelper;

    Connection connection = null;

    public void doItemBasedRecomendations(String country2, Integer age1, Integer age2) {

        String country = country2;
        Integer ager1 = age1-3;
        Integer ager2 = age2+3;
        Long n[] = new Long[2];
        int i = 0;
        String item="Item";


        connection = connhelper.database();
        System.out.println("Connection created for ItemBasedRecommend Class");

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
                ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(model);
                Recommender itemRecommender = new GenericItemBasedRecommender(model, itemSimilarity);
                List<RecommendedItem> itemRecommendations = itemRecommender.recommend(rs.getInt(1), 2);

                System.out.println("Recommending for user : " + rs.getInt(1));
                for (RecommendedItem itemRecommendation : itemRecommendations) {
                    System.out.println("Item based recommendation for user is : " + itemRecommendation.getItemID());
                    n[i] = itemRecommendation.getItemID();
                    i++;
                }

                System.out.println("n1 value" + n[1]);


                String selectSQL = "INSERT INTO LIVETABLE (bookrating,age,country_code,title,author,coverimage, tags, url)\n" +
                        "SELECT isbn,isbn,title,title, author,coverimage, tags, url FROM booksdata\n" +
                        "WHERE isbn in (" + n[0] + "," + n[1] + ");";

                System.out.println(selectSQL);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.executeUpdate(selectSQL);

            }

                }
        catch(Exception e){
                System.out.println("=============================SQL Update has failed : " + e);
            }
        finally {
            try {
                connection.close();
            }
            catch (Exception e){

            }


        }

}}
          /*            RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {

                public Recommender buildRecommender(DataModel model) throws TasteException {
                   // ItemSimilarity similarity = new EuclideanDistanceSimilarity(model);
                    ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);

                    //Optimizer optimizer = new NonNegativeQuadraticOptimizer();
                    return new GenericItemBasedRecommender(model, similarity);
                }
            };

            // Recommend certain number of items for a particular user
            // Here, recommending 5 items to user_id = 9
            Recommender recommender = recommenderBuilder.buildRecommender(model);
            List<RecommendedItem> recomendations = recommender.recommend(4, 5);
            // recommend (user_id, number_of_items_to_recommend)
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
            System.out.println("code called=======================");
        }catch (IOException e) {
            System.out.println("There was an error.");
            e.printStackTrace();
        }
        catch (TasteException e) {
            System.out.println("There was a Taste Exception.");
            e.printStackTrace();
        }
        return ok(userrecom.render());
}
*/
