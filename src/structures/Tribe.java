package structures;

import utils.BorgUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tribe {

    private int id;
    private String name;

    private static Map<Integer, Tribe> tribeMap = new HashMap<>();

    private Tribe(int id) {
        this.id = id;
    }

    public static Tribe getTribe(int id) {
        if (!tribeMap.containsKey(id)) {
            tribeMap.put(id, new Tribe(id));
        }
        return tribeMap.get(id);
    }

    public static Map<Integer, Tribe> getAllTribes() throws IOException {
        for (int i = 0; i <= 0x13;  i++) {
            if (!tribeMap.containsKey(i)) getTribe(i);
        }
        return tribeMap;
    }

    public int getId() {
        return id;
    }

    public String getName() throws IOException {
        if (name == null) {
            name = BorgUtils.getTribeNameEng(id);
        }
        return name;
    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (IOException e) {
            return null;
        }
    }
}
