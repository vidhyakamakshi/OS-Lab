import java.io.*;
public class SRT {
	int numProcess;
	int[] arrTime;
	int[] serTime;
	int[] startTime;
	int[] remTime;
	int[] finTime;
	int[] waitingTime;
	int[] turnAroundTime;
	int[] started;
	int[] done;
	int timer;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public SRT() throws NumberFormatException, IOException{
		System.out.println("Enter no of processes");
		numProcess=Integer.parseInt(br.readLine());
		arrTime=new int[numProcess];
		serTime=new int[numProcess];
		startTime=new int[numProcess];
		started=new int[numProcess];
		finTime=new int[numProcess];
		remTime=new int[numProcess];
		waitingTime=new int[numProcess];
		turnAroundTime=new int[numProcess];
		done=new int[numProcess];
	}
	public void getInputs() throws NumberFormatException, IOException {
		System.out.println("Enter arrival time");
		for(int i = 0;i<numProcess;i++){
			arrTime[i] = Integer.parseInt(br.readLine());
			startTime[i]=0;
			started[i]=0;
		}
		System.out.println("Enter service time");
		for(int i = 0;i<numProcess;i++){
			serTime[i] = Integer.parseInt(br.readLine());
			remTime[i]=serTime[i];
			done[i]=0;
		}
	}
	public void display() {
		System.out.println("Process \t Arrival Time \t Service Time \t Start Time \t Finish Time \t Waiting Time \t Turn Around Time");
		for(int i=0;i<numProcess;i++){
			System.out.println(i+1 +"\t"+ arrTime[i] +"\t"+ serTime[i] +"\t"+ startTime[i]+ "\t"+ finTime[i] +"\t"+waitingTime[i] +"\t"+ turnAroundTime[i]);
		}
	}
	public int findSmallest(int range){
		int min=9999;
		int minpos=0;
		for(int i=0;i<range;i++){
			if(done[i]!=1){
				if(remTime[i]<min){
					min=remTime[i];
					minpos=i;
				}
			}
		}
		return minpos;
	}
	public int getRange(int t){
		int count=0;
		for(int i=0;i<numProcess;i++){
			if(arrTime[i]<=t){
				count++;
			}
		}
		return count;
	}
	public int getTotalTime(){
		int totalTime=0;
		for(int i=0;i<numProcess;i++){
			totalTime+=serTime[i];
		}
		return totalTime;
	}
	public void calculate(){
		int totalTime=getTotalTime();
		int range=0;
		int smallpos=0;
		timer=0;
		do{
				range=getRange(timer);
				smallpos=findSmallest(range);
				if(started[smallpos]==0){
						startTime[smallpos]=timer;
						started[smallpos]=1;
				}
				if(remTime[smallpos]==0){
					done[smallpos]=1;
					finTime[smallpos]=timer;
					continue;
				}
				for(int i=0;i<range;i++){
						if(i!=smallpos){
							if(done[i]!=1){
								waitingTime[i]++;
							}
						}
				}
				remTime[smallpos]--;
				timer++;
		}while(timer!=totalTime);
		for(int j=0;j<numProcess;j++){
			//set last completed process to done
			if(done[j]!=1){
					finTime[j]=timer;
					done[j]=1;
			}
			turnAroundTime[j]=waitingTime[j]+serTime[j];
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		SRT s=new SRT();
		s.getInputs();
		s.calculate();
		s.display();
	}
}
