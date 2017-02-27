package com.muchq.mentat;

import com.google.inject.Scopes;
import com.muchq.guice.ReinstallableGuiceModule;
import org.skife.jdbi.v2.DBI;

public class MentatModule extends ReinstallableGuiceModule {
  @Override
  protected void configure() {
    bind(DBI.class).toProvider(DBIProvider.class).in(Scopes.SINGLETON);
  }

  public void bindDaos(Class<?>... daos) {
    for (Class<?> dao : daos) {
      bindDao(dao);
    }
  }

  public <T> void bindDao(Class<T> clazz) {
    bind(clazz).toProvider(new DaoProvider<>(getProvider(DBI.class), clazz)).in(Scopes.SINGLETON);
  }
}
