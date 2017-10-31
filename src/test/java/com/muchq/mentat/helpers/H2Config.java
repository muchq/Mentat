package com.muchq.mentat.helpers;

import com.muchq.mentat.MentatConfig;

public class H2Config implements MentatConfig {
  @Override
  public String getDataSourceClassName() {
    return "org.h2.Driver";
  }

  @Override
  public String getJdbcUrl() {
    return "jdbc:h2:mem:foo;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1";
  }

  @Override
  public String getUsername() {
    return "";
  }

  @Override
  public String getPassword() {
    return "";
  }
}
