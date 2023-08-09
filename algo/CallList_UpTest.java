package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;
import ex0.algo.ElevatorAlgo;
import ex0.simulator.Call_A;
import ex0.simulator.Simulator_A;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallList_UpTest {

    @Test
    void add() {
        CallList_Down c = new CallList_Down();
        System.out.println("first test");
        System.out.println("create a bulding ");
        Simulator_A.initData(5, null);
        Building tower = Simulator_A.getBuilding();
        ElevatorAlgo algoT = new OnlineElevAlgo(tower);
        CallForElevator call = new Call_A(0, 18, 20);
        Elevator e = tower.getElevetor(0);
        CallList c1 = new CallList_Up();
        c1.add(e,call);
        assertTrue(!c1._l1.isEmpty() || !c1._l2.isEmpty());

    }

    @Test
    void dist() {
        CallList_Down  c = new CallList_Down();
        System.out.println("first test");
        System.out.println("create a bulding ");
        Simulator_A.initData(5, null);
        Building tower = Simulator_A.getBuilding();
        ElevatorAlgo algoT = new OnlineElevAlgo(tower);
        CallForElevator call = new Call_A(0, 18, 20);
        Elevator e = tower.getElevetor(0);
        CallList c1 = new CallList_Up();
        c1.add(e,call);
        int ans = (int) c1.dist(e,call);
        assertTrue(ans != 0);


        ;

    }
}