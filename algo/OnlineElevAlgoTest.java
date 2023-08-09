package ex0.algo;


import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;
import ex0.algo.ElevatorAlgo;
import ex0.simulator.Call_A;
import ex0.simulator.Simulator_A;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;

class OnlineElevAlgoTest {


    @Test
    void allocateAnElevator() {

        System.out.println("first test");
        System.out.println("create a bulding ");
        Simulator_A.initData(5, null);
        Building tower = Simulator_A.getBuilding();
        ElevatorAlgo algoT = new OnlineElevAlgo(tower);
        CallForElevator call = new Call_A(0, 18, 20);
        for (int i = 0; i < tower.numberOfElevetors(); i++) {
            Elevator e = algoT.getBuilding().getElevetor(i);
            e.goTo(tower.minFloor() + i);
        }
        int ans = algoT.allocateAnElevator(call);
        int c = Simulator_A.getBuilding().getElevetor(ans).getState();
        assertNotEquals(c,0);
    }

    @Test
    void cmdElevator() {
        System.out.println("first test");
        System.out.println("create a bulding ");
        Simulator_A.initData(5, null);
        Building tower = Simulator_A.getBuilding();
        ElevatorAlgo algoT = new OnlineElevAlgo(tower);
        CallForElevator call = new Call_A(0, 18, 20);

        for (int i = 0; i < tower.numberOfElevetors(); i++) {
            Elevator e = algoT.getBuilding().getElevetor(i);
            e.goTo(tower.minFloor() + i);
        }
        int ans = algoT.allocateAnElevator(call);
        int p = Simulator_A.getBuilding().getElevetor(ans).getPos();
        assertNotEquals(18,ans);


    }
}