import java.io.*;
class DLDetect {
    int np,nr,resource[],available[],request[][],allocation[][];
    boolean marked[],updated;
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public DLDetect() throws Exception{
        System.out.println("Enter no of process");
        np=Integer.parseInt(br.readLine());
        System.out.println("Enter no of resources");
        nr=Integer.parseInt(br.readLine());
        marked=new boolean[np];
        resource=new int[nr];
        available=new int[nr];
        request=new int[np][nr];
        allocation=new int[np][nr];
        for(int i=0;i<nr;i++){
            System.out.println("Enter no of units of resource "+(i+1)+" available in the system");
            resource[i]=Integer.parseInt(br.readLine());
        }
        System.out.println("Enter request matrix");
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                request[i][j]=Integer.parseInt(br.readLine());
            }
        }
        System.out.println("Enter allocation matrix");
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                allocation[i][j]=Integer.parseInt(br.readLine());
            }
        }
        for(int i=0;i<nr;i++){
            int sum=0;
            for(int j=0;j<np;j++){
                sum+=allocation[j][i];
            }
            available[i]=resource[i]-sum;
        }
    }
    public void simulate(){
        markProcess();
        do{
            updated=false;
            for(int i=0;i<np;i++){
                if((!marked[i])){
                    int count=0;
                    for(int j=0;j<nr;j++){
                        if(request[i][j]<=available[j]){
                            count++;
                        }
                    }
                    if(count==nr){
                        marked[i]=true;
                        for(int j=0;j<nr;j++){
                            available[j]+=allocation[i][j];
                        }
                        updated=true;
                    }
                }
            }
        }while(updated);
        checkDeadlock();
    }

    private void markProcess() {
        for(int i=0;i<np;i++){
            int count=0;
            for(int j=0;j<nr;j++){
                if(allocation[i][j]==0){
                    count++;
                }
            }
            if(count==nr){
                marked[i]=true;
            }
        }
    }

    private void checkDeadlock() {
        int count=0;
        for(int i=0;i<np;i++){
            if(marked[i]){
                count++;
            }
        }
        if(count==np){
            System.out.println("No deadlock");
        }
        else{
            System.out.println("Dealocked processes are");
            for(int i=0;i<np;i++){
                if(!marked[i]){
                    System.out.print("\t"+(i+1));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new DLDetect().simulate();
    }
}
