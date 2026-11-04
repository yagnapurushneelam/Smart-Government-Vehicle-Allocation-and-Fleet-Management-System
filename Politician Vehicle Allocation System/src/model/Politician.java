
package model;

public abstract class Politician {

    private final int politicianId;
    private String name;
    private String constituency;

    public Politician(int politicianId, String name, String constituency) {

        if (politicianId <= 0)
            throw new IllegalArgumentException("Invalid politician ID");

        this.politicianId = politicianId;
        setName(name);
        setConstituency(constituency);
    }

    public int getPoliticianId() {
        return politicianId;
    }

    public String getName() {
        return name;
    }

    public String getConstituency() {
        return constituency;
    }

    public abstract String getDesignation();

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be empty");

        this.name = name.trim();
    }

    public void setConstituency(String constituency) {
        if (constituency == null || constituency.isBlank())
            throw new IllegalArgumentException("Constituency cannot be empty");

        this.constituency = constituency.trim();
    }

    public void displayDetails() {

        System.out.println("Politician ID: " + politicianId);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + getDesignation());
        System.out.println("Constituency: " + constituency);
    }
}
