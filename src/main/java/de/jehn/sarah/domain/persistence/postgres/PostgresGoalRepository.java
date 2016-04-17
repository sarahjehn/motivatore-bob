package de.jehn.sarah.domain.persistence.postgres;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * Created by sarahjehn on 05.03.16.
 */


public class PostgresGoalRepository extends Repository {


    @Autowired
    public PostgresGoalRepository(DataSource dataSource){
        super(dataSource);
    }

}
