package utils;

import enums.BorgColor;
import enums.Language;
import enums.LevelRate;
import enums.BorgRarity;
import structures.LevelProperties;
import structures.Tribe;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.*;

public class BorgUtils {

    private static final int BORG_GROUP_LENGTHS_START_ADDRESS = 0x802c9480;
    private static final int COSTS_START_ADDRESS = 0x802e3764;
    private static final int COLORS_START_ADDRESS = 0x802e3870;
    private static final int LEVEL_PROPERTIES_START_ADDRESS = 0x802f8d38;
    private static final int NO_START_ADDRESS = 0x802f8fc8;
    private static final int BORG_TRIBES_START_ADDRESS = 0x802f91d8;
    private static final int RARITY_START_ADDRESS = 0x802f9cbc;
    private static final int LEVEL_RATE_START_ADDRESS = 0x802f9dc8;
    private static final int BORG_NAMES_START_ADDRESS = 0x80357ca0;
    private static final int STORY_VALUES_START_ADDRESS = 0x8036218c;
    private static final int TRIBE_NAMES_START_ADDRESS = 0x80398674;

    public static Set<Integer> getBorgIds(int groupId) throws IOException {
        ByteBuffer buffer = Utils.seekRaf(BORG_GROUP_LENGTHS_START_ADDRESS + groupId, new byte[1]);
        Set<Integer> borgIds = new HashSet<>();
        for (int i = 0; i < buffer.get(0); i++) {
            borgIds.add((groupId << 8) | i);
        }
        return borgIds;
    }

    public static Set<Integer> getAllBorgIds() throws IOException {
        Set<Integer> borgIds = new HashSet<>();
        for (int i = 0; i <= 0xf; i++) {
            borgIds.addAll(getBorgIds(i));
        }
        return borgIds;
    }

