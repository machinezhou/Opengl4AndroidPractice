package com.zhou.lawson.openglpractice.renders;

import android.content.Context;
import android.opengl.GLSurfaceView;
import com.zhou.lawson.openglpractice.R;
import com.zhou.lawson.openglpractice.data.objects.Mallet;
import com.zhou.lawson.openglpractice.data.objects.Table;
import com.zhou.lawson.openglpractice.data.programs.ColorShaderProgram;
import com.zhou.lawson.openglpractice.data.programs.TextureShaderProgram;
import com.zhou.lawson.openglpractice.helper.MatrixHelper;
import com.zhou.lawson.openglpractice.helper.TextureHelper;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;

/**
 * Created by lawson on 16/12/5.
 */

public class AirHockeyRenderer implements GLSurfaceView.Renderer {
  private final Context context;

  private final float[] projectionMatrix = new float[16];
  private final float[] modelMatrix = new float[16];

  private Table table;
  private Mallet mallet;

  private TextureShaderProgram textureProgram;
  private ColorShaderProgram colorProgram;

  private int texture;

  public AirHockeyRenderer(Context context) {
    this.context = context;
  }

  @Override public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

    table = new Table();
    mallet = new Mallet();

    textureProgram = new TextureShaderProgram(context);
    colorProgram = new ColorShaderProgram(context);

    texture = TextureHelper.loadTexture(context, R.drawable.air_hockey_surface);
  }

  @Override public void onSurfaceChanged(GL10 glUnused, int width, int height) {
    // Set the OpenGL viewport to fill the entire surface.
    glViewport(0, 0, width, height);

    MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1f, 10f);

    setIdentityM(modelMatrix, 0);
    translateM(modelMatrix, 0, 0f, 0f, -2.5f);
    rotateM(modelMatrix, 0, -60f, 1f, 0f, 0f);

    final float[] temp = new float[16];
    multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
    System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
  }

  @Override public void onDrawFrame(GL10 glUnused) {
    // Clear the rendering surface.
    glClear(GL_COLOR_BUFFER_BIT);

    // Draw the table.
    textureProgram.useProgram();
    textureProgram.setUniforms(projectionMatrix, texture);
    table.bindData(textureProgram);
    table.draw();

    // Draw the mallets.
    colorProgram.useProgram();
    colorProgram.setUniforms(projectionMatrix);
    mallet.bindData(colorProgram);
    mallet.draw();
  }
}
