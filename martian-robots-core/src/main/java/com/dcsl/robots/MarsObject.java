package com.dcsl.robots;

import com.dcsl.position.Position;

/**
 * An object on Mars.
 */
public interface MarsObject {

  /**
   * Gets the object's current position.
   * @return The object's position.
   */
  Position getPosition();

}
