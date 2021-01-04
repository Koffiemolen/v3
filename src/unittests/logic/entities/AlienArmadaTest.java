package logic.entities;

import MockClasses.AlienMock;

import java.util.ArrayList;

public class AlienArmadaTest {
    AlienArmada alienArmada;

    // Setting things up

    public AlienArmada buildAlienArmada(){
        // Set up alien colection
        ArrayList<AlienMock> list = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            // 5 columns
            for (int column = 0; column < 5; column++) {
                // Enemy speed will increase each level
                list.add(new AlienMock((20 + (row * 100)), (20 + (column * 60)), 1, 0, column, null, 40, 40));
            }
        }
        return new AlienArmada(list);

    }
}
