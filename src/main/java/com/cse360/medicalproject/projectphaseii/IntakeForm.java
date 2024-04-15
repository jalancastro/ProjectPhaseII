package com.cse360.medicalproject.projectphaseii;
public class IntakeForm {
    private String weight;
    private String height;
    private String bodyTemp;
    private String bloodPressure;
	private String age;
    private DataAccessObject dao;

    public IntakeForm(String weight, String height, String bodyTemp, String bloodPressure, String age) {
        this.weight = weight;
        this.height = height;
        this.bodyTemp = bodyTemp;
        this.bloodPressure = bloodPressure;
        this.age = age;
    }

   
}
