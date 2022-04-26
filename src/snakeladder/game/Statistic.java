package snakeladder.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

// Tag for change 5
public class Statistic <K>{

    private HashMap<K,Integer> stats;

    public Statistic(List<K> statIndex) {
        this.stats = new HashMap<K, Integer>();
        for(int i = 0; i < statIndex.size(); i ++){
            stats.put(statIndex.get(i), 0);

        }
    }

    public void addRStats(K key){
        var check = stats.get(key);
        stats.put(key,check += 1);

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
