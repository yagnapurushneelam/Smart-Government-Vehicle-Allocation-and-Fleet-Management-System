
package model;

public class MP extends Politician {

    private String house;

    public MP(int id, String name, String constituency,
              String house) {

        super(id, name, constituency);

        setHouse(house);
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {

        if (house == null ||
            (!house.equalsIgnoreCase("Lok Sabha") &&
             !house.equalsIgnoreCase("Rajya Sabha"))) {

            throw new IllegalArgumentException(
                "House must be Lok Sabha or Rajya Sabha"
            );
        }

        this.house = house.equalsIgnoreCase("Lok Sabha")
                ? "Lok Sabha" : "Rajya Sabha";
    }

    @Override
    public String getDesignation() {
        return "MP";
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("House: " + house);
    }
}
