package com.muchq.mentat.helpers;

import com.hubspot.rosetta.jdbi.BindWithRosetta;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import java.util.Optional;

public interface TestFooDao {
  @SingleValueResult
  @SqlQuery("SELECT id, name FROM foo WHERE id = :id")
  Optional<Foo> getFooById(@Bind("id") long id);

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO foo SET name = :name")
  int createFoo(@BindWithRosetta FooPost fooPost);
}
