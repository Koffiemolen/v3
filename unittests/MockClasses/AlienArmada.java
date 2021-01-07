package MockClasses;

import nl.StijveHark.Game.MovingGameObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AlienArmada {
    private ArrayList<AlienMock> aliens;

    public AlienArmada(ArrayList<AlienMock> aliens){
        this.aliens = aliens;
    }

    public List<AlienMock> getAliens(){
        return Collections.unmodifiableList(this.aliens);
    }

    // Method to add aliens to collection
    public boolean addAlien(AlienMock alien){
        this.aliens.add(alien);
        return true;
    }

    // Method to add aliens to specific location
    public boolean addAlien(int index, AlienMock alien){
        this.aliens.add(index, alien);
        return true;
    }

    // Add remaining aliens from previous collection to this one
    // May come in handy if you need to restart the level when you die, old aliens + new one
    public void addRemainingAliens(AlienArmada alienArmada){
        int alienCount = alienArmada.alienCount();
        for (int index = 0; index < alienCount; index++){
            MovingGameObject alien = alienArmada.takeFirstAlien();
            this.aliens.add(0, (AlienMock) alien);
        }
    }

    // count the remaining aliens
    public int alienCount(){
        return aliens.size();
    }

    // select the first alien from the collection, remove and return it
    public MovingGameObject takeFirstAlien(){
        if(this.alienCount() > 0){
            MovingGameObject alien = this.aliens.get(0);
            this.removeAlien(alien);
            return alien;
        }
        return null;
    }

    // Remove specific alien from collection
    public boolean removeAlien(MovingGameObject alien){
        int alienIndex = findAlienIndex(alien);
        if(alienIndex >= 0){
            aliens.remove(alienIndex);
            return true;
        }
        return false;
    }

    // Returns index of specific alien
    private int findAlienIndex(MovingGameObject alien){
        return this.aliens.indexOf(alien);
    }

    // Shuffle the alien collection
    protected void shuffleAliens(){
        Collections.shuffle(this.aliens, new Random());
    }

    public MovingGameObject getFirstAlien(){
        return this.aliens.get(0);
    }
}
