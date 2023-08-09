package ex0.algo;

import ex0.CallForElevator;
import ex0.Elevator;

public abstract class CallList {
    protected LinkedList _l1 = new LinkedList();
    protected LinkedList _l2 = new LinkedList();


    public abstract void add(Elevator e, CallForElevator c);
    public abstract double dist(Elevator e, CallForElevator c);

}
