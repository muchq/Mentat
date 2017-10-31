package com.muchq.mentat;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

public class DBIProvider implements Provider<DBI> {
  private final DataSource dataSource;

  @Inject
  public DBIProvider(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public DBI get() {
    DBI dbi = new DBI(dataSource);
    dbi.registerMapper(new RosettaMapperFactory());
    dbi.registerContainerFactory(new OptionalContainerFactory());
    return dbi;
  }
}
