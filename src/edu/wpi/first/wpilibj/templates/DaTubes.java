/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author PatrickM
 */
public class DaTubes {

    public void set(boolean on) {
        for (int i = 0; i < this.Valves.length; i++) {
            this.Valves[i].set(on);
        }
        for (int i = 0; i < this.Groups.length; i++) {
            this.Groups[i].set(on);
        }
        for (int i = 0; i < this.ControlGroups.length; i++) {
            this.ControlGroups[i].set(on);
        }
    }
    public ValveGroup Groups[];
    public ControlGroup ControlGroups[];
    public Solenoid Valves[];

    public void addPnumaticValve(Solenoid valve) {
        this.Valves[this.Valves.length] = valve;
    }

    public void addPnumaticValveGroup(ValveGroup group) {
        this.Groups[this.Groups.length] = group;
    }

    public void addPnumaticValveControlGroup(ControlGroup controlgroup) {
        this.ControlGroups[this.ControlGroups.length] = controlgroup;
    }
    public ControlGroup ControlSystems[];

    public class ValveGroup {

        public void set(boolean on) {
            for (int i = 0; i < this.Valves.length; i++) {
                this.Valves[i].set(on);
            }
        }
        public Solenoid Valves[];

        public void addPnumaticValve(Solenoid valve) {
            this.Valves[this.Valves.length] = valve;
        }
    }

    public class ControlGroup {

        public ValveGroup Groups[];
        public ControlGroup ControlGroups[];
        public Solenoid Valves[];

        public void addPnumaticValve(Solenoid valve) {
            this.Valves[this.Valves.length] = valve;
        }

        public void addPnumaticValveGroup(ValveGroup group) {
            this.Groups[this.Groups.length] = group;
        }

        public void addPnumaticValveControlGroup(ControlGroup controlgroup) {
            this.ControlGroups[this.ControlGroups.length] = controlgroup;
        }

        public void set(boolean on) {
            for (int i = 0; i < this.Valves.length; i++) {
                this.Valves[i].set(on);
            }
            for (int i = 0; i < this.Groups.length; i++) {
                this.Groups[i].set(on);
            }
            for (int i = 0; i < this.ControlGroups.length; i++) {
                this.ControlGroups[i].set(on);
            }
        }
    }
}
