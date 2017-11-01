package com.muchq.mentat.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hubspot.rosetta.annotations.RosettaCreator;

import java.util.Objects;

public class Foo {
  private final int id;
  private final String name;

  @RosettaCreator
  public Foo(@JsonProperty("id") int id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    Foo that = (Foo) other;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }
}
