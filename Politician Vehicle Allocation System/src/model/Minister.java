
package model;

public class Minister extends Politician {

    private String ministry;

    public Minister(int id, String name, String constituency,
                    String ministry) {

        super(id, name, constituency);

        setMinistry(ministry);
    }

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String ministry) {

        if (ministry == null || ministry.isBlank())
            throw new IllegalArgumentException("Ministry cannot be empty");

        this.ministry = ministry.trim();
    }

    @Override
    public String getDesignation() {
        return "Minister";
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Ministry: " + ministry);
    }
}
