package system;

import enums.BorgColor;
import structures.Tribe;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

public class GetCounterParser {

    public static void main(String[] args) throws IOException {
        GetCounterParser main = new GetCounterParser();
        main.createWatchFile(main.buildGetCounterList(main.findStartAddress()));
    }

    private int findStartAddress() {
        File file = new File(getClass().getClassLoader().getResource("save.gci").getPath());
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(0x3);
            switch (raf.readByte()) {
                case 0x50: return 0x8119ef78;
                case 0x45: return 0x811956d8;
                case 0x4A: return 0x81194878;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<BorgThing> buildGetCounterList(int startAddress) {
        List<BorgThing> borgThingCounterList = new ArrayList<>();
        File file = new File(getClass().getClassLoader().getResource("save.gci").getPath());
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            byte[] magic = new byte[4];
            raf.seek(0x1039c);
            while (true) {
                raf.readFully(magic);
                if (magic[0] == 0) break;
                ByteBuffer byteBuffer = ByteBuffer.wrap(magic);
                int borg = ~byteBuffer.getShort(0) & 0x0FFF;
                BorgColor colorEnum = BorgColor.values()[(~byteBuffer.get(0) & 0xF0) >> 4];
                int count = ~byteBuffer.getShort(2);
                borgThingCounterList.add(new BorgThing(BorgSpecies.getBorgSpecies(borg), colorEnum, count, startAddress + 2));
                startAddress += 4;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return borgThingCounterList.stream().sorted(Comparator.comparing(BorgThing::getColor)).collect(Collectors.toList());
    }

    private void createWatchFile(List<BorgThing> getCounterList) {
        try {
            StringBuilder watchList = new StringBuilder("{\"watchList\": [");
            for (Tribe tribe : Tribe.getAllTribes().values()) {
                List<BorgSpecies> tribeBorgSpecies = BorgSpecies.getAllBorgSpecies().values().stream().filter(b -> {
                    try {
                        return b.getTribe() == tribe;
                    } catch (IOException e) {
                        return false;
                    }
                }).sorted().collect(Collectors.toList());
                List<BorgThing> tribeBorgThings = getCounterList.stream().filter(b -> {
                    try {
                        return b.getSpecies().getTribe() == tribe;
                    } catch (IOException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
                if (tribeBorgThings.isEmpty()) {
                    continue;
                }
                StringBuilder tribeGroupEntries = new StringBuilder("{\"groupEntries\": [");
                for (BorgSpecies borgSpecies : tribeBorgSpecies) {
                    List<BorgThing> borgThingSpeciesCounters = getCounterList.stream().filter(b -> b.getSpecies() == borgSpecies).collect(Collectors.toList());
                    if (borgThingSpeciesCounters.isEmpty()) {
                        continue;
                    }
                    StringBuilder borgGroupEntries = new StringBuilder("\"groupEntries\": [");
                    for (BorgThing borgThing : borgThingSpeciesCounters) {
                        String addressLine = "\"address\": \"" + Integer.toHexString(borgThing.getMemoryAddress()) + "\",";
                        String baseIndexLine = "\"baseIndex\": 0,";
                        String labelLine = "\"label\": \"" + borgThing.getColor().getName() + "\",";
                        String typeIndexLine = "\"typeIndex\": 1,";
                        String unsignedLine = "\"unsigned\": false";
                        String entry = "{" + addressLine + baseIndexLine + labelLine + typeIndexLine + unsignedLine + "},";
                        borgGroupEntries.append(entry);
                    }
                    borgGroupEntries.deleteCharAt(borgGroupEntries.length() - 1);
                    borgGroupEntries.append("],\"groupName\": \"").append(borgSpecies.getName()).append("\"");
                    tribeGroupEntries.append("{");
                    tribeGroupEntries.append(borgGroupEntries);
                    tribeGroupEntries.append("},");
                }
                tribeGroupEntries.deleteCharAt(tribeGroupEntries.length() - 1);
                tribeGroupEntries.append("],\"groupName\": \"").append(tribe.getName()).append(" Borg\"},");
                watchList.append(tribeGroupEntries);
            }
            watchList.deleteCharAt(watchList.length() - 1);
            watchList.append("]}");

            FileWriter writer = new FileWriter("Gotcha Force GET Counters.dmw");
            writer.write(watchList.toString());
            writer.close();
        }
        catch (IOException e) {
            System.err.println("An Error has occurred. Unable to create watch file.");
            e.printStackTrace();
        }
    }
}