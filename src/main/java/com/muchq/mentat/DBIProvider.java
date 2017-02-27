package com.muchq.mentat;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.skife.jdbi.v2.DBI;

public class DBIProvider implements Provider<DBI> {
  private final MySqlConfiguration configuration;

  @Inject
  public DBIProvider(MySqlConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public DBI get() {
    HikariConfig config = new HikariConfig("/hikari.properties");
    config.setUsername(configuration.getUsername());
    config.setPassword(configuration.getPassword());
    config.addDataSourceProperty("databaseName", configuration.getDb());
    config.addDataSourceProperty("serverName", configuration.getHost());

    DBI dbi = new DBI(new HikariDataSource(config));
    dbi.registerMapper(new RosettaMapperFactory());
    dbi.registerContainerFactory(new OptionalContainerFactory());
    return dbi;
  }
}
