/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

/**
 *
 * @author Benji
 */
public abstract class SApplication extends JFrame implements KeyListener, MouseListener, MouseMotionListener, ActionListener
{
    protected SController controller;
    protected Thread controllerThread;
    
    protected boolean halted;
    
    protected double scaleX = 1;
    protected double scaleY = 1;
    
    Dimension screenSize;
    
    public SApplication() 
    {   
        super();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                
        super.addMouseListener((MouseListener)this);
        super.addKeyListener((KeyListener)this);
        super.addMouseMotionListener((MouseMotionListener)this);
    }

    public void setScale(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    public void start()
    {
        controllerThread = new Thread(controller);
        controllerThread.setPriority(Thread.MAX_PRIORITY);
        controllerThread.start();
    }
    
    public void halt(String reason) {
        controller.halt();
        this.setAlwaysOnTop(false);
        this.dispose();
    }
    
    public void resetController() {
        halted = false;
        //controller.enable();
        controllerThread = new Thread(controller);
        controllerThread.setPriority(Thread.MAX_PRIORITY);
        controllerThread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        controller.paint(g);
    }

    public boolean isHalted() {
        return halted;
    }
    
    protected void buildUI()
    {
        controller = new SController(this);
    }    

    @Override
    public Point getMousePosition() throws HeadlessException {
        try {
            return new Point((int)(super.getMousePosition().getX()*scaleX), 
                (int)(super.getMousePosition().getY()*scaleY));
        } catch (NullPointerException e) {
        }
        return new Point(0, 0);
    }
    
    
}
