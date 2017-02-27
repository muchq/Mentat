package com.muchq.mentat;

import com.muchq.immutables.MoonStyle;
import org.immutables.value.Value.Immutable;

@Immutable
@MoonStyle
public interface MySqlConfigurationIF {
  String getUsername();
  String getPassword();
  String getHost();
  String getDb();
}
