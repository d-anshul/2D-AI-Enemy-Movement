package game.enemyai

import game.enemyai.decisiontree.DecisionTreeValue
import game.lo4_data_structures.graphs.Graph
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.{AIAction, MovePlayer}
import org.scalactic.{Or, attempt}

import scala.collection.mutable._
import java.util
import scala.collection.mutable.ListBuffer

class AIPlayer(val id: String) {


  // TODO: Replace this placeholder code with your own


  def locatePlayer(playerId: String, playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {



    if (playerLocations.value.playerId == playerId) {
      playerLocations.value
    }
    else if (playerLocations==null){
      null
    }
    else {
      locatePlayer(playerId, playerLocations.next)
    }
  }




var pl: PlayerLocation = new PlayerLocation(0.0,0.0,"")

  // TODO: Replace this placeholder code with your own
  def closestPlayer(playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {

    var x0 = locatePlayer(id, playerLocations).x
    //println(x0)
    var y0 = locatePlayer(id, playerLocations).y
    //println(y0)
    var mindist = 0.0
    var list: ListBuffer[Double] = ListBuffer[Double]()
    var node1 = playerLocations
    while (node1 != null) {
      if(node1.value.playerId == id) {
        //println(node1.value.playerId)
        node1 = node1.next
      }
      else{
        //println(node1.value.playerId + ":" + node1.value.x + "," + node1.value.y)
        var x1 = node1.value.x
        var y1 = node1.value.y
        var ecd = Math.sqrt(Math.pow((x1 - x0), 2) + Math.pow((y1 - y0), 2))
        list += ecd
        node1 = node1.next
      }



    }
    //println(list)
    mindist = list.min
    //println("mindist"+ mindist)
    var node2 = playerLocations
    while (node2 != null) {
      var x1 = node2.value.x
      var y1 = node2.value.y
      var ecd = Math.sqrt(Math.pow((x1 - x0), 2) + Math.pow((y1 - y0), 2))
      var cappa = 0.01
      if(node2.value.playerId == id) {
        //println(node2.value.playerId)
        node2 = node2.next

      }
      else {
        if ((mindist - ecd).abs < cappa) {
          return node2.value
        }
        else {
          node2 = node2.next
        }
      }
      }
    null
    }



  // TODO: Replace this placeholder code with your own
  def computePath(start: GridLocation, end: GridLocation): LinkedListNode[GridLocation] = {

    var node: LinkedListNode[GridLocation] = new LinkedListNode[GridLocation](end, null)
    var x0 = end.x
    var y0 = end.y
    var x1 = start.x
    var y1 = start.y
    while(Math.abs(node.value.x - start.x) + Math.abs(node.value.y - start.y) > 1 ){

      var up = new GridLocation(x0,y0+1)
      var down = new GridLocation(x0,y0-1)
      var left = new GridLocation(x0-1,y0)
      var right = new GridLocation(x0+1,y0)
      var mapofmovement = Map("up" -> up, "down" -> down, "left" -> left, "right" -> right)

      var upd = (up.x-x1).abs + (up.y-y1).abs
      var downd = (down.x-x1).abs + (down.y-y1).abs
      var leftd = (left.x-x1).abs + (left.y-y1).abs
      var rightd = (right.x-x1).abs + (right.y-y1).abs
      var mapofd = Map("up" -> upd, "down" -> downd, "left" -> leftd, "right" -> rightd)
      var min = mapofd.minBy(_._2)._1


      node = new LinkedListNode[GridLocation](mapofmovement(min),node)
      x0 = mapofmovement(min).x
      y0 = mapofmovement(min).y



    }
    node = new LinkedListNode[GridLocation](start,node)
    node

  }

  var a = 0

  // TODO: Replace this placeholder code with your own
  def makeDecision(gameState: AIGameState, decisionTree: BinaryTreeNode[DecisionTreeValue]): AIAction = {

      if (decisionTree.value.check(gameState) < 0) {
        makeDecision(gameState, decisionTree.left)
      }
      else if (decisionTree.value.check(gameState) > 0) {

        makeDecision(gameState, decisionTree.right)
      }
      else  {

        //println(decisionTree.value.action(gameState))
        decisionTree.value.action(gameState)
        //println(decisionTree.value.action(gameState))

      }

  }
    //return  MovePlayer(id,Math.random(),Math.random())
    //MovePlayer(this.id, Math.random() - 0.5, Math.random() - 0.5)

  def distanceAvoidWalls(gameState: AIGameState, g1: GridLocation, g2: GridLocation): Int = {

    var k = gameState.levelAsGraph()
    var gf1 = gameState.gridLocationToId(g1)
    var gf2 = gameState.gridLocationToId(g2)
    bfs(gameState, k,g1, g2 )

  }
  def bfs(gameState: AIGameState, graph: Graph[GridLocation] , gl1: GridLocation, gl2: GridLocation): Int ={

    val q  = new Queue[Int]()
    var l = new ListBuffer[Int]
    var m = Map[Int,Int]()
    var exp = false
    var dis = 0
    q.enqueue(gameState.gridLocationToId(gl1))
    m += (gameState.gridLocationToId(gl1)->0)

    while(q.isEmpty != true){

      var temp = q.dequeue()

      var gp = graph.adjacencyList(temp)
      for(i <- gp) {
        if (l.contains(i)== false) {
          exp = true
          l += i
          q.enqueue(i)
          dis = m(temp) + 1
          m += (i -> dis)
          if(i == gameState.gridLocationToId(gl2) ){
            return dis
          }

        }
      }

    }

    dis



  }





  // TODO: Replace this placeholder code with your own
  def closestPlayerAvoidWalls(gameState: AIGameState): PlayerLocation = {
    closestPlayer(gameState.playerLocations)

  }

  // TODO: Replace this placeholder code with your own
  def getPath(gameState: AIGameState): LinkedListNode[GridLocation] = {
    computePath(locatePlayer(this.id, gameState.playerLocations).asGridLocation(), closestPlayerAvoidWalls(gameState).asGridLocation())
  }




}

