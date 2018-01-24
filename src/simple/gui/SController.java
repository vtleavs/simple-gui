/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benji
 */
public class SController implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    ArrayList<SElement> elements = new ArrayList<>();
    
    private boolean halted;
    
    private boolean disabled;
    
    SApplication uiHandler;
    
    public SController(SApplication handler) 
    {
        this.uiHandler = handler;
        
        handler.addMouseListener((MouseListener)this);
        handler.addMouseMotionListener((MouseMotionListener)this);
        handler.addKeyListener((KeyListener)this);
    }
    
    public void paint(Graphics g)
    {
        for(SElement e : elements)
        {
            if(e.isVisible())
            {
                e.paint(g);
            }
        }
    }
    
    public void add(SElement element)
    {
        element.setSize(new Dimension(element.width, element.height));
        element.setLocation(new Point(element.xLocation, element.yLocation));
        elements.add(element);
    }
    
    public void remove(SElement element)
    {
        elements.remove(element);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(disabled)
            return;
        
        ActionEvent event = null;
        for(SElement e : elements)
        {
            if(!e.isDisabled())
            {
                event = e.mouseClicked(me);
                if(event != null)
                {
                    uiHandler.actionPerformed(event);
                    
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(disabled)
            return;
        
        ActionEvent event = null;
        for(SElement e : elements)
        {
            if(!e.isDisabled())
            {
                event = e.mousePressed(me);
                if(event != null)
                {
                    uiHandler.actionPerformed(event);
                    
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(disabled)
            return;
        
        ActionEvent event = null;
        for(SElement e : elements)
        {
            if(!e.isDisabled())
            {
                event = e.mouseReleased(me);
                if(event != null)
                {
                    uiHandler.actionPerformed(event);
                    
                }
            }
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        if(disabled)
            return;
        
        ActionEvent event = null;
        for(SElement e : elements)
        {
            if(!e.isDisabled())
            {
                event = e.mouseDragged(me);
                if(event != null)
                {
                    uiHandler.actionPerformed(event);
                    
                }
            }
        }
    }
    
    public Point getMousePosition()
    {
        return uiHandler.getMousePosition();
    }
    
    public void halt()
    {
        disabled = true;
        halted = true;
    }

    @Override
    public void run() {
        while(!halted)
        {            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(SController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent me) 
    {
        if(disabled)
            return;
        
        for(SElement e : elements)
        {
            if(!e.isDisabled())
            {
                e.mouseMoved(me);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if(disabled)
            return;
        
        for(SElement e : elements)
        {
            if(!e.isDisabled())
            {
                e.keyTyped(ke);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(disabled)
            return;
        
        for(SElement e : elements)
        {
            if(!e.isDisabled())
            {
                e.keyPressed(ke);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public boolean isHalted() {
        return halted;
    }
    
    public void unHalt()
    {
        halted = false;
    }

    public void disable() {
        this.disabled = true;
    }
    
    public void enable() {
        this.disabled = false;
    }
    
    
}