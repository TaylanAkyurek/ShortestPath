public class UnionSet{

int set[];


 public void makeSet(int[][]array_graph){
  int set[]=new int[array_graph[0].length];
    
   for(int i=0; i<array_graph[0].length; i++){
      set[i]=-1;

    }
  this.set=set;
  }

  public int find(int i){

  if(set[i]<0){
    
    return i;
  }
  else{
    return find(set[i]);
  }

  }

  void makeUnion( int i, int j){
  
  int x=set[find(i)];
  int y=set[find(j)];

  if(x>y){

    set[find(i)]=find(j);

    set[find(j)]+=x;
  }
  else{
    set[find(j)]=find(i);
    
    set[find(i)]+=y;
  }

  

  }



}