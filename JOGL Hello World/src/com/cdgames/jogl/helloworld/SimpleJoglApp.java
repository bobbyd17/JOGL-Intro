package com.cdgames.jogl.helloworld;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class SimpleJoglApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleJoglApp() throws HeadlessException {
		super("Simple JOGL Application");//JFrame Title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ends program when JFrame is closed
		
		//JOGL Code!!!
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		GLCanvas glcanvas = new GLCanvas(glcapabilities);
		glcanvas.addGLEventListener(new SimpleGLEventListener());
		glcanvas.display();
		
		getContentPane().add(glcanvas, BorderLayout.CENTER);
		setSize(500,300);
		
		centerWindow(this);
		
	}
	
	public void centerWindow(Component frame){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		
		if(frameSize.width > screenSize.width){
			frameSize.width = screenSize.width;
		}
		if(frameSize.height > screenSize.height){
			frameSize.height = screenSize.height;
		}
        frame.setLocation( (screenSize.width/2 - frameSize.width/2) , (screenSize.height/2 - frameSize.height/2) );//sets the center of the frame at the center of the screen

	}

	public SimpleJoglApp(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SimpleJoglApp(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SimpleJoglApp(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		final SimpleJoglApp app = new SimpleJoglApp();
		
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					app.setVisible(true);
				}
			}
		);
	}

}
