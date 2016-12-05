package com.zhou.lawson.openglpractice.data.programs;

import android.content.Context;
import com.zhou.lawson.openglpractice.helper.ShaderHelper;

import static android.opengl.GLES20.glUseProgram;
import static com.zhou.lawson.openglpractice.Utils.readTextFileFromResource;

/**
 * Created by lawson on 16/12/5.
 */

public abstract class ShaderProgram {

  // Uniform constants
  protected static final String U_MATRIX = "u_Matrix";
  protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

  // Attribute constants
  protected static final String A_POSITION = "a_Position";
  protected static final String A_COLOR = "a_Color";
  protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

  // Shader program
  protected final int program;

  protected ShaderProgram(Context context, int vertexShaderResourceId,
      int fragmentShaderResourceId) {
    // Compile the shaders and link the program.
    program = ShaderHelper.buildProgram(readTextFileFromResource(context, vertexShaderResourceId),
        readTextFileFromResource(context, fragmentShaderResourceId));
  }

  public void useProgram() {
    // Set the current OpenGL shader program to this program.
    glUseProgram(program);
  }
}
