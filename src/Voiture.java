import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Voiture {

	@Id
	private String matricule;
	private String marque;
	@OneToOne(cascade=CascadeType.MERGE)
	private Personne proprietaire;
	 
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public Personne getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Personne proprietaire) {
		this.proprietaire = proprietaire;
		proprietaire.addVoiture(this);
	}
	@Override
	public String toString() {
		return "Voiture ["
				+ (matricule != null ? "matricule=" + matricule + ", " : "")
				+ (marque != null ? "marque=" + marque + ", " : "")
				+ (proprietaire != null ? "proprietaire=" + proprietaire : "")
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricule == null) ? 0 : matricule.hashCode());
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
		Voiture other = (Voiture) obj;
		if (matricule == null) {
			if (other.matricule != null)
				return false;
		} else if (!matricule.equals(other.matricule))
			return false;
		return true;
	}
	
}
