package io.view;

import io.controller.IOController;

import javax.swing.JFrame;

/**
 * Grame for the IO project
 * @author Mikel North
 * @version 1.1 13/12/13 created setup method and constructor. added a title to the window.
 */
public class IOFrame extends JFrame
{
	/**
	 * IOPanel for the frame.
	 */
	private IOPanel basePanel;
	
	/**
	 * constructor for the IOFrame.
	 * @param base controller IOController passed in for MVC relationship
	 */
	public IOFrame(IOController baseController)
	{
		basePanel = new IOPanel(baseController);
		
		setupFrame();
	}
	
	/**
	 * sets up the frame size and loads the content panel.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("IO Project");
		this.setSize(400, 400);
		this.setVisible(true);
	}
}
