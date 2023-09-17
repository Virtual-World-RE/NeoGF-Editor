package com.crystalpixel.neogfutils.borg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.crystalpixel.neogfutils.utils.borg.BorgUtils;

public class Tribe {

    private int id;
    private String name;
    private static Map<Integer, Tribe> tribeMap = new HashMap<>();

    private Tribe(int id) {
        this.id = id;
    }

    public static Tribe getTribe(int id) {
        return tribeMap.computeIfAbsent(id, Tribe::new);
    }

    public static Map<Integer, Tribe> getAllTribes() throws IOException {
        for (int i = 0; i <= 0x13; i++) {
            getTribe(i);
        }
        return tribeMap;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        if (name == null) {
            try {
                name = BorgUtils.getTribeNameEng(id);
            } catch (IOException e) {
                name = "Unknown";
            }
        }
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
