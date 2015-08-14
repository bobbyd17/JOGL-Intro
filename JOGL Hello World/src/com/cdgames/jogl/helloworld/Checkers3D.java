package com.cdgames.jogl.helloworld;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

@SuppressWarnings("serial")
public class Checkers3D extends Frame implements KeyListener {

  private static float deltaZ = 0.0f;
  private static float deltaX = 0.0f;
  private static float deltaY = 0.0f;
  private static float scaleZ = 1.0f;
  private static float scaleX = 1.0f;
  private static float scaleY = 1.0f;

  static GL2 gl;

  static GLCanvas canvas;
  static GLCapabilities capabilities;
  static GLProfile profile;
  static Animator animator;

  public Checkers3D() {

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        animator.stop();
        dispose();
        System.exit(0);
      }
    });

    profile = GLProfile.getDefault();
    capabilities = new GLCapabilities(profile);
    canvas = new GLCanvas(capabilities);
    animator = new Animator(canvas);
    GraphicListener graphiclistener = new GraphicListener();
    canvas.addGLEventListener(graphiclistener);
    canvas.addKeyListener(this);
    add(canvas, BorderLayout.CENTER);

    animator.start();

  }

  public class GraphicListener implements GLEventListener {

    public void display(GLAutoDrawable drawable) {
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

      //TODO 0.0f and so on does things in relation to screen size. Will need to change the method to do things of exact size.
      gl.glLoadIdentity();
      gl.glTranslatef(deltaX, deltaY, deltaZ);
      gl.glScalef(scaleX, scaleY, scaleZ);
      gl.glColor3f(1.0f, 1.0f, 1.0f);//r,g,b 1.0 = 100%
      gl.glBegin(GL2.GL_QUADS);
      gl.glNormal3f(0.0f, 0.0f, 0.0f);//shading
      gl.glVertex3f(-1.0f, -1.0f, 0.0f);//x,y,z coordinates 
      gl.glVertex3f(1.0f, -1.0f, 0.0f);
      gl.glVertex3f(1.0f, 1.0f, 0.0f);
      gl.glVertex3f(-1.0f, 1.0f, 0.0f);
      

      gl.glColor3f(0.429412f, 0.429412f, 0.429412f);//black
      gl.glVertex3f(-0.8f, -0.8f, 0.0f);//x,y,z coordinates 
      gl.glVertex3f(0.8f, -0.8f, 0.0f);
      gl.glVertex3f(0.8f, 0.8f, 0.0f);
      gl.glVertex3f(-0.8f, 0.8f, 0.0f);
      
      float x = -0.8f;
      float y = -0.8f;
      float size = 0.2f;
      
      gl.glColor3f(2.0f, 0.0f, 0.0f);//red
      for(int i=0; i<8; i++){
    	  for(int j=0; j<8; j++){
    		  if((i+j)%2==0){
    			//draw individual square
    			  gl.glVertex3f(x, y, 0.0f);//x,y,z coordinates 
    		      gl.glVertex3f(x + size, y, 0.0f);
    		      gl.glVertex3f(x + size, y + size, 0.0f);
    		      gl.glVertex3f(x, y + size, 0.0f);
    		  }
    		  x += size;
    	  }
    	  x = -0.8f;
    	  y += size;
      }
      
      gl.glEnd();
      
      
      //TODO make this work for multiple lines
      double xDelta = -.7;
      double yDelta = -.7;
      for(int first = 0; first < 8; first++){
    	  for(int second = 0;second < 8; second++){
    		  if((first+second)%2==1){
    			  if(second<3){
    				  gl.glColor3f(2.0f, 0.0f, 0.0f);//red
    				  for(int b =0; b <= 150; b++){
        		          gl.glBegin(GL.GL_LINES);
        			      double angle = 2 * Math.PI * b / 300;
        			      double angle2 = 2 * Math.PI * (b+150)/300;
        			      double x2 = Math.cos(angle);
        			      double y2 = Math.sin(angle);
        			      double x4 = Math.cos(angle2);
        			      double y4 = Math.sin(angle2);
        			      gl.glVertex3d((x2/12.5)+xDelta,(y2/12.5)+yDelta,0.0d);
        			      gl.glVertex3d((x4/12.5)+xDelta,(y4/12.5)+yDelta,0.0d);
        			      gl.glEnd();
    				  }
    			  }
    			  else if(second>4){
    				  gl.glColor3f(0.0f, 0.0f, 0.0f);//black currently blue
    				  for(int b =0; b <= 150; b++){
        		          gl.glBegin(GL.GL_LINES);
        			      double angle = 2 * Math.PI * b / 300;
        			      double angle2 = 2 * Math.PI * (b+150)/300;
        			      double x2 = Math.cos(angle);
        			      double y2 = Math.sin(angle);
        			      double x4 = Math.cos(angle2);
        			      double y4 = Math.sin(angle2);
        			      gl.glVertex3d((x2/12.5)+xDelta,(y2/12.5)+yDelta,0.0d);
        			      gl.glVertex3d((x4/12.5)+xDelta,(y4/12.5)+yDelta,0.0d);
        			      gl.glEnd();
    				  }
    			  }
    		  }
    	      yDelta += .2;
    	  }
    	  yDelta = -.7;
    	  xDelta += .2;
      }
      
      boolean king = false;
      if(king == true){
		  //crown
		  gl.glColor3f(0.0f, 0.0f, 0.0f);//black
		  gl.glBegin(GL.GL_TRIANGLES);
		  gl.glVertex3d(-0.35d, -0.525d, 0.0d);//.1 is half the diagonal
		  gl.glVertex3d(-0.3d, -0.525d, 0.0d);
		  gl.glVertex3d(-0.35d, -0.45d, 0.0d);//left
		  
		  gl.glVertex3d(-0.25d, -0.525d, 0.0d);
		  gl.glVertex3d(-0.25d, -0.45d, 0.0d);
		  gl.glVertex3d(-0.3d, -0.525d, 0.0d);//right
		  
		  gl.glVertex3d(-0.275d, -0.525d, 0.0d);
		  gl.glVertex3d(-0.3d, -0.45d, 0.0d);
		  gl.glVertex3d(-0.325d, -0.525d, 0.0d);//center
		  
		  gl.glEnd();
      }

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

    public void init(GLAutoDrawable drawable) {
      gl = drawable.getGL().getGL2();
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

      gl.glEnable(GL2.GL_LIGHTING);
      float lightAmbient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
      gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, lightAmbient, 0);

      gl.glEnable(GL2.GL_LIGHT0);
      float lightPosition[] = { -0.4f, 0.5f, 0.7f, 1.0f };
      gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
      float lightIntensity[] = { 1.0f, 1.0f, 1.0f, 1.0f };
      gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, lightIntensity, 0);

      gl.glEnable(GL2.GL_LIGHT1);
      float lightPosition2[] = { 0.0f, -0.8f, 0.3f, 1.0f };
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPosition2, 0);
      float lightIntensity2[] = { 1.0f, 0.0f, 0.0f, 0.0f };
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, lightIntensity2, 0);
      float lightSpecularIntensity2[] = { 1.0f, 1.0f, 1.0f, 1.0f };
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightSpecularIntensity2, 0);

      gl.glEnable(GL2.GL_COLOR_MATERIAL);
      gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
      float lightSpecularColor[] = { 1.0f, 1.0f, 1.0f, 1.0f };
      gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, lightSpecularColor, 0);
      gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 80);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

    public void dispose(GLAutoDrawable drawable) {}

  }

  public void keyTyped(KeyEvent key) {}

  public void keyPressed(KeyEvent key) {
    switch (key.getKeyCode()) {
      case KeyEvent.VK_SPACE://Zoom In?
        scaleZ += 0.05;
        scaleY += 0.05;
        scaleX += 0.05;
        break;
      case KeyEvent.VK_SHIFT://Zoom Out?
        scaleZ -= 0.05;
        scaleY -= 0.05;
        scaleX -= 0.05;
        break;
      case KeyEvent.VK_D://right
        deltaX += 0.05;
        break;
      case KeyEvent.VK_A://left
        deltaX -= 0.05;
        break;
      case KeyEvent.VK_W://up
        deltaY += 0.05;
        break;
      case KeyEvent.VK_S://down
        deltaY -= 0.05;
        break;  
      default:
        break;
    }
  }

  public void keyReleased(KeyEvent key) {}

  public static void main(String[] args) {
    Checkers3D frame = new Checkers3D();
    frame.setTitle("CD Checkers");
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().height, Toolkit.getDefaultToolkit().getScreenSize().height);
    if(Toolkit.getDefaultToolkit().getScreenSize().getWidth()>Toolkit.getDefaultToolkit().getScreenSize().getHeight()){
        frame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-Toolkit.getDefaultToolkit().getScreenSize().getHeight())/2,0);//TODO This assumes width is bigger than height
    }
    else{
        frame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-Toolkit.getDefaultToolkit().getScreenSize().getWidth())/2,0);//TODO This assumes width is bigger than height
    }
    frame.setVisible(true);
    frame.canvas.requestFocus();
  }

}
