package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

public class OnlineElevAlgo implements ElevatorAlgo {
    public static final int UP = 1, DOWN = -1, ERROR = -2;
    private Building _building;
    private CallList[] _elevL;
    private LinkedList l;


    public OnlineElevAlgo(Building b) {
        this._building = b;
        if (_building.numberOfElevetors() == 1) {
            _elevL = new CallList[2];
            _elevL[0] = new CallList_Up();
            _elevL[1] = new CallList_Down();
            this.l = _elevL[0]._l1;
        } else {
            this._elevL = new CallList[_building.numberOfElevetors()];
            for (int i = 0; i < _elevL.length; i++) {
                if (i % 2 == 0) {
                    _elevL[i] = new CallList_Down();    // Even --> DOWN
                } else {
                    _elevL[i] = new CallList_Up();  // Odd --> UP
                }
            }
        }
    }

    @Override
    public Building getBuilding() {
        return _building;
    }

    @Override
    public String algoName() {
        return "OnlineElevAlgo";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {

        if (c.getSrc() == c.getDest()) {
            throw new RuntimeException("ERR: You are already in your destination");
        }
        int ans = 0;

        if (_building.numberOfElevetors() == 1) {
            if (c.getSrc() < c.getDest()) { //Up call
                _elevL[0].add(_building.getElevetor(0), c);
                return 0;
            }
            if (c.getSrc() > c.getDest()) { //Down call
                _elevL[1].add(_building.getElevetor(0), c);
                return 0;
            }
        }

        if (c.getDest() > c.getSrc()) { //UP --> odd elevator

            Elevator thisElev;
            double minDist = Integer.MAX_VALUE;
            for (int i = 1; i < _elevL.length; i += 2) {
                thisElev = _building.getElevetor(i);
                if ((thisElev.getState() != ERROR)) {
                    double thisDist = _elevL[i].dist(thisElev, c);
                    if (thisDist < minDist) {
                        minDist = thisDist;
                        ans = i;
                    }
                }
            }
        } else if (c.getDest() < c.getSrc()) { //DOWN --> even elevator

            Elevator thisElev;
            double minDist = Integer.MAX_VALUE;
            for (int i = 0; i < _elevL.length; i += 2) {
                thisElev = _building.getElevetor(i);
                if ((thisElev.getState() != ERROR)) {
                    double thisDist = _elevL[i].dist(thisElev, c);
                    if (thisDist < minDist) {
                        minDist = thisDist;
                        ans = i;
                    }
                }
            }
        }
        _elevL[ans].add(_building.getElevetor(ans), c);   //Add the call to the elevator list
        return ans;
    }


    @Override
    public void cmdElevator(int elev) {
        int numberOfElev = _building.numberOfElevetors();
        if (numberOfElev == 1) {
            oneElev(elev);
            return;
        }

        Elevator e = this._building.getElevetor(elev);
        CallList list = _elevL[elev];
        LinkedList cl1 = list._l1, cl2 = list._l2;


        if ((e.getState() == Elevator.ERROR) || (cl1.isEmpty() && cl2.isEmpty()))
            return;

        if (cl1._head == null) e.goTo(cl2.getNum(0));
        else {
            e.goTo(cl1.getNum(0));
        }

        this.l = cl1;

        if (list instanceof CallList_Up) {
            if (e.getState() != Elevator.DOWN) {
                {
                    l.removeUp(e.getPos());
                }
            }
        }

        if (list instanceof CallList_Down) {
            if (e.getState() != Elevator.UP) {
                {
                    l.removeDown(e.getPos());
                }
            }
        }
    }





    public void oneElev(int elev) {
        Elevator e = this.getBuilding().getElevetor(elev);
        CallList u = _elevL[0];
        CallList d = _elevL[1];

        if ((e.getState() == Elevator.ERROR))
            return;
        if(u._l1.isEmpty() && d._l1.isEmpty())
            return;


        if (u._l1.isEmpty() && !d._l1.isEmpty())
            l = d._l1;
        else if(d._l1.isEmpty() && !u._l1.isEmpty())
            l = u._l1;


        e.goTo(this.l.getNum(0));
        if (this.l == u._l1) {
            u._l1.removeUp(e.getPos());
        }

        if (this.l == d._l1) {
            d._l1.removeDown(e.getPos());
        }
    }
}


