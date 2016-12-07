package com.zhou.lawson.openglpractice;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.zhou.lawson.openglpractice.renders.AirHockeyRenderer;

public class MainActivity extends AppCompatActivity {

  private GLSurfaceView glSurfaceView;
  private boolean rendererSet = false;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    glSurfaceView = new GLSurfaceView(this);

    final AirHockeyRenderer airHockeyRenderer = new AirHockeyRenderer(this);
    if (Utils.supportsEs2(this)) {
      glSurfaceView.setEGLContextClientVersion(2);
      glSurfaceView.setRenderer(airHockeyRenderer);
      rendererSet = true;
    } else {
      Toast.makeText(this, "This device does not support OpenGL ES 2.0.", Toast.LENGTH_LONG).show();
    }
    glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        if (event != null) {
          // Convert touch coordinates into normalized device
          // coordinates, keeping in mind that Android's Y
          // coordinates are inverted.
          final float normalizedX = (event.getX() / (float) v.getWidth()) * 2 - 1;
          final float normalizedY = -((event.getY() / (float) v.getHeight()) * 2 - 1);

          if (event.getAction() == MotionEvent.ACTION_DOWN) {
            glSurfaceView.queueEvent(new Runnable() {
              @Override public void run() {
                airHockeyRenderer.handleTouchPress(normalizedX, normalizedY);
              }
            });
          } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            glSurfaceView.queueEvent(new Runnable() {
              @Override public void run() {
                airHockeyRenderer.handleTouchDrag(normalizedX, normalizedY);
              }
            });
          }

          return true;
        } else {
          return false;
        }
      }
    });
    setContentView(glSurfaceView);
  }

  @Override protected void onPause() {
    super.onPause();
    if (rendererSet) {
      glSurfaceView.onPause();
    }
  }

  @Override protected void onResume() {
    super.onResume();
    if (rendererSet) {
      glSurfaceView.onResume();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
