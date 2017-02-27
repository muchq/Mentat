package com.muchq.mentat;

import com.google.inject.Provider;
import org.skife.jdbi.v2.DBI;

public class DaoProvider<T> implements Provider<T> {
  private final Provider<DBI> dbiProvider;
  private final Class<T> clazz;

  public DaoProvider(Provider<DBI> dbiProvider, Class<T> clazz) {
    this.dbiProvider = dbiProvider;
    this.clazz = clazz;
  }

  public T get() {
    DBI dbi = dbiProvider.get();
    return clazz.cast(dbi.onDemand(clazz));
  }
}
