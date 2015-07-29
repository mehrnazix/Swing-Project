package ir.mahan.train.model;

import java.io.Serializable;

public class Person implements Serializable {

	public Person(String name, String occupation, AgeCategory ageCategory,
			EmpCategory empCategory, String taxId, boolean usCitizen,
			Gender gender) {
		super();
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empCategory = empCategory;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
	}
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmpCategory empCategory;
	private String taxId;
	private boolean usCitizen;
	private Gender gender;
}
