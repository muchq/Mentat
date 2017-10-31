package com.muchq.mentat;

import com.google.common.collect.ImmutableMap;
import com.muchq.immutables.MoonStyle;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

import java.util.Map;

@Immutable
@MoonStyle
public interface MariaConfigurationIF extends MentatConfig {
  String getUsername();
  String getPassword();
  String getHost();
  String getDb();

  @Default
  default int getPort() {
    return 3306;
  }

  @Derived
  @Override
  default String getJdbcUrl() {
    return "jdbc:mariadb://" + getHost() + ":" + getPort() + "/" + getDb();
  }

  @Default
  default Map<String, String> getConnectionProperties() {
    return ImmutableMap.of(
        "cachePrepStmts", "true",
        "prepStmtCacheSize", "250",
        "prepStmtCacheSqlLimit", "2048"
    );
  }

  @Default
  @Override
  default String getDataSourceClassName() {
    return "org.mariadb.jdbc.MariaDbDataSource";
  }
}
