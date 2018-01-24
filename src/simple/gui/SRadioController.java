/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Benji
 */
public class SRadioController extends SElement 
{
    private ArrayList<SRadioButton> radioButtons;
    private SButton defaultButton;
    
    private Point upperLeftCorner;
    private Point lowerRightCorner;

    public SRadioController(SController controller) 
    {
        super(-99, -99, -99, -99, controller);
        
        radioButtons = new ArrayList<>();
    }
    
    public void add(SRadioButton button)
    {
        radioButtons.add(button);
    }
    
    public void add(SRadioButton[] buttons)
    {
        for(SRadioButton b : buttons)
            add(b);
    }
    
    public void remove(SRadioButton button)
    {
        radioButtons.remove(button);
    }
    
    public int numButtons()
    {
        return radioButtons.size();
    }
    
    public void setDefaultButton(SRadioButton button)
    {
        defaultButton = button;
        radioControl(button);
    }
    
    public void setDefaultButton(int index)
    {
        defaultButton = radioButtons.get(index);
    }

    @Override
    public void paint(Graphics g) {        
        for(SRadioButton b : radioButtons)
        {
            b.paint(g);
        }
    }

    @Override
    public ActionEvent mouseClicked(MouseEvent e) {        
        for(SRadioButton b : radioButtons)
        {
            if(e.getButton() == 1 && b.isCaptured() && !b.isDisabled())
            {
                radioControl(b);
                return new ActionEvent(b, 195819, b.getName());
            }
        }
        return null;
    }
    
    private void radioControl(SRadioButton button)
    {
        button.toggleOn();
        for(SRadioButton b2 : radioButtons)
        {
            if(!b2.equals(button))
            {
                b2.toggleOff();
            }

        }
    }
    
    public String[] getLinkedButtonNames() {
        String[] names = new String[100];
        for(int i = 0; i < radioButtons.size(); ++i)
            names[i] = radioButtons.get(i).getName();
        return names;
    }

    public ArrayList<SRadioButton> getRadioButtons() {
        return radioButtons;
    }

    @Override
    public ActionEvent mousePressed(MouseEvent e) {
        return super.mousePressed(e);
    }

    @Override
    public ActionEvent mouseReleased(MouseEvent e) {
        return super.mouseReleased(e);
    }
    
    
}
