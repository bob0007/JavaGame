/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitygame.ui;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import gravitygame.render.Paintable;

/**
 *
 * @author Henry
 */
public abstract class Component implements Paintable {
public abstract MouseListener getMouseListener();
public abstract KeyListener getKeyListener();
}
