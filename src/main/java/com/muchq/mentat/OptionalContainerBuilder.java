package com.muchq.mentat;

import org.skife.jdbi.v2.ContainerBuilder;

import java.util.Optional;

class OptionalContainerBuilder implements ContainerBuilder<Optional<?>> {
  private Optional<?> optional = Optional.empty();

  @Override
  public ContainerBuilder<Optional<?>> add(Object it) {
    optional = Optional.ofNullable(it);
    return this;
  }

  @Override
  public Optional<?> build() {
    return optional;
  }
}
