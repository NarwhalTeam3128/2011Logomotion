/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.Components;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author PatrickM
 */
public class XboxGamepad extends Joystick
{
    public Stick rStick;
    public Stick lStick;
    public Button A;
    public Button B;
    public Button X;
    public Button Y;
    public Button Start;
    public Button Back;
    public Button LB;
    public Button RB;
    public DPad pad;
    public Trigger lTrigger;
    public Trigger rTrigger;


    public XboxGamepad(final int port)
    {
        super(port);
    }

    public void controllerSetup()
    {
        try
        {
            rStick = new Stick("right");
            lStick = new Stick("left");
            A = new Button("A");
            B = new Button("B");
            X = new Button("X");
            Y = new Button("Y");
            Start = new Button("START");
            Back = new Button("BACK");
            LB = new Button("LB");
            RB = new Button("RB");
            pad = new DPad();
            lTrigger= new Trigger("Left");
            rTrigger= new Trigger("Right");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     */
    public class Stick
    {

        String stick;

        public Stick(String leftright) throws Exception
        {
            stick = leftright;
            if (!(stick == null ? "left" == null : stick.equals("left")) & !(stick == null ? "right" == null : stick.equals("right")))
            {
                throw new IllegalArgumentException("invalid stick name:" + this.stick);
            }
        }

        /**
         *
         * @return
         */
        public double getStickX()
        {
            if (this.stick == null ? "left" == null : this.stick.equals("left"))
            {
                return getRawAxis(1);
            } 
            else if (this.stick == null ? "right" == null : this.stick.equals("right"))
            {
                return getRawAxis(4);
            }
            return 0;
        }

        /**
         *
         * @return
         */
        public double getStickY()
        {
            if (stick == null ? "left" == null : stick.equals("left"))
            {
                return getRawAxis(2);
            } 
            else if (stick == null ? "right" == null : stick.equals("right"))
            {
                return getRawAxis(5);
            }
            return 0;
        }


        public boolean isStickPressed()
        {
            if (this.stick == null ? "left" == null : this.stick.equals("left"))
            {
                return getRawButton(9);
            }
            else if (this.stick == null ? "right" == null : this.stick.equals("right"))
            {
                return getRawButton(10);
            }
            return false;
        }
    }

    /**
     *
     */
    public class DPad
    {

        DPad()
        {

        }
        /**
         *
         * @return
         */
        public double getLeftRight()
        {
            System.out.println(getRawAxis(6));
            return getRawAxis(6);
        }

        /**
         *
         * @return
         */
        public double getUpDown()
        {
            System.out.println(getRawAxis(6));
            return getRawAxis(7);
        }
    }

    /**
     *
     */
    public class Button
    {

        /**
         * This class represents a VALID button on the xbox controller
         * Valid buttons:
         * A,B,X,Y,START,BACK,LB,RB
         */
        private int button;

        Button(String buttonname) throws Exception
        {
            if (buttonname == null ? "A" == null : buttonname.equals("A"))
            {
                this.button = 1;
            } 
            else if (buttonname == null ? "B" == null : buttonname.equals("B"))
            {
                this.button = 2;
            } 
            else if (buttonname == null ? "X" == null : buttonname.equals("X"))
            {
                this.button = 3;
            } 
            else if (buttonname == null ? "Y" == null : buttonname.equals("Y"))
            {
                this.button = 4;
            } 
            else if (buttonname == null ? "START" == null : buttonname.equals("START"))
            {
                this.button = 8;
            } 
            else if (buttonname == null ? "BACK" == null : buttonname.equals("BACK"))
            {
                this.button = 7;
            } 
            else if (buttonname == null ? "LB" == null : buttonname.equals("LB"))
            {
                this.button = 5;
            } 
            else if (buttonname == null ? "RB" == null : buttonname.equals("RB"))
            {
                this.button = 6;
            } 
            else
            {
                throw new IllegalArgumentException("invalid button name:" + buttonname);
            }

        }

        /**
         *
         * @return
         */
        public boolean isPressed()
        {
            return getRawButton(this.button);
        }
    }

    /**
     *
     */
    public class Trigger
    {

        private int side;
        private double value;

        Trigger(String LeftRight) throws Exception
        {
            if (LeftRight == null ? "Left" == null : LeftRight.equals("Left"))
            {
                this.side = 1;
            } 
            else if (LeftRight == null ? "Right" == null : LeftRight.equals("Right"))
            {
                this.side = 2;
            } 
            else
            {
                throw new IllegalArgumentException("invalid trigger:" + LeftRight);
            }
        }

        /**
         *
         * @return
         */
        public double TriggerPressedPercent()
        {
            this.value = getRawAxis(3);
            if ((this.side == 1 & this.value <= 0) || (this.side == 2 & this.value >= 0))
            {
                return Math.abs(this.value);
            } 
            else
            {
                return 0;
            }
        }
    }




}

