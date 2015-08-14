package com.cdgames.jogl.helloworld;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class SimpleGLEventListener implements GLEventListener {

	public SimpleGLEventListener() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		//Try to add a shape
				GL2 gl;
				
				float deltaX =0.0f, deltaY = 0.0f, deltaZ = 0.0f;
		
				gl = drawable.getGL().getGL2();
				gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
				// Enables clearing of the depth buffer
				gl.glClearDepth(1.0f);
				// Z-Buffer algorithm
				gl.glEnable(GL2.GL_DEPTH_TEST);
				// The type of depth test to do
				gl.glDepthFunc(GL2.GL_LEQUAL);
				gl.glCullFace(GL2.GL_FRONT);
				gl.glEnable(GL2.GL_CULL_FACE);
				gl.glFrontFace(GL2.GL_CW);
				
				gl.glLoadIdentity();
				gl.glTranslatef(deltaX, deltaY, deltaZ);
				gl.glColor3f(1.0f, 1.0f, 1.0f);
				gl.glBegin(GL2.GL_TRIANGLE_STRIP);
				gl.glNormal3f(0.0f, 0.0f, 1.0f);
				gl.glVertex3f(0.0f, 0.0f, 0.0f);
				gl.glVertex3f(0.3f, 0.0f, -0.4f);
				gl.glVertex3f(0.0f, 0.3f, 0.0f);
				gl.glVertex3f(0.3f, 0.3f, 0.4f);
				gl.glVertex3f(0.3f, 0.6f, 0.0f);
				gl.glEnd();

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub

	}
}
