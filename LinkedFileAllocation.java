package os;
import java.io.*;
import java.util.*;
public class LFA {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    LinkedList<Integer> memory;
    int nl,nf,file[];
    boolean free[];
    public LFA() throws Exception{
        System.out.println("Enter no of locations");
        nl=Integer.parseInt(br.readLine());
        System.out.println("Enter no of files");
        nf=Integer.parseInt(br.readLine());
        file=new int[nf];
        free=new boolean[nl];
        for(int i=0;i<nl;i++){
            free[i]=true;
        }
        memory=new LinkedList<Integer>();
        for(int i=0;i<nf;i++){
            System.out.println("Enter no of blocks for file "+(i+1));
            file[i]=Integer.parseInt(br.readLine());
        }
    }
    public void simulate() throws Exception{
        int block=0;
        for(int i=0;i<nf;i++){
            memory.addLast(-1);
                System.out.println("Enter "+file[i]+" blocks for file "+(i+1));
                for(int j=0;j<file[i];j++){
                        System.out.println("Enter block no ( a no from 1 to "+nl+" )");
                        block=Integer.parseInt(br.readLine());
                        if(free[block-1]){
                            memory.addLast(block);
                            free[block-1]=false;
                        }
                        else{
                            System.out.println("Block "+block+" not free");
                            int k=memory.size()-1;
                            while(memory.get(k)!=-1){
                                free[(int)memory.get(k)-1]=true;
                                memory.remove(k);
                                k--;
                            }
                            memory.remove(k);
                            i--;
                            break;
                        }
                }
        }
        display();
    }
    private void display() {
        int fileid=0;
        for(int i=0;i<memory.size();i++){
            if(memory.get(i)==-1){
                fileid++;
                System.out.println("File "+fileid);
            }
            else{
                System.out.println(memory.get(i));
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new LFA().simulate();
    }
}
