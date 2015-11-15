import java.io.*;
public class FCFS {
	int numProcess;
	int[] arrTime;
	int[] serTime;
	int[] startTime;
	int[] finTime;
	int[] waitTime;
	int[] turnAroundTime;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public FCFS() throws NumberFormatException, IOException{
		System.out.println("Enter no of process");
		numProcess=Integer.parseInt(br.readLine());
		arrTime=new int[numProcess];
		serTime=new int[numProcess];
		startTime=new int[numProcess];
		finTime=new int[numProcess];
		waitTime=new int[numProcess];
		turnAroundTime=new int[numProcess];
	}
	public void getInputs() throws NumberFormatException, IOException{
		System.out.println("Enter arrival time");
		for(int i=0;i<numProcess;i++){
			arrTime[i]=Integer.parseInt(br.readLine());
		}
		System.out.println("Enter service time");
		for(int i=0;i<numProcess;i++){
			serTime[i]=Integer.parseInt(br.readLine());
		}
	}
	public void calculate(){
		startTime[0]=arrTime[0];
		for(int i=0;i<(numProcess-1);i++){
			finTime[i]=startTime[i]+serTime[i];
			if(arrTime[i+1]<finTime[i]){
				startTime[i+1]=finTime[i];
			}
			else{
				startTime[i+1]=arrTime[i+1];
			}
			finTime[i+1]=startTime[i+1]+serTime[i+1];
		}
		for(int j=0;j<numProcess;j++){
			waitTime[j]=startTime[j]-arrTime[j];
			turnAroundTime[j]=waitTime[j]+serTime[j];
		}
	}
	public void display(){
		System.out.println("Process \t Arrival time \t Service time \t Start time\t Finish time \t Waiting time\t Turn around time");
		for(int i=0;i<numProcess;i++){
			System.out.println((i+1)+"\t"+arrTime[i]+"\t"+serTime[i]+"\t"+startTime[i]+"\t"+finTime[i]+"\t"+waitTime[i]+"\t"+turnAroundTime[i]);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		FCFS f=new FCFS();
		f.getInputs();
		f.calculate();
		f.display();
	}
}
