package snakeladder.game;

import java.util.HashMap;
import java.util.Hashtable;

// Tag for change 5
public class Statistic <K>{

    private HashMap<K,Integer> stats;

    public Statistic(HashMap<K, Integer> stats) {
        this.stats = stats;
    }

    public void addRStats(K key){
        var check = stats.get(key);
        if (check == null){
            stats.put(key, 1);
        }
        else {
            stats.put(key,check += 1);
        }
    }

    public String getStats(String playerName, String statName) {
        java.lang.String msg = "";
        msg += playerName + " " +statName + ": ";
        for (K key: stats.keySet()) {
            msg += key + "-";
            msg += stats.get(key) + ", ";
        }
        msg = msg.substring(0,msg.lastIndexOf(","));
        return msg;
    }
}
