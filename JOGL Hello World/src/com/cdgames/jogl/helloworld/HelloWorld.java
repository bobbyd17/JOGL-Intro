package com.cdgames.jogl.helloworld;

import com.jogamp.opengl.GLCapabilities;

public class HelloWorld {

	public static void main (String args[]){
		try{
			System.loadLibrary("jogl_cg");//name in lib folder after lib prefix and before extension
			System.out.println("Hello World! (libs in the house)");
			GLCapabilities caps = new GLCapabilities(null);
			System.out.println("Hello JOGL! (jar available)");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
