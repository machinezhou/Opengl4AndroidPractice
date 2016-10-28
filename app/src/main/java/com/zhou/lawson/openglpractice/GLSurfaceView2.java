package com.zhou.lawson.openglpractice;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by lawson on 16/10/28.
 */

public class GLSurfaceView2 extends GLSurfaceView {
  final AirHockeyRenderer renderer;

  public GLSurfaceView2(Context context) {
    super(context);
    setEGLContextClientVersion(2); // This is the important line
    renderer = new AirHockeyRenderer(context);
    setRenderer(renderer);
  }
}