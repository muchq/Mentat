package com.muchq.mentat;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.muchq.mentat.helpers.Foo;
import com.muchq.mentat.helpers.FooPost;
import com.muchq.mentat.helpers.H2Config;
import com.muchq.mentat.helpers.TestFooDao;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class MentatConnectionTest {
  private TestFooDao fooDao;

  @Before
  public void setup() {
    Injector injector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
        install(new MentatModule().bindDao(binder(), TestFooDao.class));
        bind(DataSource.class).toProvider(HikariDataSourceProvider.class);
      }

      @Provides
      public MentatConfig getConfig() {
        return new H2Config();
      }
    });

    DBI dbi = injector.getInstance(DBI.class);
    Handle h = dbi.open();
    h.execute("create table foo (id int auto_increment, name varchar(100))");
    h.close();

    fooDao = injector.getInstance(TestFooDao.class);
  }

  @Test
  public void itWorks() throws SQLException {
    FooPost fooPost = new FooPost(UUID.randomUUID().toString());
    int fooId = fooDao.createFoo(fooPost);
    Optional<Foo> fooMaybe = fooDao.getFooById(fooId);

    assertThat(fooMaybe).isPresent();
    assertThat(fooMaybe.get()).isEqualTo(new Foo(fooId, fooPost.getName()));
  }
}
