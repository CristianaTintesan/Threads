import java.io.File;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int nrClients,nrQueues,simulationTime,arrivalMin,arrivalMax,serviceMin,serviceMax;
		String a,b,cif1,cif2;
		try {
			File myFile = new File("C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment2\\src\\in-test-1.txt"); //primul fisier
			File myFile2 = new File("C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment2\\src\\in-test-2.txt");// al doi-lea fisier
			File myFile3 = new File("C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment2\\src\\in-test-3.txt");// al trei-lea fisier
			Scanner x = new Scanner(myFile);// daca dorim sa rulam datele din fisierul doi punem myFile2; sau din al trei-lea : myFile3
			nrClients = x.nextInt();// Indiferent din ce fisier citim, rezultatele vor fi trecute in acelasi fisier de iesire.
			nrQueues = x.nextInt();
			simulationTime = x.nextInt();
			x.nextLine();
			a = x.nextLine();// Citim linia 4 ca String
			b = x.nextLine();// Citim linia 5 ca String
			cif1 = "";
			cif2 = "";
			int ok = 0;
			int k = 0;
			while (ok == 0 && k < a.length()) { //delimitam minArrival de virgula
				if (a.charAt(k) == ',') {
					ok = 1;
				} else {
					cif1 += a.charAt(k);
				}
				k++;
			}
			while (k < a.length()) { //delimitam maxArrival de virgula
				cif2 += a.charAt(k);
				k++;
			}
			arrivalMin = Integer.parseInt(cif1); // trandformam din string in int
			arrivalMax = Integer.parseInt(cif2);
			
			cif1 = "";
			cif2 = "";
			ok = 0;
			k = 0;
			while (ok == 0 && k < b.length()) { //procedam la fel pentru minService si maxService
				if (b.charAt(k) == ',') {
					ok = 1;
				} else {
					cif1 += b.charAt(k);
				}
				k++;
			}
			while (k < b.length()) {
				cif2 += b.charAt(k);
				k++;
			}

			serviceMin = Integer.parseInt(cif1);
			serviceMax = Integer.parseInt(cif2);

			x.close();
			StoreManager p = new StoreManager(arrivalMin, arrivalMax, serviceMin, serviceMax, nrQueues, simulationTime,
					nrClients);
			p.start(); // pornim thread-ul principal
		} catch (Exception e) {
			System.out.println("Eroare la fisierul de citire\n");
		}

	}
}
