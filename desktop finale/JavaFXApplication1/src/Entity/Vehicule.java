package Entity;

/**
 *
 * @author Firas
 */
public class Vehicule {
    private int id_vehicule;
    private String matricule ;
    private float weight;
    private String etat;
    private String marque;
    private String description;

    public Vehicule(int id_vehicule, String matricule, float weight, String etat, String marque, String description) {
        this.id_vehicule = id_vehicule;
        this.matricule = matricule;
        this.weight = weight;
        this.etat = etat;
        this.marque = marque;
        this.description = description;
    }

    public Vehicule(String matricule, float weight, String etat, String marque, String description) {
        this.matricule = matricule;
        this.weight = weight;
        this.etat = etat;
        this.marque = marque;
        this.description = description;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
      //  return "Vehicule{" + "id_vehicule=" + id_vehicule + ", matricule=" + matricule + ", weight=" + weight + ", etat=" + etat + ", marque=" + marque + ", description=" + description + '}';
   
    return matricule+" "+marque;
    }
    
    
    
}