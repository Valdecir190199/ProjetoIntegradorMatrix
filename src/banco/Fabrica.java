package banco;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Fabrica {
	private static EntityManagerFactory fac;
	
	
	public  static EntityManagerFactory get(){
		if (fac == null) {
			fac = Persistence.createEntityManagerFactory("comercialPU");
		}
		return fac;
	}

}