    public static Map<BorgColor, Integer> getCosts(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,COSTS_START_ADDRESS + group * 4, new byte[4]);
        int groupStartAddress = buffer.getInt();
        int count = BorgColor.values().length;
        buffer = Utils.seekRaf(raf,groupStartAddress + slot * 2 * count, new byte[2 * count]);
        Map<BorgColor, Integer> borgCosts = new HashMap<>();
        for (BorgColor borgColor : BorgColor.values()) {
            borgCosts.put(borgColor, (int) buffer.getShort());
        }
        return borgCosts;
    }

    public static Set<BorgColor> getColors(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,COLORS_START_ADDRESS + group * 4, new byte[4]);
        int groupStartAddress = buffer.getInt();
        buffer = Utils.seekRaf(raf, groupStartAddress + slot, new byte[1]);
        Set<BorgColor> borgColors = new HashSet<>();
        for (int i = 0; i < BorgColor.values().length; i++) {
            boolean hasColor = (buffer.get(0) & (1 << i)) != 0;
            if (hasColor) borgColors.add(BorgColor.values()[i]);
        }
        return borgColors;
    }

    public static LevelProperties[] getLevelProperties(int id) throws IOException {
        int levelPropertiesStartAddress = getLevelPropertiesStartAddress(id);
        int size = 18;
        int levelCount = 20;
        byte[] magic = new  byte[size * levelCount];
        LevelProperties[] levelPropertiesArray = new LevelProperties[levelCount];
        RandomAccessFile raf = Utils.getRaf();
        ByteBuffer buffer;
        for (int i = 0; i < levelCount; i++) {
            buffer = Utils.seekRaf(raf, levelPropertiesStartAddress + size * i, magic);
            LevelProperties levelProperties = new LevelProperties();
            levelProperties.setHp(buffer.getShort(0x0));
            levelProperties.setbAmmoStart(buffer.getShort(0x2));
            levelProperties.setbAmmoMax(buffer.getShort(0x4));
            levelProperties.setbAmmoType(buffer.getShort(0x6));
            levelProperties.setbAmmoReload(buffer.getShort(0x8));
            levelProperties.setxAmmoStart(buffer.getShort(0xa));
            levelProperties.setxAmmoMax(buffer.getShort(0xc));
            levelProperties.setxAmmoType(buffer.getShort(0xe));
            levelProperties.setxAmmoReload(buffer.getShort(0x10));
            levelPropertiesArray[i] = levelProperties;
        }
        return levelPropertiesArray;
    }

    public static int getLevelPropertiesStartAddress(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,LEVEL_PROPERTIES_START_ADDRESS + group * 4, new byte[4]);
        int groupStartAddress = buffer.getInt();
        int size = 18;
        int levelCount = 20;
        return groupStartAddress + (slot * size * levelCount);
    }

    public static int getNo(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,NO_START_ADDRESS + group * 4, new byte[4]);
        int groupStartAddress = buffer.getInt();
        buffer = Utils.seekRaf(raf, groupStartAddress + slot * 2, new byte[2]);
        return buffer.getShort();
    }

    public static Tribe getTribe(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,BORG_TRIBES_START_ADDRESS + group * 4, new byte[4]);
        int groupStartAddress = buffer.getInt();
        buffer = Utils.seekRaf(raf, groupStartAddress + slot * 2, new byte[2]);
        return Tribe.getTribe(buffer.getShort());
    }

    public static Map<BorgColor, BorgRarity> getRarities(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,RARITY_START_ADDRESS + group * 4, new byte[4]);
        int groupStartAddress = buffer.getInt();
        buffer = Utils.seekRaf(raf, groupStartAddress + slot * 2 * 6, new byte[2 * 6]);
        Map<BorgColor, BorgRarity> borgRarities = new HashMap<>();
        for (BorgColor borgColor : BorgColor.values()) {
            borgRarities.put(borgColor, BorgRarity.values()[buffer.getShort()]);
        }
        return borgRarities;
    }

    public static LevelRate getLevelRate(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,LEVEL_RATE_START_ADDRESS + group * 4, new byte[4]);
        int groupStartAddress = buffer.getInt();
        buffer = Utils.seekRaf(raf,groupStartAddress + slot, new byte[1]);
        return LevelRate.values()[buffer.get()];
    }

    public static String getBorgNameEng(int id) throws IOException {
        return getBorgName(id, Language.ENGLISH);
    }

    public static String getBorgNameGer(int id) throws IOException {
        return getBorgName(id, Language.GERMAN);
    }

    public static String getBorgNameFre(int id) throws IOException {
        return getBorgName(id, Language.FRENCH);
    }

    public static String getBorgName(int id, Language language) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,BORG_NAMES_START_ADDRESS + (group * 4) + (language.ordinal() * 0x10 * 4), new byte[4]);
        final int groupStartAddress = buffer.getInt();
        buffer = Utils.seekRaf(raf,groupStartAddress + slot * 4, new byte[4]);
        int nameAddress = buffer.getInt();
        StringBuilder name = new StringBuilder();
        while (true) {
            buffer = Utils.seekRaf(raf,nameAddress, new byte[2]);
            if (buffer.getShort(0) == 0) break;
            name.append(new String(buffer.array(), "Shift_JIS"));
            nameAddress += 2;
        }
        return name.toString();
    }

    public static int getDataCrystalCount(int id) throws IOException {
        int storyStartAddress = getStoryStartAddress(id);
        if (storyStartAddress == -1) return 0;
        ByteBuffer buffer = Utils.seekRaf(storyStartAddress + 2, new byte[2]);
        return buffer.getShort();
    }

    public static Map<BorgColor, Integer> getGetValues(int id) throws IOException {
        int storyStartAddress = getStoryStartAddress(id);
        if (storyStartAddress == -1) return null;
        ByteBuffer buffer = Utils.seekRaf(storyStartAddress + 4, new byte[12]);
        Map<BorgColor, Integer> getValues = new HashMap<>();
        for (BorgColor borgColor : BorgColor.values()) {
            getValues.put(borgColor, (int) buffer.getShort());
        }
        return getValues;
    }

    private static int getStoryStartAddress(int id) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int storyStartAddress = STORY_VALUES_START_ADDRESS;
        ByteBuffer buffer;
        while (true) {
            buffer = Utils.seekRaf(raf, storyStartAddress, new byte[2]);
            if (buffer.getShort(0) == -1) return -1;
            if (buffer.getShort(0) == id) break;
            storyStartAddress += 16;
        }
        return storyStartAddress;
    }

    public static String getTribeNameEng(int id) throws IOException {
        return getTribeName(id, Language.ENGLISH);
    }

    public static String getTribeNameGer(int id) throws IOException {
        return getTribeName(id, Language.GERMAN);
    }

    public static String getTribeNameFre(int id) throws IOException {
        return getTribeName(id, Language.FRENCH);
    }

    //TODO: Refactor getName function into a generic function to be used between Tribes and Borgs.
    public static String getTribeName(int id, Language language) throws IOException {
        RandomAccessFile raf = Utils.getRaf();
        int group = (0xf00 & id) >> 8;
        int slot = 0xff & id;
        ByteBuffer buffer = Utils.seekRaf(raf,TRIBE_NAMES_START_ADDRESS + (group * 4) + (language.ordinal() * 0x10 * 4), new byte[4]);
        final int groupStartAddress = buffer.getInt();
        buffer = Utils.seekRaf(raf,groupStartAddress + slot * 4, new byte[4]);
        int nameAddress = buffer.getInt();
        StringBuilder name = new StringBuilder();
        while (true) {
            buffer = Utils.seekRaf(raf,nameAddress, new byte[2]);
            if (buffer.getShort(0) == 0) break;
            name.append(new String(buffer.array(), "Shift_JIS"));
            nameAddress += 2;
        }
        return name.toString();
    }
}