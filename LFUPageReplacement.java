import java.io.*;
class LFU {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int np,nf,nref,fault,page,count[],pages[],frame[];
    boolean fresh,allocated[];
    public  LFU() throws Exception{
        boolean valid=false;
        System.out.println("Enter no of frames");
        nf=Integer.parseInt(br.readLine());
        frame=new int[nf];
        for(int i=0;i<nf;i++){
            frame[i]=-1;
        }
        System.out.println("Enter no of pages");
        np=Integer.parseInt(br.readLine());
        allocated=new boolean[np];
        count=new int[np];
        System.out.println("Enter no of references");
        nref=Integer.parseInt(br.readLine());
        System.out.println("Enter "+nref+" pages to be referenced (any no from  0 to "+(np-1)+" )");
        pages=new int[nref];
        while(!valid){
            int cntr=0;
            for(int i=0;i<nref;i++){
                pages[i]=Integer.parseInt(br.readLine());
                if((pages[i]>=0)&&(pages[i]<np)){
                    cntr++;
                }
            }
            if(cntr==nref){
                valid=true;
            }
            else{
                System.out.println("Invalid input");
                System.out.println("Enter "+nref+" pages to be referenced (any no from  0 to "+(np-1)+" )");
            }
        }  
    }
    public void simulate(){
        int k=0;
        for(int i=0;i<nref;i++){
            page=pages[i];
            fresh=true;
            for(int j=0;j<nf;j++){
                if(frame[j]==page){
                    fresh=false;
                    count[page]++;
                    break;
                }  
            }
            if(fresh){
                if(k>=nf){
                    k=recycle();
                    i--;
                }
                else{
                    if(frame[k]==-1){
                        frame[k]=page;
                        allocated[page]=true;
                        count[page]++;
                        k++;
                        System.out.println("Page:"+page); 
                        display();
                    }
                    else{
                        k=recycle();
                        i--;
                    }
                }
            }
            else{
                System.out.println("Page:"+page); 
                display();
            } 
        }
        System.out.println("At the end");
        System.out.println("Page:"+page); 
        display();
        System.out.println("Page fault="+fault);
    }
    private int recycle() {
        fault++;
        int min=getMinUsed();
        allocated[frame[min]]=false;
        frame[min]=-1;
        return min;
    }
    private void display() {
        for(int i=0;i<nf;i++){
            System.out.println(frame[i]);
        }
    }
    private int getMinUsed() {
        int min=9999;
        int minpos=0;
        for(int i=0;i<nf;i++){
            if(count[frame[i]]<min){
                min=count[frame[i]];
                minpos=i;
            }
        }
        return minpos;
    }
    public static void main(String[] args) throws Exception{
        new LFU().simulate();
    }
}
