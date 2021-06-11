package Entity;

/**
 *
 * @author Firas
 */
public class Delivery {
    
        private int id_delivery;
        private String reference;
        private String client_name;
        private String driver_name	;
        private String address;
        private String statut;
        private int vehicule;

    public Delivery(int id_delivery, String reference, String client_name, String driver_name, String address, String statut, int vehicule) {
        this.id_delivery = id_delivery;
        this.reference = reference;
        this.client_name = client_name;
        this.driver_name = driver_name;
        this.address = address;
        this.statut = statut;
        this.vehicule = vehicule;
    }

    public Delivery(String reference, String client_name, String driver_name, String address, String statut, int vehicule) {
        this.reference = reference;
        this.client_name = client_name;
        this.driver_name = driver_name;
        this.address = address;
        this.statut = statut;
        this.vehicule = vehicule;
    }

    public int getId_delivery() {
        return id_delivery;
    }

    public void setId_delivery(int id_delivery) {
        this.id_delivery = id_delivery;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getVehicule() {
        return vehicule;
    }

    public void setVehicule(int vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Delivery{" + "id_delivery=" + id_delivery + ", reference=" + reference + ", client_name=" + client_name + ", driver_name=" + driver_name + ", address=" + address + ", statut=" + statut + ", vehicule=" + vehicule + '}';
    }
        
        
    
}