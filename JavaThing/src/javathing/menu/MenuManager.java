/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import javathing.MainClass;
import javathing.input.MenuMouseListener;
import javathing.settings.Settings;

/**
 *
 * @author Henry
 */
public class MenuManager {
    List<MenuButton> buttons;
    public MenuManager(List<MenuButton> buttons, Color bgColor) {
        this.buttons = buttons;
	this.bgColor = bgColor;
	addMouseListener(new MenuMouseListener());//Yea... I might move this somewhere else, but it will be here for now.
    }
    
    private Color bgColor;
    
    public void paint(Graphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HTIGHT);
        for (MenuButton b : buttons) {
            b.paint(g);
        }
    }
    
    public void handleMouseRelease(MouseEvent me) {
	for (MenuButton button : buttons) {
	    if (button.isClicked(me)) {
		button.onClick();
	    }
	}
    }
    
    private boolean listenersAreActive = false;
    private List<KeyListener> keyListeners = new LinkedList<KeyListener>();
    public void addKeyListener(KeyListener keyListener) {
	keyListeners.add(keyListener);
	if (listenersAreActive) {
	    MainClass.addKeyListener(keyListener);
	}
    }
    
    public void removeKeyListener(KeyListener keyListener) {
	keyListeners.remove(keyListener);
	if (listenersAreActive) {
	    MainClass.removeKeyListener(keyListener);
	}
    }
    
    private List<MouseListener> mouseListeners = new LinkedList<MouseListener>();
    public void addMouseListener(MouseListener mouseListener) {
	mouseListeners.add(mouseListener);
	if (listenersAreActive) {
	    MainClass.addMouseListener(mouseListener);
	}
    }
    
    public void removeMouseListener(MouseListener mouseListener) {
	mouseListeners.remove(mouseListener);
	if (listenersAreActive) {
	    MainClass.removeMouseListener(mouseListener);
	}
    }
    public void activateListeners() {
	listenersAreActive = true;
	for (KeyListener kl : keyListeners) {
	    MainClass.addKeyListener(kl);
	}
	for (MouseListener ml : mouseListeners) {
	    MainClass.addMouseListener(ml);
	}
    }
    
    public void deactivateListeners() {
	listenersAreActive = false;
	for (KeyListener kl : keyListeners) {
	    MainClass.removeKeyListener(kl);
	}
	for (MouseListener ml : mouseListeners) {
	    MainClass.removeMouseListener(ml);
	}
    }
}
