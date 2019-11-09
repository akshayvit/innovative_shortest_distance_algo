import java.util.*;
import java.lang.Math;
class uniter
{
    public int st,ft,profit;
    uniter(int s,int f,int w)
    {
         this.st=s; this.ft=f; this.profit=w;
    }
}
class unit
{
   int wt,ind;
   unit(int w,int i)
  { 
    this.wt=w;
    this.ind=i;
} 
}
class sortern implements Comparator<uniter>
{
    public int compare(uniter a,uniter b)
    {
            return a.ft-b.ft;
    }
}
class graph
{
  static int count=1;
  public static ArrayList<unit>[] vert;
  private static int n;
  private static ArrayList<Integer> tin,tot;
  graph(int m){
     this.n=m;
     vert=new ArrayList[m];
     for(int i=0;i<m;i++)
     { 
         vert[i]=new ArrayList<>();
     }
     tin=tot=new ArrayList<Integer>();
  }
  public static void addedge(int u,int v,int w)
{
   vert[u].add(new unit(w,v));
}
public static void dfs()
{
   boolean [] visited=new boolean[n];
   for(int i=0;i<n;i++)
   {
        visited[i]=false;
   }
   visited[0]=true;
    /*for(int i=0;i<n;i++)
    {
        System.out.println("Source: "+i);
         for(int j=0;j<vert[i].size();j++)
         {
               System.out.println(vert[i].get(j).ind+" "); 
         }
    }*/
   int pre[] = new int[n];
 int post[]=new int[n];
  for(int i=0;i<n;i++)
  {
   dfsutil(visited,i,n-1,pre,post);
  }
 for(int i=0;i<n;i++){
        tin.add(pre[i]);
        tot.add(post[i]);
   }
}
public static void dfsutil(boolean[] v,int src,int dest,int[] p,int[] q)
{
   int ti,to;
   v[src]=true;
   p[src]=count;
   count+=1;
  //System.out.println("Source "+src); 
   for(int i=0;i<vert[src].size();i++)
{ 
   if(!v[vert[src].get(i).ind])
  {
   //  System.out.println(vert[src].get(i).ind);
      dfsutil(v,vert[src].get(i).ind,dest,p,q);
   }
}
  q[src]=count++;
}
public static  int select(ArrayList<uniter> a,int i)
{
    for(int j=i-1;j>=0;j--)
    {
         if(a.get(i).st>=a.get(j).ft)
               return j;
    }
    return -1;
}
public static void minprof()
{
    dfs();
    System.out.println(n);
    for(int i=0;i<n;i++)
    {
        System.out.println(i+"->"+tin.get(i)+" "+tot.get(i));
    }
    ArrayList<uniter> al=new ArrayList<>();
    int od=0,id=0;
    int[] nw=new int[n];
    for(int i=0;i<n;i++)
    {
       od=0;
       for(int j=0;j<vert[i].size();j++)
       { 
            od+=vert[i].get(j).wt;
        }
        nw[i]=od;
    }
    for(int i=0;i<n;i++)
   {
      al.add(new uniter(tin.get(i),tot.get(i),nw[i]));
   }
    Collections.sort(al,new sortern());
    int[] dp=new int[n];
    dp[0]=al.get(0).profit;
    for(int i=1;i<al.size();i++)
    {
        int incl=dp[i];
        int sl=select(al,i);
        if(sl!=-1)
         {
               System.out.println(sl);
               incl+=dp[sl];
         }
        dp[i]=dp[i-1]<incl ? dp[i-1]:incl;
   }
  System.out.println("Distance: "+dp[n-1]);
}
}
class algo1
{
    public static void main(String[] args)
   {
         graph g=new graph(5);
         g.addedge(0,1,4);
         g.addedge(0,2,5);
         g.addedge(1,3,1);
         g.addedge(3,2,2);
         g.addedge(2,4,3);
         g.addedge(3,4,2);
         g.minprof();
   }
}