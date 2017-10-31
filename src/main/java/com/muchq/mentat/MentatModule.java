package com.muchq.mentat;

import com.google.inject.Binder;
import com.google.inject.Scopes;
import com.muchq.guice.ReinstallableGuiceModule;
import org.skife.jdbi.v2.DBI;

public class MentatModule extends ReinstallableGuiceModule {
  @Override
  protected void configure() {
    bind(HikariDataSourceProvider.class);
    bind(DBI.class).toProvider(DBIProvider.class).in(Scopes.SINGLETON);
  }

  public MentatModule bindDaos(Binder binder, Class<?>... daos) {
    for (Class<?> dao : daos) {
      bindDao(binder, dao);
    }
    return this;
  }

  public <T> MentatModule bindDao(Binder binder, Class<T> clazz) {
    binder.bind(clazz).toProvider(new DaoProvider<>(binder.getProvider(DBI.class), clazz)).in(Scopes.SINGLETON);
    return this;
  }
}
