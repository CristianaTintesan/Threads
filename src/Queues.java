
import java.util.LinkedList;


public class Queues extends Thread {
	private LinkedList<Client> clientQueue = new LinkedList<Client>();
	private int timpService;

	public Queues(int timeService) {
		this.timpService = timeService;
	}
	
	public void setTimeWaiting() {
		this.timpService=this.timpService-1;
	}

	public int getSize() {
		return clientQueue.size();
	}

	public void addClient(Client client) {// adauga un client in coada
		clientQueue.add(client);
	}

	public void removeClient(int i) {// sterge clientul din coada aflat pe pozitia i
		clientQueue.remove();
	}
	public Client getClient() { //returneaza primul client din coada
		Client a=clientQueue.getFirst();
		return a;
	}

	public void run() {// thread-ul secundar
		while (true) {
			if (clientQueue.isEmpty() == false) {
				try {
					sleep(1000);

				} catch (InterruptedException e) {
					System.out.println("Interrupted");
				} finally {

				}
			} else {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Interrupted");
				}
			}
		}
	}

	public int elemLeft() {// returneaza cate elemente mai sunt in coada
		return clientQueue.size();
	}

	public int getID(int i) { //Pentru a obtine id-ul unui client din coada
		return clientQueue.get(i).getId();
	}

	public int getArriv(int i) { //Pentru a obtine timpul de sosire a unui client din coada
		return clientQueue.get(i).getArrivTime();
	}

	public int getServ(int i) { //Pentru a obtine timpul de servire a unui client din coada
		return clientQueue.get(i).getServTime();
	}
   
}
