
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoreManager extends Thread {
	private int nrQueues;
	private int simTime;
	private int nrClients;
	private static int realTime;
	private int minArrivalTime;
	private int maxArrivalTime;
	private int minServiceTime;
	private int maxServiceTime;

	private List<Client> cList = new ArrayList<Client>();
	private static Queues[] qL;

	public StoreManager(int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int nrQueues, int simTime,
			int nrofClients) {
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minServiceTime = minServiceTime;
		this.maxServiceTime = maxServiceTime;
		this.nrQueues = nrQueues;
		this.simTime = simTime;
		this.nrClients = nrofClients;
		qL = new Queues[this.nrQueues];
		for (int i = 0; i < nrQueues; i++) { // Se creaza cozile
			qL[i] = new Queues(nrQueues);
		}
	}

	public static int getRealTime() {
		return realTime;
	}

	public int findMin() { // pentru a det. indexul cozii cu timpul minim de asteptare
		int index = 0;
		int s1 = 0;
		int suma = Integer.MAX_VALUE;
		for (int i = 0; i < nrQueues; i++) { // parcurgem toate cozile
			if (qL[i].elemLeft() == 0) {
				index = i;
				return index;
			} else {
				s1 = 0;
				for (int k = 0; k < qL[i].getSize(); k++) { // parcurgem toate elem dintr-o coada
					s1 += qL[i].getServ(k);
				}
			}
			if (suma > s1) {
				suma = s1;
				index = i;

			}
		}
		return index;
	}

	public void generateClients() {
		Random random = new Random();
		for (int i = 0; i < nrClients; i++) {
			int randomArrival;
			int randomService;
			randomArrival = random.nextInt((maxArrivalTime - minArrivalTime) + 1) + minArrivalTime; // generarea random a clientilor
			randomService = random.nextInt((maxServiceTime - minServiceTime) + 1) + minServiceTime;
			cList.add(new Client(i + 1, randomArrival, randomService));
		}
		System.out.println("Simulation started");
		for (Queues cQueue : qL) { //pornim cozile
			cQueue.start();//apeland thread-ul secundar
		}
	}

	public void run() {
		generateClients();
		try {
			FileWriter myWriter = new FileWriter("Out-Test1.txt");// se creaza fisierul de iesire
			for (realTime = 0; realTime <= simTime; realTime++) { // parcurgem toate secundele din timpul de simulare
				myWriter.write("Time: " + realTime + "\n" + "Wainting clients: ");
				for (Client c : cList) { // parcurgem lista clientilor
					if (c.getArrivTime() > realTime) {
						myWriter.write("(" + c.getId() + "," + c.getArrivTime() + "," + c.getServTime() + "); ");// afisam clientii care astepta
					}
					if (c.getArrivTime() == realTime) {
						qL[findMin()].addClient(c);//adaugam un client in cea mai optima coada
					}
				}
				myWriter.write("\n");
				for (int j = 0; j < nrQueues; j++) {
					myWriter.write("Queue " + j + ":");
					if (qL[j].elemLeft() == 0) {
						myWriter.write(" closed\n");//dacaq coada este goala afisam mesajul closed
					} else {
						Client a = qL[j].getClient();
						myWriter.write(" (" + a.getId() + "," + a.getArrivTime() + "," + a.getServTime() + ")");// afisam clienti de la coada
						a.setServiceTime(1); //decrementam timpul de servire a primului client din coada
						if (a.getServTime() == 0) {
							qL[j].removeClient(0);// scoatem clientu din coada cand se termina timpul de servire
						}
						for (int k = 1; k < qL[j].getSize(); k++) {
							myWriter.write(" (" + qL[j].getID(k) + "," + qL[j].getArriv(k) + "," + qL[j].getServ(k) + ")");// afisam clientii care nu sunt in fruntea cozii
						}
						myWriter.write("\n");
					}
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			myWriter.close();
			//System.out.println("Finish"); ne anunta cand se terima simularea
		} catch (IOException e1) {

		}

	}

}