package com.muchq.mentat;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Map.Entry;

public class HikariDataSourceProvider implements Provider<DataSource> {
  private final MentatConfig mentatConfig;

  @Inject
  public HikariDataSourceProvider(MentatConfig mentatConfig) {
    this.mentatConfig = mentatConfig;
  }

  @Override
  public DataSource get() {
    final HikariDataSource ds = new HikariDataSource();
    ds.setMaximumPoolSize(mentatConfig.getMaxPoolSize());
    ds.setDriverClassName(mentatConfig.getDataSourceClassName());
    ds.setJdbcUrl(mentatConfig.getJdbcUrl());
    ds.addDataSourceProperty("user", mentatConfig.getUsername());
    ds.addDataSourceProperty("password", mentatConfig.getPassword());
    for (Entry<String, String> entry : mentatConfig.getExtraProperties().entrySet()) {
      ds.addDataSourceProperty(entry.getKey(), entry.getValue());
    }
    return ds;
  }
}
