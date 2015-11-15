import java.io.*;
import java.util.*;
public class MFT{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    LinkedList memory=new LinkedList();
    int tsize,psize,np,ns,process[],vacant;
    boolean allAllocated,allocated[];
    public MFT2() throws Exception{
        System.out.println("Enter total size");
        tsize=Integer.parseInt(br.readLine());
        while(true){
            System.out.println("Enter no of partitions");
            ns=Integer.parseInt(br.readLine());
            if((tsize%ns)==0){
                break;
            }
            else{
                System.out.println("Cannot partition memory into "+ns+" equal slots");
            }
        }
        vacant=ns;
        psize=tsize/ns;
        System.out.println("Enter no of processes");
        np=Integer.parseInt(br.readLine());
        process=new int[np];
        allocated=new boolean[np];
        boolean valid=false;
        while(!valid){
            int count=0;
            for(int i=0;i<np;i++){
                System.out.println("Enter size of process "+(i+1));
                process[i]=Integer.parseInt(br.readLine());
                if(process[i]<=tsize){
                    count++;
                }
                else{
                    System.out.println("Memory insufficient");
                    break;
                }
            }
            if(count==np){
                valid=true;
            }
        }
    }
    public void simulate() throws Exception{
        allAllocated=false;
        while(!allAllocated){
            allocate();
            int count=0;
            for(int i=0;i<np;i++){
                if(allocated[i]){
                    count++;
                }
            }
            if(count==np){
                allAllocated=true;
            }
        }
        display();
        System.out.println("End");
    }
    private void allocate() throws Exception{
        for(int i=0;i<np;i++){
            if(!allocated[i]){
                if(process[i]<=psize){
                    if(vacant!=0){
                        memory.addLast(i+1);
                        vacant--;
                        allocated[i]=true;
                    }
                    else{
                        performRemoval();
                        i--;
                    }
                }
                else{
                    int sr=0;
                    if((process[i]%psize)==0){
                        sr=process[i]/psize;
                    }
                    else{
                        sr=(process[i]/psize)+1;
                    }
                    if(sr<=vacant){
                        for(int j=1;j<=sr;j++){
                            memory.addLast(i+1);
                            allocated[i]=true;
                            vacant--;
                        }
                    }
                    else{
                        performRemoval();
                        i--;
                    }
                }
            }
        }
    }
    private void display() {
        System.out.println("Slot \t Process ID");
        for(int i=0;i<memory.size();i++){
            System.out.println((i+1)+"\t"+memory.get(i));
        }
    }
    private void performRemoval() throws Exception{
        int id=1;
        display();
        System.out.println("Memory insufficient Select process id to be deleted");
        id=Integer.parseInt(br.readLine());
        for(int i=0;i<memory.size();i++){
            if((int)memory.get(i)==id){
                memory.remove(i);
                i--;
                vacant++;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new MFT2().simulate();
    }
}
