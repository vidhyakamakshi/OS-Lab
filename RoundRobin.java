import java.io.*;
class RR {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int n,q,timer,arr[],ser[],rem[],start[],fin[],wait[],tat[];
    boolean started[],done[];
    public RR() throws Exception{
        System.out.println("Enter no of process");
        n=Integer.parseInt(br.readLine());
        arr=new int[n];
        ser=new int[n];
        rem=new int[n];
        start=new int[n];
        fin=new int[n];
        wait=new int[n];
        tat=new int[n];
        started=new boolean[n];
        done=new boolean[n];
        System.out.println("Enter arrival time");
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(br.readLine());
            started[i]=false;
            done[i]=false;
        }
        System.out.println("Enter service time");
        for(int i=0;i<n;i++){
            ser[i]=Integer.parseInt(br.readLine());
            rem[i]=ser[i];
        }
        System.out.println("Enter quantum");
        q=Integer.parseInt(br.readLine());
    }
    public void simulate(){
        timer=0;
        int prevpos=0;
        int totalTime=getTotalTime();
        while(timer!=totalTime){
            int range=getRange();
            int pos=getPos(range,prevpos);
            if(!started[pos]){
                start[pos]=timer;
                started[pos]=true;
            }
                if(rem[pos]<=q){
                    timer+=rem[pos];
                    for(int i=0;i<range;i++){
                        if((started[i])&&(!done[i])&&(i!=pos)){
                            wait[i]+=rem[pos];
                        }
                    }
                    rem[pos]=0;
                    fin[pos]=timer;
                    done[pos]=true;
                }
                else{
                    rem[pos]-=q;
                    timer+=q;
                    for(int i=0;i<range;i++){
                        if((started[i])&&(!done[i])&&(i!=pos)){
                            wait[i]+=q;
                        }
                    } 
                }
            prevpos=pos;
        }
        for(int i=0;i<n;i++){
            int delay=start[i]-arr[i];
            wait[i]+=delay;
            tat[i]=wait[i]+ser[i];
        }
        display();
    }
    private int getTotalTime() {
        int t=0;
        for(int i=0;i<n;i++){
            t+=ser[i];
        }
        return t;
    }
    private int getRange() {
        int range=0;
        for(int i=0;i<n;i++){
            if(arr[i]<=timer){
                range++;
            }
        }
        return range;
    }
    private int getPos(int range,int prev) {
        int pos=0;
        boolean turn=false;
        for(int i=0;i<range;i++){
            if(!done[i]){
                if(i>prev){
                    pos=i;
                    turn= true; 
                    break;
                }
            }
        }
        if(!turn){
            for(int i=0;i<range;i++){
                if(!done[i]){
                    pos=i;
                    break;
                }
            }
        }
        return pos;
    }
    private void display() {
        System.out.println("Process \t Arrival time \t Service time \t Start time \t Finish time \t Waiting time \t Turn Around time");
       for(int i=0;i<n;i++){
           System.out.println((i+1)+"\t"+arr[i]+"\t"+ser[i]+"\t"+start[i]+"\t"+fin[i]+"\t"+wait[i]+"\t"+tat[i]);
       }
    }
    public static void main(String[] args) throws Exception{
        new RR().simulate();
    }
}
