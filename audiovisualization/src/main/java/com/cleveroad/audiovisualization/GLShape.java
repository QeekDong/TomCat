package com.cleveroad.audiovisualization;

import android.opengl.GLES20;

/**
 * Abstract shape implementation.
 * 创建shader程序
 */
abstract class GLShape {

	protected static final String VERTEX_POSITION = "vPosition";
	protected static final String VERTEX_COLOR = "vColor";
	private static final String VERTEX_SHADER_CODE =
			"attribute vec4 " + VERTEX_POSITION + ";" +
					"void main() {" +
					"  gl_Position = " + VERTEX_POSITION + ";" +
					"}";
	private static final String FRAGMENT_SHADER_CODE =
			"precision mediump float;" +
					"uniform vec4 " + VERTEX_COLOR + ";" +
					"void main() {" +
					"  gl_FragColor = " + VERTEX_COLOR + ";" +
					"}";
	protected static final int COORDS_PER_VERTEX = 3;
	protected static final int SIZE_OF_FLOAT = 4;
	protected static final int SIZE_OF_SHORT = 2;

	/**
	 * Shape color.
	 */
	private final float color[];

	/**
	 * Program associated with shape.
	 */
	private final int program;

	public GLShape(float[] color) {
		this.color = color;
		// 加载顶点着色器
		int vertexShader = GLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER_CODE);
        // 加载片元着色器
		int fragmentShader = GLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);
        // 创建程序
		program = GLES20.glCreateProgram();
        // 向程序中加入顶点着色器
		GLES20.glAttachShader(program, vertexShader);
        // 向程序中加入片元着色器
		GLES20.glAttachShader(program, fragmentShader);
        // 链接程序
		GLES20.glLinkProgram(program);
	}

	protected float[] getColor() {
		return color;
	}

	protected int getProgram() {
		return program;
	}

    public void setColor(float[] color) {
        System.arraycopy(color, 0, this.color, 0, this.color.length);
    }
}
