import java.io.*;
class IFA {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int nl,nf,memory[],file[],index[][];
    boolean free[];
    public IFA() throws Exception{
        System.out.println("Enter no of locations");
        nl=Integer.parseInt(br.readLine());
        System.out.println("Enter no of files");
        nf=Integer.parseInt(br.readLine());
        free=new boolean[nl];
        memory=new int[nl];
        file=new int[nf];
        for(int i=0;i<nl;i++){
            free[i]=true;
        }
        boolean valid=false;
        while(!valid){
            int sum=0;
            for(int i=0;i<nf;i++){
                System.out.println("Enter no of blocks required for file "+(i+1));
                file[i]=Integer.parseInt(br.readLine());
                sum+=file[i];
            }
            if(sum<=nl){
                valid=true;
            }
            else{
                System.out.println("Insufficient memory. Request lesser blocks");
            }
        }
        index=new int[nf][];
        for(int i=0;i<nf;i++){
            index[i]=new int[file[i]];
        }
    }
    public void simulate()throws Exception{
        int block=0;
        for(int i=0;i<nf;i++){
            System.out.println("Enter "+file[i]+" blocks for file "+(i+1));
            for(int j=0;j<file[i];j++){
                System.out.println("Enter block no (a no from 1 to "+nl+" )");
                block=Integer.parseInt(br.readLine());
                if(free[block-1]){
                    memory[block-1]=i+1;
                    free[block-1]=false;
                    for(int k=0;k<index[i].length;k++){
                        if(index[i][k]==0){
                            index[i][k]=block;
                            break;
                        }
                    }
                }
                else{
                    System.out.println("Block "+block+" not free");
                    for(int k=0;k<nl;k++){
                        if(memory[k]==(i+1)){
                            memory[k]=0;
                            free[k]=true;
                        }
                    }
                    i--;
                    break;
                }
            }
        }
        display();
    }
    private void display() {
       for(int i=0;i<nf;i++){
           System.out.println("File "+(i+1));
           for(int j=0;j<index[i].length;j++){
               System.out.println(index[i][j]);
           }
       }
    }
    public static void main(String[] args) throws Exception{
        new IFA().simulate();
    }
}
