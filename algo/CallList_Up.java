package ex0.algo;

import ex0.CallForElevator;
import ex0.Elevator;

public class CallList_Up extends CallList {

    public void add(Elevator e, CallForElevator c) {

        if (this._l1.isEmpty() && this._l2.isEmpty()) {    //The elevator is waiting for calls
            this._l1.addInPlace(c.getSrc());
            this._l1.addInPlace(c.getDest());
            return;
        }

        if (this._l1.isEmpty() && !this._l2.isEmpty() && e.getState() == Elevator.DOWN) {
            this._l2.remove(_l2.getNum(0));
            LinkedList.swap(this);
            this.add(e, c);
            return;
        }

        if (e.getState() != Elevator.DOWN) {
            if (e.getPos() <= c.getSrc()) {
                e.stop(c.getSrc());
                this._l1.addInPlace(c.getSrc());
                this._l1.addInPlace(c.getDest());
            }
            else {
                this._l2.addInPlace(c.getSrc());
                this._l2.addInPlace(c.getDest());
            }
            return;
        }

        if (e.getState() == Elevator.DOWN && !_l1.isEmpty()){
            if(c.getSrc()<_l1.getNum(0)){
                this._l2.addInPlace(c.getSrc());
                this._l2.addInPlace(c.getDest());
            }else {
                this._l1.addInPlace(c.getSrc());
                this._l1.addInPlace(c.getDest());
            }
            return;
        }

        if (e.getState() == Elevator.DOWN && _l1.isEmpty() && !_l2.isEmpty()){
            LinkedList.swap(this);
            if(c.getSrc()<_l1.getNum(0)){
                this._l2.addInPlace(c.getSrc());
                this._l2.addInPlace(c.getDest());
            }else {
                this._l1.addInPlace(c.getSrc());
                this._l1.addInPlace(c.getDest());
            }
        }

    }

    public double dist(Elevator e, CallForElevator c){

        int pos = e.getPos(), src = c.getSrc(), dest = c.getDest();
        double speed = e.getSpeed(), start = e.getStartTime(), stop = e.getStopTime();
        double open = e.getTimeForOpen(), close = e.getTimeForClose();

        double ans = Integer.MAX_VALUE;


        CallList_Up testDist = new CallList_Up();
        testDist._l1 = _l1.copy();
        testDist._l2 = _l2.copy();
        testDist.add(e,c);

        if (testDist._l1.contains(src)){
            return Math.abs(pos-dest)*speed +
                    +(stop+open+close+start)*(testDist._l1.indexOf(dest))+ stop+open;
        }
        else{ //if (!testDist._l1.contains(src) && testDist._l2.contains(src)) {
            int lastLevel = testDist._l1.getNum(this._l1._size - 1);
            int firstLevel = testDist._l2._head.getData();
            return (Math.abs(pos - lastLevel) + Math.abs(lastLevel - firstLevel)) * speed +
                    +(stop + open + close + start) * (this._l1._size + testDist._l2.indexOf(dest)) + stop + open;
        }
    }

}

