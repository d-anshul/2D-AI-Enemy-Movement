package tests

import com.fasterxml.jackson.databind.util.LinkedNode
import game.enemyai.PlayerLocation
import game.enemyai.AIPlayer
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest._

class Task1 extends FunSuite {


  test("your test") {

    var pl1: PlayerLocation = new PlayerLocation(5.0,2.0,"p1")
    var pl2: PlayerLocation = new PlayerLocation(-3.3,-6.0,"p2")
    var pl3: PlayerLocation = new PlayerLocation(5.0,3.0,"p3")
    var cpl3: PlayerLocation = new PlayerLocation(2.0,1.0,"cp3")

    var plloc: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](pl1,null)
    plloc = new LinkedListNode[PlayerLocation](pl2,plloc)

    var aipl1: AIPlayer = new AIPlayer("cp3")
    assert(aipl1.locatePlayer("p1",plloc).x==5.0)
    assert(aipl1.locatePlayer("p1",plloc).y==2.0)

    assert(aipl1.locatePlayer("p2", plloc).x == -3.3)
    assert(aipl1.locatePlayer("p2", plloc).y == -6.0)


    var pl11: PlayerLocation = new PlayerLocation(5.0, 2.0, "p1")
    var pl21: PlayerLocation = new PlayerLocation(3.0, 6.0, "p2")
    var pl31: PlayerLocation = new PlayerLocation(5.0, 3.0, "p3")
    var cpl31: PlayerLocation = new PlayerLocation(2.0, 1.0, "cp3")

    var plloc1: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](pl11, null)
    plloc1 = new LinkedListNode[PlayerLocation](pl21, plloc1)
    plloc1 = new LinkedListNode[PlayerLocation](cpl31, plloc1)
    plloc1 = new LinkedListNode[PlayerLocation](pl31, plloc1)
    var aipl2: AIPlayer = new AIPlayer("cp3")
    var abc0 = aipl2.closestPlayer(plloc1).playerId
    var abc = aipl2.closestPlayer(plloc1).x
    var abc1 = aipl2.closestPlayer(plloc1).y
    assert("p1" == abc0)
    assert(2.0==abc1)
    assert(5.0 == abc)


    var aipl3: AIPlayer = new AIPlayer("cp7")
    var gp1: GridLocation = new GridLocation(5,2)
    var gp2: GridLocation = new GridLocation(13,9)
    var lln = aipl3.computePath(gp1,gp2)
    var ln11 = new LinkedListNode[GridLocation](new GridLocation(13,9),null)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(13,8),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(13,7),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(13,6),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(13,5),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(13,4),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(13,3),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(13,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(12,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(11,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(10,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(9,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(8,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(7,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(6,2),ln11)
    ln11 = new LinkedListNode[GridLocation](new GridLocation(5,2),ln11)

    while(ln11 != null)
      {
        assert(lln.value.x== ln11.value.x)
        assert(lln.value.y== ln11.value.y)
        ln11 = ln11.next
        lln = lln.next


      }

    var aipl34: AIPlayer = new AIPlayer("cp47")
    var gp3: GridLocation = new GridLocation(-1,2)
    var gp4: GridLocation = new GridLocation(2,-3)
    var lln11 = aipl34.computePath(gp3, gp4)
    println(lln11)

    var ln1133 = new LinkedListNode[GridLocation](new GridLocation(2,-3),null)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(2,-2),ln1133)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(2,-1),ln1133)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(2,0),ln1133)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(2,1),ln1133)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(2,2),ln1133)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(1,2),ln1133)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(0,2),ln1133)
    ln1133 = new LinkedListNode[GridLocation](new GridLocation(-1,2),ln1133)

    while (lln11 != null) {
      assert(lln11.value.x == ln1133.value.x)
      assert(lln11.value.y == ln1133.value.y)
      ln1133 = ln1133.next
      lln11 = lln11.next


    }



  }

}
