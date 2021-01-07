package logic.entities;

import MockClasses.AlienArmada;
import MockClasses.AlienMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    void setUp(){
        this.alienArmada = buildAlienArmada();
    }

    // https://java-design-patterns.com/patterns/arrange-act-assert/
    @Test
    void addAlien_ArmadaSize(){
        // Arrange: Set up a test environment
        // Row 6, column 0
        AlienMock alien = new AlienMock(520,20,1,0,1,null,40,40);

        // Act: Act out the test scenario's
        this.alienArmada.addAlien(alien);

        // Assert: Verify the result from the test
        assertEquals(31,this.alienArmada.getAliens().size());
    }

    @Test
    void addAlienVerifyLocationInArmada(){
        // Arrange: Set up a test environment
        AlienMock alien = new AlienMock(520,20,1,0,1,null,40,40);

        // Act: Act out the test scenario's
        this.alienArmada.addAlien(alien);

        // Assert: Verify the result from the test
        assertEquals(alien,this.alienArmada.getAliens().get(30));
    }

    @Test
    void addAlienToSpecificLocationInArmada_ArmadaSize(){
        // Arrange: Set up a test environment
        AlienMock alien = new AlienMock(520,20,1,0,1,null,40,40);

        // Act: Act out the test scenario's
        this.alienArmada.addAlien(1,alien);

        // Assert: Verify the result from the test
        assertEquals(31,this.alienArmada.getAliens().size());
    }

    @Test
    void addAlienToSpecificLocationInArmada_CorrectPosition(){
        // Arrange: Set up a test environment
        AlienMock alien = new AlienMock(520,20,1,0,1,null,40,40);

        // Act: Act out the test scenario's
        this.alienArmada.addAlien(30,alien);

        // Assert: Verify the result from the test
        assertEquals(alien,this.alienArmada.getAliens().get(30));
    }

    @Test
    void addAlienArmadaToAlienArmada_ArmadaSizeFirst(){
        // Arrange: Set up a test environment
        AlienArmada secondArmada = buildAlienArmada();

        // Act: Act out the test scenario's
        this.alienArmada.addRemainingAliens(secondArmada);

        // Assert: Verify the result from the test
        assertEquals(60,this.alienArmada.getAliens().size());
    }

    @Test
    void addAlienArmadaToAlienArmada_SecondArmadaSize(){
        // Arrange: Set up a test environment
        AlienArmada secondArmada = buildAlienArmada();

        // Act: Act out the test scenario's
        this.alienArmada.addRemainingAliens(secondArmada);

        // Assert: Verify the result from the test
        assertEquals(60,this.alienArmada.getAliens().size());
    }

    @Test
    void removeAlien_ValidRemovalTargetWithIncorrectReturn(){
        // Arrange: Set up a test environment
        AlienMock removedAlien = this.alienArmada.getAliens().get(1);

        // Act: Act out the test scenario's
        this.alienArmada.removeAlien(removedAlien);

        // Assert: Verify the result from the test
        assertFalse(this.alienArmada.getAliens().contains(removedAlien));
    }

    @Test
    void removeAlien_ValidRemovalTargetWithCorrectReturn(){
        // Arrange: Set up a test environment
        AlienMock removedAlien = this.alienArmada.getAliens().get(1);

        // Act: Act out the test scenario's
        boolean response = this.alienArmada.removeAlien(removedAlien);

        // Assert: Verify the result from the test
        assertTrue(response);
    }

    @Test
    void removeAlien_ValidRemovalTargetWithAramadaCount(){
        // Arrange: Set up a test environment
        AlienMock removedAlien = this.alienArmada.getAliens().get(1);

        // Act: Act out the test scenario's
        this.alienArmada.removeAlien(removedAlien);

        // Assert: Verify the result from the test
        assertEquals(29, this.alienArmada.getAliens().size());
    }

    @Test
    void removeAlien_InvalidRemovalTargetWithResult(){
        // Arrange: Set up a test environment
        AlienMock removedAlien = new AlienMock(0,0,0,0,0,null,0,0);

        // Act: Act out the test scenario's
        boolean response = this.alienArmada.removeAlien(removedAlien);

        // Assert: Verify the result from the test
        assertFalse(response);
    }

    @Test
    void removeAlien_InvalidRemovalTargetWithArmadaCount(){
        // Arrange: Set up a test environment
        AlienMock removedAlien = new AlienMock(0,0,0,0,0,null,0,0);

        // Act: Act out the test scenario's
        this.alienArmada.removeAlien(removedAlien);

        // Assert: Verify the result from the test
        assertEquals(30,this.alienArmada.getAliens().size());
    }

    @Test
    void showFirstAlien(){
        // Arrange: Set up a test environment
        AlienMock firstAlien = alienArmada.getAliens().get(0);

        // Act: Act out the test scenario's
        AlienMock alien = (AlienMock) alienArmada.getFirstAlien();

        // Assert: Verify the result from the test
        assertEquals(firstAlien, alien);
    }

    @Test
    void removeFirstAlienCountSizeAlienArmada(){
        // Arrange: Set up a test environment

        // Act: Act out the test scenario's
        this.alienArmada.takeFirstAlien();

        // Assert: Verify the result from the test
        assertEquals(29, this.alienArmada.getAliens().size());
    }

    @Test
    void removeFirstAlienAndCheckFirstAlien(){
        // Arrange: Set up a test environment
        AlienMock compareAlien = alienArmada.getAliens().get(0);

        // Act: Act out the test scenario's
        AlienMock alien = (AlienMock) this.alienArmada.takeFirstAlien();

        // Assert: Verify the result from the test
        assertEquals(compareAlien,alien);
    }

    @Test
    void countAliens(){
        // Arrange: Set up a test environment
        int amountCheck = this.alienArmada.getAliens().size();

        // Act: Act out the test scenario's
        int amount = this.alienArmada.alienCount();

        // Assert: Verify the result from the test
        assertEquals(amount, amountCheck);
    }
}
