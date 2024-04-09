public class PharmacyOrder {
    protected String medication;
    protected String pharmacyAddress;
    protected String pharmacyZip;
    protected String pharmacyCity;
    protected String pharmacyState;

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    public void setPharmacyCity(String pharmacyCity) {
        this.pharmacyCity = pharmacyCity;
    }

    public void setPharmacyState(String pharmacyState) {
        this.pharmacyState = pharmacyState;
    }

    public void setPharmacyZip(String pharmacyZip) {
        this.pharmacyZip = pharmacyZip;
    }

    public String orderSummary() {
        String order = medication + "\n" + pharmacyAddress + "\n" + pharmacyCity + ", " + pharmacyState + " " + pharmacyZip + "\n";
        return order;
    }
}
