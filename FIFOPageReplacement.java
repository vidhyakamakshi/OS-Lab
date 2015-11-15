import java.io.*;
class FIFO {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int nf,nref,frame[],pages[],fault,page;
    boolean fresh;
    public FIFO() throws Exception{
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
    public void simulate(){
        int q=0;
        for(int i=0;i<nref;i++){
            page=pages[i];
            fresh=true;
            for(int j=0;j<nf;j++){
                if(frame[j]==page){
                    fresh=false;
                    System.out.println("Page:"+page);
                    display();
                    break;
                }
            }
            if(fresh){
                if(q>=nf){
                    q=0;
                }
                if(frame[q]!=-1){
                    fault++;
                }
                    frame[q]=page;
                    q++;
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
        new FIFO().simulate();
    }
}
