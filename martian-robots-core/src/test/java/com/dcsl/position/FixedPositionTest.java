package com.dcsl.position;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

class FixedPositionTest {

  @Test
  public void equalsAndHashCode_Always_Valid() {
    EqualsVerifier.forClass(FixedPosition.class).suppress(Warning.STRICT_INHERITANCE).verify();
  }

}