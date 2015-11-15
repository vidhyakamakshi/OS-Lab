import java.io.*;
class LRU {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int fault,nref,nf,frame[],pages[],page,index;
    boolean proceed,wait;
    public LRU() throws Exception{
        System.out.println("Enter no of frames");
        nf=Integer.parseInt(br.readLine());
        frame=new int[nf];
        for(int i=0;i<nf;i++){
            frame[i]=-1;
        }
        System.out.println("Enter no of pages to be referenced");
        nref=Integer.parseInt(br.readLine());
        pages=new int[nref];
        System.out.println("Enter "+nref+" pages to be referenced");
        for(int i=0;i<nref;i++){
            pages[i]=Integer.parseInt(br.readLine());
        }
    }
    public void  simulate(){
        for(int i=0;i<nref;i++){
            page=pages[i];
            proceed=true;
            wait=true;
            for(int j=0;j<nf;j++){
                if(frame[j]==page){
                    proceed=false;
                    wait=false;
                    System.out.println("Page:"+page);
                    display();
                    break;
                }
            }
            if(proceed){
                for(int j=0;j<nf;j++){
                    if(frame[j]==-1){
                        frame[j]=page;
                        wait=false;
                        System.out.println("Page:"+page);
                        display();
                        break;
                    }
                }
            }
            if(wait){
                fault++;
                for(int k=i-1;k>=0;k--){
                    for(int j=0;j<nf;j++){
                        if(frame[j]==pages[k]){
                            index=j;
                        }
                    }
                }
                frame[index]=page;
                System.out.println("Page:"+page);
                display();
            }
        }
        System.out.println("Page fault="+fault);
    }
    private void display() {
        for(int i=0;i<nf;i++){
            System.out.println(frame[i]);
        }
    }
    public static void main(String[] args) throws Exception{
       new LRU().simulate();
    }
}
