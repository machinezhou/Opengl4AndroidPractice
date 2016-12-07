package com.zhou.lawson.openglpractice.data.objects;

import com.zhou.lawson.openglpractice.data.Geometry;
import com.zhou.lawson.openglpractice.data.VertexArray;
import com.zhou.lawson.openglpractice.data.programs.ColorShaderProgram;
import java.util.List;

/**
 * Created by lawson on 16/12/7.
 */

public class Puck {
  private static final int POSITION_COMPONENT_COUNT = 3;

  public final float radius, height;

  private final VertexArray vertexArray;
  private final List<ObjectBuilder.DrawCommand> drawList;

  public Puck(float radius, float height, int numPointsAroundPuck) {
    ObjectBuilder.GeneratedData generatedData = ObjectBuilder.createPuck(
        new Geometry.Cylinder(new Geometry.Point(0f, 0f, 0f), radius, height), numPointsAroundPuck);
    this.radius = radius;
    this.height = height;

    vertexArray = new VertexArray(generatedData.vertexData);
    drawList = generatedData.drawList;
  }

  public void bindData(ColorShaderProgram colorProgram) {
    vertexArray.setVertexAttribPointer(0, colorProgram.getPositionAttributeLocation(),
        POSITION_COMPONENT_COUNT, 0);
  }

  public void draw() {
    for (ObjectBuilder.DrawCommand drawCommand : drawList) {
      drawCommand.draw();
    }
  }
}
