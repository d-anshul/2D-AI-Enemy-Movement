package tests
import com.fasterxml.jackson.databind.util.LinkedNode
import game.enemyai.{AIGameState, AIPlayer, PlayerLocation}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest._


class Task3 extends FunSuite {

  test("No walls") {

    var aipl34: AIPlayer = new AIPlayer("33")
    var gp3: GridLocation = new GridLocation(2, 3)
    var gp4: GridLocation = new GridLocation(5,5)
    var aig: AIGameState = new AIGameState
    aig.levelWidth = 20
    aig.levelHeight = 20
    aipl34.distanceAvoidWalls(aig,gp3,gp4)
    print(aipl34.distanceAvoidWalls(aig,gp3,gp4))
    assert(aipl34.distanceAvoidWalls(aig,gp3,gp4)==5)









  }


  test("walls") {

    var aipl34: AIPlayer = new AIPlayer("33")
    var gp3: GridLocation = new GridLocation(2, 9)
    var gp4: GridLocation = new GridLocation(4, 9)
    var aig: AIGameState = new AIGameState
    aig.levelWidth = 20
    aig.levelHeight = 20
    aig.wallLocations =   List(new GridLocation(3, 10), new GridLocation (3, 9), new GridLocation (3, 8), new GridLocation (3, 7))
    aipl34.distanceAvoidWalls(aig, gp3, gp4)
    print(aipl34.distanceAvoidWalls(aig, gp3, gp4))
    assert(aipl34.distanceAvoidWalls(aig, gp3, gp4) == 6)


  }

}
