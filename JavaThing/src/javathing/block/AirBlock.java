/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javathing.block;

import java.awt.Graphics;
import javathing.render.PlatformerGraphicsUtil;

/**
 *
 * @author lausd_user
 */
public class AirBlock extends Block {

    
    public AirBlock(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean getPassable() {
        return true;
    }

    @Override
    public void paint(Graphics g) {
       
    }

    @Override
    public void update() {
    }
    
}
