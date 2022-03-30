import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
public class HW4 {
	
	public static void main(String[] args) {
  	Scanner scan = new Scanner(System.in);
		Graph graph = new Graph();
				
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] parts = line.split(" ");
			if (parts[0].equals("end")) break;
			String src = parts[0];
			String dst = parts[1];
			int cost = Integer.parseInt(parts[2]);
			int latency = Integer.parseInt(parts[3]);
			
			graph.addVertex(src);
			graph.addVertex(dst);
			Edge edge = new Edge(src, dst, cost, latency);
			graph.addEdge(edge);
		}
		
	  //System.out.println(Arrays.deepToString(graph.asArray(false)));
		//System.out.println(Arrays.deepToString(graph.asArray(true)));
		
		HW4 hw4 = new HW4();
		scan.close();
		
		System.out.println(hw4.totalTransitTime(graph));
		System.out.println(hw4.cheapestTransitTime(graph));
		System.out.println(hw4.timeIncrease(graph));

	}
	
	// You can add any methods you need, both to this file and Graph.java file
  static Graph cheapestNetwork(Graph graph) {
		// TODO Auto-generated method stub
    Graph newGraph=new Graph();

    int array_graph[][]= graph.asArray(false);
    int latency_graph[][]= graph.asArray(true);
    int sum=0;

    PriorityQueue<Edge> edgeHeap=new PriorityQueue<>();

    for(int i=0;i<array_graph[0].length;i++){
    for(int j=i+1;j<array_graph[0].length;j++){

      if(array_graph[i][j]>0){
      Edge e= new Edge(Integer.toString(i),Integer.toString(j),array_graph[i][j],latency_graph[i][j]);
      edgeHeap.add(e);
      }
    }
  }
  UnionSet unionSet=new UnionSet();
  unionSet.makeSet(array_graph);
  Edge current;
  for(int i=0;i<array_graph[0].length;i++){
      current=edgeHeap.remove();
        if(unionSet.find(Integer.parseInt(current.src))!=unionSet.find(Integer.parseInt(current.dst))){
          sum+=current.cost;
          newGraph.addEdge(current);
          newGraph.addVertex(current.src);
          newGraph.addVertex(current.dst);
          unionSet.makeUnion(Integer.parseInt(current.src),Integer.parseInt(current.dst));
        }
        else{
          
        }

  }

    return newGraph;
	}



  static int getMin(int array[],boolean isKnown[]){
  int min =10000;
  int index=-1;
  for(int i=0; i<array.length;i++){
    if (array[i]<min && isKnown[i]==false){
    min=array[i];
    index=i;
    }
  }
  return index;
  }

	// The method for task 1 
	int totalTransitTime(Graph graph) {
		// TODO Auto-generated method stub
  int sum=0; 
  int adjMatrix[][] = graph.asArray(true);

  boolean isKnown[]= new boolean[adjMatrix[0].length];
//if i used PriorityQueue, it's impossible to acces by indexing and every time i want to access an element i should search and its O(n) so i prefered array.
 
  int dist[]= new int[adjMatrix[0].length];

for(int k=0;k<adjMatrix[0].length;k++){
 
  for(int i=0;i<adjMatrix[0].length;i++){

    dist[i]=10000;
  }

  for(int i=0;i<adjMatrix[0].length;i++){
    isKnown[i]=false;
  }

  
  dist[k]=0;
  int current=0;

  for(int i=0;i<adjMatrix[0].length;i++){
  current=HW4.getMin(dist,isKnown);
  isKnown[current]=true;
    
    for(int j=0;j<adjMatrix[0].length;j++){
      if(adjMatrix[current][j]!=0){
        if(isKnown[j]==false){
          if(dist[j]>dist[current]+adjMatrix[current][j]){
            dist[j]=dist[current]+adjMatrix[current][j];
          }
        }        
      }
    }
  }

  for(int i=0;i<dist.length;i++){
      sum+=dist[i];
    }
}
		return sum;
	}

	// The method for task 2 
	int cheapestTransitTime(Graph graph) {
		// TODO Auto-generated method stub
		return totalTransitTime(cheapestNetwork(graph));
	}

	// The method for task 3 
	int timeIncrease(Graph graph) {
		// TODO Auto-generated method stub
		return totalTransitTime(cheapestNetwork(graph))-totalTransitTime(graph);
	}
	
}
