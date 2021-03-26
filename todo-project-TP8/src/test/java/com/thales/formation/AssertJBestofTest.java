package com.thales.formation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.byLessThan;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class AssertJBestofTest {

  @Test
  void iterable_basic_assertions_examples() {

    List<String> abc = Arrays.asList("a", "b", "c");
    assertThat(abc).hasSize(3)
        .containsAnyOf("b")
        .containsAnyOf("b", "c")
        .containsAnyOf("a", "b", "c")
        .containsAnyOf("a", "b", "c", "d");
  }

  @Test
  void iterable_basic_objects_assertions_examples() {

    Person formateur = new Person("formateur");
    Person eleve = new Person("eleve");

    List<Person> abc = Arrays.asList(formateur,
        eleve);

    assertThat(abc).hasSize(2)
        .contains(formateur);

    assertThat(abc)
        .filteredOn(person -> person.getName().startsWith("form"))
        .contains(formateur);

    assertThat(abc).extracting(Person::getName).contains("formateur", "eleve");
  }

  @Test
  void basciException() {

    assertThatThrownBy(() -> serviceThatCanRaiseAnException()).isInstanceOf(RuntimeException.class).hasMessage("oups");
  }

  @Test
  void big_decimals_assertions_examples() {

    // You can use String directly and we will create the corresponding BigDecimal for you, thus ...
    assertThat(new BigDecimal("8.0")).isEqualTo("8.0");
    // ... is equivalent to :
    assertThat(new BigDecimal("8.0")).isEqualTo(new BigDecimal("8.0"));

    // With BigDecimal, 8.0 is not equals to 8.00 but it is if you use compareTo()
    assertThat(new BigDecimal("8.0")).isEqualByComparingTo(new BigDecimal("8.00"));
    assertThat(new BigDecimal("8.0")).isEqualByComparingTo("8.00");
    assertThat(new BigDecimal("8.0")).isNotEqualByComparingTo("8.01");

    // isGreaterThanOrEqualTo uses compareTo semantics
    assertThat(new BigDecimal("8.0")).isGreaterThanOrEqualTo(new BigDecimal("8.00"));
    assertThat(new BigDecimal("8.1")).isGreaterThanOrEqualTo(new BigDecimal("8.10"));
  }

  @Test
  void instant_assertions_examples() {
    Instant firstOfJanuary2000 = Instant.parse("2000-01-01T00:00:00.00Z");

    assertThat(firstOfJanuary2000).isEqualTo("2000-01-01T00:00:00.00Z")
        .isAfter("1999-12-31T23:59:59.99Z")
        .isAfter(firstOfJanuary2000.minusSeconds(1))
        .isAfterOrEqualTo("2000-01-01T00:00:00.00Z")
        .isBefore(firstOfJanuary2000.plusSeconds(1))
        .isBefore("2000-01-01T00:00:00.01Z")
        .isBetween(firstOfJanuary2000.minusSeconds(1), firstOfJanuary2000.plusSeconds(1))
        .isCloseTo("1999-12-31T23:59:59.99Z", within(10, ChronoUnit.MILLIS))
        .isCloseTo("1999-12-31T23:59:59.99Z", byLessThan(11, ChronoUnit.MILLIS))
        .isBetween("1999-01-01T00:00:00.00Z", "2001-01-01T00:00:00.00Z")
        .isBetween("2000-01-01T00:00:00.00Z", "2001-01-01T00:00:00.00Z")
        .isBetween("1999-01-01T00:00:00.00Z", "2000-01-01T00:00:00.00Z")
        .isBetween("2000-01-01T00:00:00.00Z", "2000-01-01T00:00:00.00Z")
        .isStrictlyBetween("1999-01-01T00:00:00.00Z", "2001-01-01T00:00:00.00Z");
    try {
      assertThat(firstOfJanuary2000).isCloseTo("1999-12-31T23:59:59.99Z", within(1, ChronoUnit.MILLIS));
    } catch (AssertionError e) {
      //      logAssertionErrorMessage("Instant.isCloseTo", e);
    }

    Instant instant = Instant.now();
    assertThat(instant).isBetween(instant.minusSeconds(1), instant.plusSeconds(1))
        .isBetween(instant, instant.plusSeconds(1))
        .isBetween(instant.minusSeconds(1), instant)
        .isBetween(instant, instant)
        .isStrictlyBetween(instant.minusSeconds(1), instant.plusSeconds(1));
  }

  private void serviceThatCanRaiseAnException() {
    throw new RuntimeException("oups");
  }

  public class Person {

    private String name;

    public Person(String name) {
      super();
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getEnclosingInstance().hashCode();
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Person other = (Person) obj;
      if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
        return false;
      if (name == null) {
        if (other.name != null)
          return false;
      } else if (!name.equals(other.name))
        return false;
      return true;
    }

    private AssertJBestofTest getEnclosingInstance() {
      return AssertJBestofTest.this;
    }

  }
}
