//ralph perricelli
//The SHOW Lifestyle 2014
//maze class and solver 
//csc 111 spring 2014

import java.util.Scanner;
import java.io.*;

public class Maze{

//Instance Variables
private static final char PATH = 'p';
private static final char CLEAR = ' ';
private static final char VISITED = 'v';
private static final char FINISH = 'F';
private int numRows=7;
private int numCols=20;
private int startRow;
private int startCol;
private int endRow;
private int endCol;
private int curCol;
private int curRow;
private char[][] maze = new char[numRows][numCols];
	
//default constructor
	public Maze()
	{
		//nothing in here because our file input does all the work
	}

	//reads maze and populates array
	public void readMaze()
	{
		
		//initializes string used for input
		Scanner fileInput;
		
		File inFile = new File("/Users/RalphPerricelli/Desktop/maze1.txt");
		System.out.println("Reading Maze! \n");
		
		//try catch for file reading
		try{ 
			fileInput = new Scanner(inFile);
			
			//takes in info based upon original maze sheet 
			numCols = fileInput.nextInt();
			numRows = fileInput.nextInt();
			endCol = fileInput.nextInt();
			endRow = fileInput.nextInt();
			startCol = fileInput.nextInt();
			startRow = fileInput.nextInt();
			curCol=startCol;
			curRow=startRow;
			
			//There was an extra space after the last int, so i read it and take it way
			String temp=fileInput.nextLine();//removes spaces and leaves maze
			
			//reads mazes and populates array
			while(fileInput.hasNext()){
				for(int i=0; i<numRows; i++)
				{	
					String input = fileInput.nextLine();
					for(int j = 0; j<numCols; j++)
					{
						maze[i][j]=input.charAt(j);
					}
					
				}
				
				
			}
			maze[startRow][startCol]='S';
		}///end try
		//if file not found let the user know
		catch (FileNotFoundException e){
			System.out.println(e);
			System.exit(1);
		}//end catch
		
	}
	
	//print maze and print the win/start locations
	public void printMaze()
	{
		//print out important info
		System.out.println("\nnumRows: " + numRows + " numCols: " + numCols);
		System.out.println("Start Loc: (" + startRow + "," + startCol + ")");
		System.out.println("Win Loc: (" + endRow + "," + endCol +")");
		
		//loop through maze and print its contents
		for (int i = 0; i < numRows; i++) {
		    for (int j = 0; j < numCols; j++) {
		        System.out.print(maze[i][j]);
		    }
		System.out.println();
		}
	}
	
	//go north method
	public boolean goNorth(){
        
		//flag variable
		boolean success;
        
        //check to see if north spot is open
        if(maze[curRow-1][curCol] == CLEAR)
        {
            curRow = curRow - 1;
            maze[curRow][curCol] = PATH;
            
            //checks for end condition
            if (maze[curRow][curCol] == maze[endRow][endCol])
            { 
            	maze[endRow][endCol]=FINISH;
            	success = true;
            } else {
                success = goNorth();//if it worked then go north again
                if(!success){
                
                	success = goWest();//if not then go west
                    
                	if(!success){
                    
                		success = goEast();//if not west then try east
                        
                		//if not then we are in an enclosed box!
                		if(!success){
                        
                			maze[curRow][curCol] = VISITED;
                			curRow = curRow + 1;
                        }
                    }
                }
            }
        } else {
            success = false;
    }
    return success;
}

	
	//go west method
	public boolean goWest(){
        
		//flag variable
		boolean success;
        
		//check to see if west position is open
		if(maze[curRow][curCol-1] == CLEAR){
        
			curCol = curCol - 1;
            maze[curRow][curCol] = PATH;
            
            //checks for end condition
            if (maze[curRow][curCol] == FINISH)
            {
            	success = true;
            } else {
            success = goWest();//go west if it works
                
            if(!success){
                success = goSouth();//if not we try south
                    
                if(!success){
                    success = goNorth();//worst case we try south
                        
                    if(!success)
                    {
                        maze[curRow][curCol] = VISITED;
                        curCol = curCol + 1;
                    }
                        }
                    }
                }
            } else {
                success = false;
        }
        return success;
    }

	//go east method
	public boolean goEast(){
        
		//flag var
		boolean success;
        
		//check for open east space
		if(maze[curRow][curCol+1] == CLEAR){
            curCol = curCol + 1;
            maze[curRow][curCol] = PATH;
            
            //check for end condition
            if (maze[curRow][curCol] == FINISH){
            success = true;
            } 
            else 
            {
            success = goEast();//IF IT Works go east
                
            if(!success){
                success = goNorth();//if not east try north
                    
                if(!success){
                    success = goSouth();//if not north try south
                        
                    if(!success){
                            maze[curRow][curCol] = VISITED;
                            curCol = curCol - 1;
                            }
                        }
                    }
                }
            } else {
                success = false;
        }
        return success;
    }

	
	//go south method
	public boolean goSouth(){
        
		//flag variable
		boolean success;
        
		//check if south spot is clear
		if(maze[curRow+1][curCol] == CLEAR){
            curRow = curRow + 1;
            maze[curRow][curCol] = PATH;
            
            //check for end condition
            if (maze[curRow+1][curCol] == FINISH){
            success = true;
            } 
            else
            {
            success = goSouth();//if soutj works keep going
                
            if(!success){
                success = goEast();//try east
                    
                if(!success){
                    success = goWest();//try west
                        
                    if(!success){
                            maze[curRow][curCol] = VISITED;
                            curRow = curRow - 1;
                            }
                        }
                    }
                }
            } else {
                success = false;
        }
        return success;
    }
	
	
}
	
