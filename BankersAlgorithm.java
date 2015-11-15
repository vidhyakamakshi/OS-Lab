import java.io.*;
class BA {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int np,nr,resource[],available[],claim[][],allocation[][],request[][];
    boolean suspended[];
    public BA() throws Exception{
        System.out.println("Enter no of processes");
        np=Integer.parseInt(br.readLine());
        System.out.println("Enter no of resources");
        nr=Integer.parseInt(br.readLine());
        resource=new int[nr];
        available=new int[nr];
        suspended=new boolean[np];
        claim=new int[np][nr];
        allocation=new int[np][nr];
        request=new int[np][nr];
        for(int i=0;i<nr;i++){
            System.out.println("Enter no of units of resource "+(i+1)+" available in the system");
            resource[i]=Integer.parseInt(br.readLine());
        }
        System.out.println("Enter claim matrix");
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                claim[i][j]=Integer.parseInt(br.readLine());
            }
        }
        System.out.println("Enter allocation matrix");
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                allocation[i][j]=Integer.parseInt(br.readLine());
            }
        }
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                request[i][j]=claim[i][j]-allocation[i][j];
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
        for(int i=0;i<np;i++){
            int count=0;
            for(int j=0;j<nr;j++){
                if(request[i][j]>available[j]){
                    count++;
                }
            }
            if(count==0){
                System.out.println("Process "+(i+1)+" runs to completion");
                for(int j=0;j<nr;j++){
                    available[j]+=allocation[i][j];
                }
            }
            else{
                System.out.println("Process "+(i+1)+" suspended");
                suspended[i]=true;
            }
        }
        checkResume();
        if(checkEqual()){
            System.out.println("Safe");
        }
        else{
            System.out.println("Not safe");
        }
    }
    private void checkResume() {
        for(int i=0;i<np;i++){
            if(suspended[i]){
                int count=0;
                for(int j=0;j<nr;j++){
                    if(request[i][j]<=available[j]){
                        count++;
                    }
                }
                if(count==nr){
                    System.out.println("Process "+(i+1)+" resumes and runs to completion");
                    for(int j=0;j<nr;j++){
                        available[j]+=allocation[i][j];
                    }
                }
                else{
                    System.out.println("Process "+(i+1)+" cannot run to completion");
                }
            }
        }
    }
    private boolean checkEqual() {
        int count=0;
        for(int i=0;i<nr;i++){
            if(resource[i]==available[i]){
                count++;
            }
        }
        if(count==nr){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args) throws Exception{
        new BA().simulate();
    }
}
