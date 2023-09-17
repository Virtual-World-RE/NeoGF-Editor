package com.crystalpixel.neogfutils.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.crystalpixel.neogfutils.borg.BorgColor;
import com.crystalpixel.neogfutils.borg.BorgLevelStats;
import com.crystalpixel.neogfutils.borg.BorgRarity;
import com.crystalpixel.neogfutils.borg.LevelRate;
import com.crystalpixel.neogfutils.borg.Tribe;
import com.crystalpixel.neogfutils.utils.borg.BorgUtils;

public class BorgSpecies {

    private int id;
    private Integer no;
    private String name;
    private Integer dataCrystalCount;
    private LevelRate levelRate;
    private Tribe tribe;
    private Map<BorgColor, Integer> costs;
    private BorgLevelStats[] allBorgLevelStats;
    private Set<BorgColor> colors;
    private Map<BorgColor, BorgRarity> rarities;
    private Map<BorgColor, Integer> getValues;

    private static Map<Integer, BorgSpecies> borgSpeciesMap = new HashMap<>();

    private BorgSpecies(int id) {
        this.id = id;
    }

    public static BorgSpecies getBorgSpecies(int id) {
        if (!borgSpeciesMap.containsKey(id)) {
            borgSpeciesMap.put(id, new BorgSpecies(id));
        }
        return borgSpeciesMap.get(id);
    }

    public static Map<Integer, BorgSpecies> getAllBorgSpecies() throws IOException {
        Set<Integer> borgIds = BorgUtils.getAllBorgIds();
        for (int id : borgIds) {
            if (!borgSpeciesMap.containsKey(id)) getBorgSpecies(id);
        }
        return borgSpeciesMap;
    }

    public int getId() {
        return id;
    }

    public Integer getNo() throws IOException {
        if (no == null) {
            no = BorgUtils.getNo(id);
        }
        return no;
    }

    public String getName() {
        if (name == null) {
            try {
                name = BorgUtils.getBorgNameEng(id);
            } catch (IOException e) {
                return null;
            }
        }
        return name;
    }

    public static String toASCII(String chars) {
        String ascii = "";
        for(int i = 0, l = chars.length(); i < l; i++) {
            char c = chars.charAt(i);

            c = (char) (0xFF & (c + 0x20));

            ascii += c;
        }

        return ascii;
    }

    public String getNameFormatted() {
        String[] words = toASCII(getName()).split("\\s");
        StringBuilder formattedName = new StringBuilder();
        for(String w : words) {
            if (w.startsWith("II") || w.startsWith("IV")) {
                formattedName.append(w);
                break;
            }
            formattedName.append(w.substring(0,1).toUpperCase()).append(w.substring(1).toLowerCase()).append(" ");
        }
        return formattedName.toString().trim();
    }

    public Integer getDataCrystalCount() throws IOException {
        if (dataCrystalCount == null) {
            dataCrystalCount = BorgUtils.getDataCrystalCount(id);
        }
        return dataCrystalCount;
    }

    public LevelRate getLevelRate() throws IOException {
        if (levelRate == null) {
            levelRate = BorgUtils.getLevelRate(id);
        }
        return levelRate;
    }

    public Tribe getTribe() throws IOException {
        if (tribe == null) {
            tribe = BorgUtils.getTribe(id);
        }
        return tribe;
    }

    public int getCost() {
        try {
            return getCosts().get(BorgColor.N);
        }
        catch (IOException e) {
            return 0;
        }
    }

    public Map<BorgColor, Integer> getCosts() throws IOException {
        if (costs == null) {
            costs = BorgUtils.getCosts(id);
        }
        return costs;
    }

    public BorgLevelStats[] getAllBorgLevelStats() throws IOException {
        if (allBorgLevelStats == null) {
            allBorgLevelStats = BorgUtils.getBorgLevelStats(id);
        }
        return allBorgLevelStats;
    }

    public Set<BorgColor> getColors() throws IOException {
        if (colors == null) {
            colors = BorgUtils.getColors(id);
        }
        return colors;
    }

    public Map<BorgColor, BorgRarity> getRarities() throws IOException {
        if (rarities == null) {
            rarities = BorgUtils.getRarities(id);
        }
        return rarities;
    }

    public Map<BorgColor, Integer> getGetValues() throws IOException {
        if (getValues == null) {
            getValues = BorgUtils.getGetValues(id);
        }
        return getValues;
    }

    public boolean hasColor(BorgColor color) throws IOException {
        return getColors().contains(color);
    }

    public BorgSpecies loadDetails() throws IOException {
        getNo();
        getName();
        getDataCrystalCount();
        getLevelRate();
        getTribe();
        getCosts();
        getAllBorgLevelStats();
        getColors();
        getRarities();
        getGetValues();
        return this;
    }

    @Override
    public String toString() {
        return getNameFormatted();
    }
}
