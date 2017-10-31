package com.muchq.mentat;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public interface MentatConfig {
  String getDataSourceClassName();
  String getJdbcUrl();
  String getUsername();
  String getPassword();

  default Map<String, String> getExtraProperties() {
    return ImmutableMap.of();
  }

  default int getMaxPoolSize() {
    return 20;
  }
}
