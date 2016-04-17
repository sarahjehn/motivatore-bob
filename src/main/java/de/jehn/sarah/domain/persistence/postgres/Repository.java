package de.jehn.sarah.domain.persistence.postgres;

import org.springframework.jdbc.core.metadata.PostgresTableMetaDataProvider;

import javax.sql.DataSource;

/**
 * Created by sarahjehn on 05.03.16.
 */
public class Repository<T> {

    protected final DataSource dataSource;

    public Repository (final DataSource dataSource){
        this.dataSource = dataSource;
    }
}
