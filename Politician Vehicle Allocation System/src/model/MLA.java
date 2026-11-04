
package model;

public class MLA extends Politician {

    private int assemblyNumber;

    public MLA(int id, String name, String constituency,
               int assemblyNumber) {

        super(id, name, constituency);

        if (assemblyNumber <= 0)
            throw new IllegalArgumentException("Invalid assembly number");

        this.assemblyNumber = assemblyNumber;
    }

    public int getAssemblyNumber() {
        return assemblyNumber;
    }

    @Override
    public String getDesignation() {
        return "MLA";
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Assembly Number: " + assemblyNumber);
    }
}
