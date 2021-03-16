
public class Client {
	private int idClient;
	private int arrivalTime;
	private int serviceTime;

	public Client(int idClient, int arrivalTime, int serviceTime) {
		setIdClient(idClient);
		setArrivalTime(arrivalTime);
		this.serviceTime=serviceTime;
	}

	public int getId() {
		return idClient;
	}

	public int getArrivTime() {
		return arrivalTime;
	}

	public int getServTime() {
		return serviceTime;
	}
	
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime=arrivalTime;
	}
	
	public void setServiceTime(int serviceTime) {
		this.serviceTime=this.serviceTime-serviceTime;
	}
	
	public void setIdClient(int idClient) {
		this.idClient=idClient;
	}
}

