import java.io.*;
import java.util.*;
public class MVT {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    LinkedList memory;
    int np,process[],rem,size;
    boolean allAllocated,allocated[];
    public MVT() throws Exception{
        System.out.println("Enter total memory size");
        size=Integer.parseInt(br.readLine());
        rem=size;
        System.out.println("Enter no of process");
        np=Integer.parseInt(br.readLine());
        process=new int[np];
        allocated=new boolean[np];
        boolean valid=false;
        while(!valid){
            int count=0;
            for(int i=0;i<np;i++){
                System.out.println("Enter size of process "+(i+1));
                process[i]=Integer.parseInt(br.readLine());
                if(process[i]<=size){
                    count++;
                }
                else{
                    System.out.println("Memory insufficient. Re-enter proper inputs again");
                    break;
                }
            }
            if(count==np){
                valid=true;
            }
        }
        memory=new LinkedList();
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
                if(process[i]<=rem){
                    rem-=process[i];
                    allocated[i]=true;
                    memory.addLast(new String((i+1)+" "+process[i]));
                }
                else{
                    performRemoval();
                    i--;
                }
            }
        }
    }
    private void display() {
        System.out.println("S.No \t Process ID  Size");
        for(int i=0;i<memory.size();i++){
            System.out.println((i+1)+"\t"+memory.get(i));
        }
    }
    private void performRemoval() throws Exception{
        display();
        System.out.println("Enter sno of process to be deleted");
        int sno=Integer.parseInt(br.readLine());
        String junk=memory.get(sno-1).toString();
        String[] jpinfo=junk.split(" ");
        memory.remove(sno-1);
        int restored=Integer.parseInt(jpinfo[1]);
        rem+=restored;
    }
    public static void main(String[] args) throws Exception{
        new MVT().simulate();
    }
}
