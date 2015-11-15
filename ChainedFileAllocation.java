import java.io.*;
class CFA {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int nl,nf,memory[],file[];
     public CFA() throws Exception{
        System.out.println("Enter no of locations");
        nl=Integer.parseInt(br.readLine());
        System.out.println("Enter no of files");
        nf=Integer.parseInt(br.readLine());
        memory=new int[nl];
        file=new int[nf];
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
    }
     public void simulate() throws Exception{
         int block=0;
         for(int i=0;i<nf;i++){
             int limit=block+file[i];
             for(int j=block;j<limit;j++){
                 memory[j]=(i+1);
             }
             block+=file[i];
         }
         display();
     }
    private void display() {
        System.out.println("Location \t File");
        for(int i=0;i<nl;i++){
            if(memory[i]!=0){
                System.out.println((i+1)+"\t"+memory[i]);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new CFA().simulate();
    }
}
