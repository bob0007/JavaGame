/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import gravitygame.MainClass;
import gravitygame.load.PopulationLoader;
import gravitygame.load.TokenResolver;
import gravitygame.render.PlatformerGraphicsUtil;
import gravitygame.settings.Settings;
import gravitygame.utils.MathUtils;

/**
 *
 * @author Henry
 */
public class Turret extends Sprite {
    private int cooldown;
    private int currentTick = 0;
    private static final double size = 30;
    public Turret(int x, int y, int cooldown, int strtingTick) {
        this(Settings.TILE_SIZE * x + ((Settings.TILE_SIZE - size) / 2 ), Settings.TILE_SIZE * y + ((Settings.TILE_SIZE - size) / 2 ), cooldown, strtingTick);
    }
    public Turret(double x, double y, int cooldown, int startingTick) {
        super(x, y, size, size);
        this.setSpriteImage(spriteImage);
        this.setSpriteImageFromResourceLocation("javathing/resources/graphics/sprite/turret.png");
        this.cooldown = cooldown;
        this.currentTick = startingTick;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Point2D.Double position = MainClass.getLevelManager().getPlayer().getPosition();
        
        double angle = MathUtils.getAngle(position.x - x, position.y - y);
        g.setColor(Color.RED);
        PlatformerGraphicsUtil.translateGraphics(g);
        g.drawLine((int) (getX() + width / 2),(int)  (getY() + height / 2), (int) ((getX() + width / 2) + (MathUtils.getCordinates(angle)[0] * 16)),(int) ((getY() + height / 2) + (MathUtils.getCordinates(angle)[1] * 16)));
        PlatformerGraphicsUtil.unTranslateGraphics(g);
    }
    
    
    @Override
    public void update() {
        super.update();
        currentTick++;
        if (currentTick >= cooldown) {
            shoot();
            currentTick = 0;
        }
        
    }
    
    private void shoot() {
        Point2D.Double position = MainClass.getLevelManager().getPlayer().getPosition();
        double angle = MathUtils.getAngle(position.x - x, position.y - y);
        MainClass.getLevelManager().addSprite(new Bullet(getX() + width / 2, getY() + height / 2, angle, 3, Color.red));
    }
    
    public  static Turret getFromArgs(String args, TokenResolver tokenResolver) throws Exception {
        Object[] objArgs = PopulationLoader.getObjectsFromParams(args, tokenResolver);
        double x = PopulationLoader.extrapolateToDouble(((Object[]) objArgs[0])[0]);
        double y = PopulationLoader.extrapolateToDouble(((Object[]) objArgs[0])[1]);
        int cooldown = (int) PopulationLoader.extrapolateToDouble(objArgs[1]);
        int startingTick = (int) PopulationLoader.extrapolateToDouble(objArgs[2]);
        return new Turret(x, y, cooldown, startingTick);
    }
    
    
}
