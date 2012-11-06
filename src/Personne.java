import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQuery(
		name="Personne.findPersonneByPrenom",
	    query="SELECT p FROM Personne p WHERE p.prenom = :prenom"
		)
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPersonne;
	private String nom;
	private String prenom;
	
	@OneToMany(cascade=CascadeType.MERGE)
	private List<Voiture> voitures;
	
	public long getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(long idPersonne) {
		this.idPersonne = idPersonne;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", "
				+ (nom != null ? "nom=" + nom + ", " : "")
				+ (prenom != null ? "prenom=" + prenom : "") + "]";
	}
	public List<Voiture> getVoitures() {
		return voitures;
	}
	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}
	public void addVoiture(Voiture voiture) {
		if(voitures==null){
			voitures = new ArrayList<Voiture>();
		}
		if(!voitures.contains(voiture)){
			this.voitures.add(voiture);			
			voiture.setProprietaire(this);
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPersonne ^ (idPersonne >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (idPersonne != other.idPersonne)
			return false;
		return true;
	}
	
	
}
