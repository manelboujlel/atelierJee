import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Programme {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		
		Personne personne1 = new Personne();
		personne1.setIdPersonne(1);
		personne1.setNom("haddad");
		personne1.setPrenom("ramzi");
		
		em.getTransaction().begin();
		em.merge(personne1);
		em.getTransaction().commit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Voiture v = new Voiture();
		v.setMatricule("CA100");
		v.setMarque("bmw");
		v.setProprietaire(personne1);
		personne1.addVoiture(v);
		Voiture v1 = new Voiture();
		v1.setMatricule("TN200100");
		v1.setMarque("fiat");
		v1.setProprietaire(personne1);
		personne1.addVoiture(v1);

		em.getTransaction().begin();
		em.merge(personne1);
		em.getTransaction().commit();
		
		
		Voiture v2 = new Voiture();
		v2.setMatricule("FR222");
		v2.setMarque("merc");
		v2.setProprietaire(personne1);
		em.getTransaction().begin();
		em.merge(v2);
		em.getTransaction().commit();
		
		
		
		
		List personnes = em.createNamedQuery("Personne.findPersonneByPrenom").setParameter("prenom", "lePrenom2").getResultList();
		System.out.println(personnes);
				
		Query query2 = em.createQuery("select p from Personne p where p.prenom = ?1");
		query2.setParameter(1, "lePrenom2");
		List<Personne> personnes2 = query2.getResultList();
		System.out.println(personnes2);
		
		Query query3 = em.createNativeQuery("select * from Personne p where prenom = ?",Personne.class);
		query3.setParameter(1, "lePrenom2");
		List<Personne> personnes3 = query3.getResultList();
		System.out.println(personnes3);
		
		
		List<Voiture> vRecherchees = em.createQuery("select v from Voiture v where v.marque=?1").setParameter(1, "fiat").getResultList();
		if(!vRecherchees.isEmpty()){
			Voiture voitureFiat = vRecherchees.get(0);
			System.out.println(voitureFiat);
			Personne proprietaireFiat = voitureFiat.getProprietaire();
			System.out.println(proprietaireFiat);
			System.out.println(proprietaireFiat.getVoitures());
		}


	}

}
