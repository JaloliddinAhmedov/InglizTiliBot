package db;

import model.QuizModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectingDB {
    static List<QuizModel> quizes;
    static Connection c = null;
    static Statement stmt = null;

    public static List<QuizModel> connecting() {
        quizes = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "4321");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "SELECT * FROM SAVOLLAR";/* +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";*/
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String savol = rs.getString("savol");
                String javob1 = rs.getString("javob1");
                String javob2 = rs.getString("javob2");
                String javob3 = rs.getString("javob3");
                String javob4 = rs.getString("javob4");
                int topic_id = rs.getInt("numoftopic");
                int togri_id = rs.getInt("togrijavob");
                QuizModel quiz = new QuizModel(id, savol, javob1, javob2, javob3, javob4, topic_id, togri_id);
                quizes.add(quiz);
            }

            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        System.out.println("Table created successfully");
        return quizes;
    }

    static void inserting(){

    }
}
