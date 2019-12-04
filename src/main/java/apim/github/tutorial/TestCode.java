package apim.github.tutorial;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestCode {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestUnit");
		EntityManager manager = factory.createEntityManager();

		Customer c1 = new Customer(30, "Jerry", 2000);
		Customer c2 = new Customer(40, "Holly", 1800);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(c1);
		manager.persist(c2);
		tx.commit();

		Query query = manager.createQuery("select c from Customer as c");
		List<Customer> list = query.getResultList();
		for (Customer c : list) {
			System.out.println(c.getId() + ", " + c.getName() + ", " + c.getBalance());
		}

		Query querySelected = manager.createQuery("select c.id, c.name from Customer as c");
		List<Object[]> listSelected = querySelected.getResultList();
		for (Object[] arr : listSelected) {
			System.out.println(arr[0] + ", " + arr[1]);
		}

		manager.close();
		factory.close();
	}

}
