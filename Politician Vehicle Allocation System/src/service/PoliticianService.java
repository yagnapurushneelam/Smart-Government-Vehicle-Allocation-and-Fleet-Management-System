
package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import model.Politician;

public class PoliticianService {

    private final Map<Integer, Politician> politicians =
            new HashMap<>();

    public void addPolitician(Politician politician) {

        int id = politician.getPoliticianId();

        if (politicians.containsKey(id)) {
            System.out.println("Politician ID already exists!");
            return;
        }

        politicians.put(id, politician);

        System.out.println("Politician registered successfully!");
    }

    public Politician searchPolitician(int id) {
        return politicians.get(id);
    }

    public boolean exists(int id) {
        return politicians.containsKey(id);
    }

    public Collection<Politician> getAllPoliticians() {
        return politicians.values();
    }

    public void displayPoliticians() {

        if (politicians.isEmpty()) {
            System.out.println("No politicians registered.");
            return;
        }

        for (Politician p : politicians.values()) {

            System.out.println("----------------------");
            p.displayDetails();
        }
    }
}
