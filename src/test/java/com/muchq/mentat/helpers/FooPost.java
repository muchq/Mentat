package com.muchq.mentat.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FooPost {
  private final String name;

  @JsonCreator
  public FooPost(@JsonProperty("name") String name) {
    this.name = name;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    FooPost that = (FooPost) other;
    return Objects.equals(name, that.name);
  }
}
