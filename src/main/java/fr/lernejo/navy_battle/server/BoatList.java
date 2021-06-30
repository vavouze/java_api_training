package fr.lernejo.navy_battle.server;

import fr.lernejo.navy_battle.Boat;

import java.util.*;

public class BoatList
{
    private final HashMap<Boat,Integer> BoatList;
    private final String[] column = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final String[] line = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public BoatList(HashMap<Boat, Integer> boatList) {
        BoatList = boatList;
    }
    public HashMap<Boat, Integer> getBoatList() {
        return BoatList;
    }
    public void initiateAllboats() {
        for(Map.Entry<Boat, Integer> boat : this.BoatList.entrySet()) {
            boolean free;
            String randomColumn,randomLine = "";
            do {
                randomColumn = getRandom(this.column);
                randomLine = getRandom(this.line);
                free = isFree(randomColumn,randomLine);
            }while (!free);
            boolean test = false;
            for (Direction direction:Direction.values()) {
                test = setTrailingCases(randomColumn,randomLine,boat,direction);
                if (test){break;}
            }
            if (!test){initiateAllboats();}
            }}
    public boolean setTrailingCases(String column, String line, Map.Entry<Boat, Integer> boat, Direction direction) {
        if (direction == Direction.HAUT || direction == Direction.BAS) {
            List<String> cells = addCellToBoat(this.column,Arrays.asList(this.column).indexOf(column),boat.getValue(),direction.coef,column,line,true);
            if (cells.isEmpty()){return false;}else {
                for (String cell: cells) {boat.getKey().addToList(cell);}
            }
        }else {
            List<String> cells = addCellToBoat(this.line,Arrays.asList(this.line).indexOf(line),boat.getValue(),direction.coef,column,line,false);
            if (cells.isEmpty()){return false;}else {
                boat.getKey().addToList(column+line);
                for (String cell: cells) {boat.getKey().addToList(cell);}
            }
        }
        return true;
    }
    public List<String> addCellToBoat(String[] arr,int start,int length,int coef,String column,String line,boolean flag){
        List<String> tmp = new ArrayList<>();
        for (int i = start; i != (start + ((length) *coef)) ; i+= coef){
            if (flag && isInBounds(start,length,coef) && isFree(arr[i],line)) {
                tmp.add(arr[i]+line);
            }else if(!flag && isInBounds(start,length,coef) && isFree(column,arr[i])){
                tmp.add(column+arr[i]);
            }else {return new ArrayList<>();}
        }
        return tmp;
    }
    public boolean isInBounds(int start, int length,int op) {
        if (op > 0){return start + length < 10;}else {return start - length > -1;}

    }
    public String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    public boolean isFree(String column,String line) {
        boolean toReturn = true;
        for(Map.Entry<Boat, Integer> boat : this.BoatList.entrySet()) {

            if (boat.getKey().getCasesList().contains(column+line)) {
                toReturn = false;
                break;
            }
            else if (!Arrays.asList(this.column).contains(column) || !Arrays.asList(this.line).contains(line)) {
                toReturn = false;
                break;
            }
        }
        return toReturn;
    }
}
