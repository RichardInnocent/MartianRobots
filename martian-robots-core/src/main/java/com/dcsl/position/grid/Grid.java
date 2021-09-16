package com.dcsl.position.grid;

import com.dcsl.position.Position;

public interface Grid {

  int getXMin();
  int getXMax();
  int getYMin();
  int getYMax();
  boolean containsPosition(Position position);

}
