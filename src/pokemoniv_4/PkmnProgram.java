package pokemoniv_4;

import gui.PkmnGUIContainer;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Copyright 2016, Stephen Gung, All rights reserved
 * 
 * @author Stephen Gung
 */
public class PkmnProgram{
	private JFrame frame;
	
	private final PkmnGUIContainer container_l;
	private final PkmnGUIContainer container_r;
	
	public PkmnProgram(){
		container_l = new PkmnGUIContainer();
		container_r = new PkmnGUIContainer();
	}
	
	public void createUI(){
			// Creating frame
			frame = new JFrame( "Pokemon IV Breeding Probability Calculator (Gen-VI)");
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
			frame.setResizable( false);

			// Menu Bar for our frame
			JMenuBar menuBar = new JMenuBar();
			// Create a File menu
			JMenu fileMenu = createJMenu( "File", KeyEvent.VK_F);
			fileMenu.add( createJMenuItem( "Exit", 0, 0));
			menuBar.add( fileMenu);
			// Create a Options menu
			JMenu optionsMenu = createJMenu( "Options", KeyEvent.VK_O);
			menuBar.add( optionsMenu);
			// Create a Help menu
			JMenu helpMenu = createJMenu( "Help", KeyEvent.VK_H);
			menuBar.add( helpMenu);
			// Set this bar for our frame
			frame.setJMenuBar( menuBar);

			JPanel framePanel = new JPanel();
			framePanel.setLayout( new BoxLayout( framePanel, BoxLayout.X_AXIS));
			framePanel.add( container_l.createDefaultPanel());
			framePanel.add( container_r.createDefaultPanel());

			frame.add( framePanel);
			frame.pack();
			frame.setVisible( true);
		}
	
	public PkmnGUIContainer getContainer_l(){	return container_l;	}
	public PkmnGUIContainer getContainer_r(){	return container_r;	}
	
	// Helper method
	public JMenu createJMenu( String text, int key){
		JMenu menu = new JMenu( text);
		menu.setMnemonic( key);
		return menu;
	}
	
	// Helper method
	public JMenuItem createJMenuItem( String text, int key, int action){
		JMenuItem menuItem = new JMenuItem( text);
		if( key != 0 && action != 0)
			menuItem.setAccelerator( (KeyStroke.getKeyStroke( key, action)));
		return menuItem;
	}
}