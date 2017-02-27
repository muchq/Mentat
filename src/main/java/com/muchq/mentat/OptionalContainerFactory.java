package com.muchq.mentat;

import org.skife.jdbi.v2.ContainerBuilder;
import org.skife.jdbi.v2.tweak.ContainerFactory;

import java.util.Optional;

public class OptionalContainerFactory implements ContainerFactory<Optional<?>> {

  @Override
  public boolean accepts(Class<?> type) {
    return Optional.class.isAssignableFrom(type);
  }

  @Override
  public ContainerBuilder<Optional<?>> newContainerBuilderFor(Class<?> type) {
    return new OptionalContainerBuilder();
  }
}
