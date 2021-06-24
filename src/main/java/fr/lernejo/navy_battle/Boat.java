package fr.lernejo.navy_battle;

import java.util.List;

public class Boat
{
    private final List<String> CasesList;
    private final String boatName;

    public Boat(List<String> casesList, String boatName) {
        this.CasesList = casesList;
        this.boatName = boatName;
    }

    public String getBoatName() {
        return boatName;
    }


    public List<String> getCasesList() {
        return CasesList;
    }

    public void addToList(String cell) {
        this.CasesList.add(cell);
    }
}
