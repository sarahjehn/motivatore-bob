package de.jehn.sarah.domain.persistence.postgres;


import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * Created by sarahjehn on 05.03.16.
 */

public class Repository<T> {

    protected final DataSource dataSource;

    public Repository (final DataSource dataSource){
        this.dataSource = dataSource;
    }


}
