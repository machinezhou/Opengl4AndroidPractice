package com.zhou.lawson.openglpractice.data.objects;

import com.zhou.lawson.openglpractice.data.Geometry;
import com.zhou.lawson.openglpractice.data.VertexArray;
import com.zhou.lawson.openglpractice.data.programs.ColorShaderProgram;
import java.util.List;

/**
 * Created by lawson on 16/12/5.
 */

public class Mallet {
  private static final int POSITION_COMPONENT_COUNT = 3;

  public final float radius;
  public final float height;

  private final VertexArray vertexArray;
  private final List<ObjectBuilder.DrawCommand> drawList;

  public Mallet(float radius, float height, int numPointsAroundMallet) {
    ObjectBuilder.GeneratedData generatedData =
        ObjectBuilder.createMallet(new Geometry.Point(0f, 0f, 0f), radius, height,
            numPointsAroundMallet);

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
