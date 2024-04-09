public class IntakeForm {
    private int weight;
    private String height;
    private String bodyTemp;
    private String bloodPressure;

    public IntakeForm(int weight, String height, String bodyTemp, String bloodPressure) {
        this.weight = weight;
        this.height = height;
        this.bodyTemp = bodyTemp;
        this.bloodPressure = bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setBodyTemp(String bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getBodyTemp() {
        return bodyTemp;
    }

    public String getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
}
