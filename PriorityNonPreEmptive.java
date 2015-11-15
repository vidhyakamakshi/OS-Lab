import java.io.*;
class PriorityNPE {
	int numProcess;
	int[] arrTime;
	int[] serTime;
	int[] startTime;
	int[] finTime;
	int[] waitingTime;
	int[] turnAroundTime;
	int[] done;
	int[] priority;
	int timer;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public PriorityNPE() throws NumberFormatException, IOException{
		System.out.println("Enter no of processes");
		numProcess=Integer.parseInt(br.readLine());
		arrTime=new int[numProcess];
		serTime=new int[numProcess];
		startTime=new int[numProcess];
		finTime=new int[numProcess];
		priority=new int[numProcess];
		waitingTime=new int[numProcess];
		turnAroundTime=new int[numProcess];
		done=new int[numProcess];
	}
	public void getInputs() throws NumberFormatException, IOException {
		System.out.println("Enter arrival time");
		for(int i = 0;i<numProcess;i++){
			arrTime[i] = Integer.parseInt(br.readLine());
		}
		System.out.println("Enter service time");
		for(int i = 0;i<numProcess;i++){
			serTime[i] = Integer.parseInt(br.readLine());
			done[i]=0;
		}
		System.out.println("Enter priority");
		for(int i = 0;i<numProcess;i++){
			priority[i] = Integer.parseInt(br.readLine());
		}
	}
	public void display() {
		System.out.println("Process \t Arrival Time \t Service Time \t Priority  \t Start Time \t Finish Time \t Waiting Time \t Turn Around Time");
		for(int i=0;i<numProcess;i++){
			System.out.println(i+1 +"\t"+ arrTime[i] +"\t"+ serTime[i] +"\t"+priority[i]+"\t"+ startTime[i]+ "\t"+ finTime[i] +"\t"+waitingTime[i] +"\t"+ turnAroundTime[i]);
		}
	}
	public int findHighestPriority(int range){
		//small no has high priority
		int min=9999;
		int minpos=0;
		for(int i=0;i<range;i++){
			if(done[i]!=1){
				if(priority[i]<min){
					min=priority[i];
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
		int pos=0;
		timer=0;
		do{
				range=getRange(timer);
				pos=findHighestPriority(range);
				startTime[pos]=timer;
				finTime[pos]=startTime[pos]+serTime[pos];
				waitingTime[pos]=startTime[pos]-arrTime[pos];
				turnAroundTime[pos]=waitingTime[pos]+serTime[pos];
				timer=finTime[pos];
				done[pos]=1;
		}while(timer!=totalTime);	
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityNPE s=new PriorityNPE();
		s.getInputs();
		s.calculate();
		s.display();
	}
}
