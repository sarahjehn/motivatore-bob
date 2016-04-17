package de.jehn.sarah.domain.persistence.postgres;

import de.jehn.sarah.domain.model.Goal;
import de.jehn.sarah.domain.persistence.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sarahjehn on 05.03.16.
 */

@org.springframework.stereotype.Repository
public class PostgresGoalRepository extends Repository implements GoalRepository {

    private final Logger l = Logger.getLogger(PostgresGoalRepository.class.getName());

    private final String SAVE_GOAL_SQL = "INSERT INTO goal (uuid, name, \"userid\", created_at, week, done) VALUES (?,?,?,?,?,?)";

    private final String RETRIEVE_RECENT_GOALS = "SELECT name FROM goal WHERE week = ? AND " +
            "\"userid\" = ? ORDER BY created_at ASC";

    private final String RETRIEVE_RECENT_REMAINING_GOALS = "SELECT uuid, name, \"userid\", created_at, week, done FROM goal WHERE week = ? AND " +
            "\"userid\" = ? AND done = FALSE ORDER BY created_at ASC";

    private final String GOAL_COUNT_RECENT_WEEK = "SELECT COUNT(1) FROM goal WHERE week = ? AND \"userid\" = ?";

    private final String GOAL_ID = "SELECT uuid FROM goal WHERE created_at = ? AND \"userid\" = ?";

    private final String MARK_AS_DONE = "UPDATE goal SET done = ? WHERE uuid = ? AND \"userid\" = ?";

    private final String GOALS_DONE = "SELECT name FROM goal WHERE week = ? AND " +
            "\"userid\" = ? AND done_at = null ORDER BY created_at ASC";

    private final String GOALS_REMAINING = "SELECT name FROM goal WHERE week = ? AND " +
            "\"userid\" = ? AND done_at != null ORDER BY created_at ASC";

    @Autowired
    public PostgresGoalRepository(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public void saveGoal(Goal goal) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_GOAL_SQL)) {
            statement.setObject(1, goal.getUuid());
            statement.setString(2, goal.getName());
            statement.setString(3, goal.getUser());
            statement.setString(4, goal.getCreatedAt());
            statement.setInt(5, goal.getGoalWeek());
            statement.setBoolean(6, goal.getDone());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getGoals(LocalDateTime date, String user) {

        int goalWeek = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("ww")));
        ResultSet sqlResult = null;
        List<String> result = new LinkedList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(RETRIEVE_RECENT_GOALS)) {
            statement.setInt(1, goalWeek);
            statement.setString(2, user);
            sqlResult = statement.executeQuery();
            while(sqlResult.next()){
                result.add(sqlResult.getString(1));
                l.log(Level.INFO, "das ist ein Result: " + sqlResult.getString(1));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Goal> getRemainingGoals(LocalDateTime date, String user) {

        int goalWeek = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("ww")));

        l.log(Level.INFO, "This is the week it is looked for: " + goalWeek + " and the user: " + user);

        ResultSet sqlResult = null;
        List<Goal> result = new LinkedList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(RETRIEVE_RECENT_REMAINING_GOALS)) {
            statement.setInt(1, goalWeek);
            statement.setString(2, user);
            sqlResult = statement.executeQuery();
            while(sqlResult.next()) {
                result.add(new Goal((UUID) sqlResult.getObject(1),
                        sqlResult.getString(2),
                        sqlResult.getString(3),
                        sqlResult.getString(4),
                        sqlResult.getInt(5),
                        sqlResult.getBoolean(6)));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int getGoalCountOfCurrentWeek(LocalDateTime date, String user) {
        int goalWeek = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("ww")));
        ResultSet sqlResult = null;
        int result = 0;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GOAL_COUNT_RECENT_WEEK)) {
            statement.setInt(1, goalWeek);
            statement.setString(2, user);
            sqlResult = statement.executeQuery();
            while(sqlResult.next()){
                result = sqlResult.getInt(1);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;    }

    @Override
    public Goal getGoal(String timestamp, String user) {
        ResultSet sqlResult = null;
        Goal goal = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GOAL_ID)) {
            statement.setString(1, timestamp);
            statement.setString(2, user);
            sqlResult = statement.executeQuery();
            while(sqlResult.next()){
                goal = new Goal ((UUID)sqlResult.getObject(1),
                sqlResult.getString(2),
                sqlResult.getString(3),
                sqlResult.getString(4),
                sqlResult.getInt(5),
                sqlResult.getBoolean(6));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goal;    }

    @Override
    public void markAsDone(UUID uuid, String user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(MARK_AS_DONE)) {
            statement.setObject(2, uuid);
            statement.setString(3, user);
            statement.setBoolean(1, true);

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

