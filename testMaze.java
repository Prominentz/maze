//Ralph Perricelli
public class testMaze {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//construct new maze
		Maze maze = new Maze();
		
		//read maze and get info
		maze.readMaze();
		
		//printMaze and show contents
	    maze.printMaze();
	    
	    //start recursive solution
	    maze.goNorth();
	    
	    //show that i traveled to every location in the maze before i hit the end location
	    maze.printMaze();
	    

	}

}
