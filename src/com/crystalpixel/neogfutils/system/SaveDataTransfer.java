package com.crystalpixel.neogfutils.system;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.crystalpixel.neogfutils.borg.Borg;
import com.crystalpixel.neogfutils.borg.BorgColor;
import com.crystalpixel.neogfutils.borg.DataCrystalType;
import com.crystalpixel.neogfutils.utils.Utils;

public class SaveDataTransfer {

    public static void main(String[] args) throws IOException {
        SaveDataTransfer main = new SaveDataTransfer();
        List<Borg> boxList = main.getGotchaBoxBorgList(0x8058f660 + 0x870);
        main.makeBoxCode(0x80585dc0 + 0x870, boxList);
        List<Borg> warehouseList = main.getWarehouseBorgList(0x80592660);
        main.makeWarehouseCode(0x80588dc0, warehouseList);
        List<DataCrystal> itemList = main.getDataCrystalList(0x80592660 + 0x7d00);
        main.makeDataCrystalCode(0x80588dc0 + 0x7d00, itemList);
    }

    private int findStartAddress() {
        File file = new File(getClass().getClassLoader().getResource("mem1.raw").getPath());
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(0x3);
            switch (raf.readByte()) {
                case 0x50: return 0x8043f864; //pal
                case 0x45: return 0x80436220; //ntsc
                case 0x4a: return 0x80435340; //jpn
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Borg> getGotchaBoxBorgList(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getDolRaf();
        byte[] magic = new byte[32];
        raf.seek(0xffffff & startAddress);
        raf.readFully(magic);
        ByteBuffer buffer = ByteBuffer.wrap(magic);
        List<Borg> borgList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            int borgId = buffer.getShort(0x0);
            if (borgId > 0) {
                BorgSpecies borgSpecies = BorgSpecies.getBorgSpecies(borgId);
                BorgColor color = BorgColor.values()[buffer.get(0x2)];
                int level = buffer.get(0x3);
                int timeObtained = buffer.getInt(0x4);
                int exp = buffer.getInt(0x8);
                boolean isNew = buffer.get(0xc) == 1;
                Borg borg = new Borg(borgSpecies, color);
                borg.setLevel(level);
                borg.setTimeObtained(timeObtained);
                borg.setExp(exp);
                borg.setNew(isNew);
                borgList.add(borg);
            }
            startAddress += 32;
            raf.seek(0xffffff & startAddress);
            raf.readFully(magic);
        }
        return borgList;
    }

    private List<Borg> getWarehouseBorgList(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getDolRaf();
        byte[] magic = new byte[16];
        raf.seek(0xffffff & startAddress);
        raf.readFully(magic);
        ByteBuffer buffer = ByteBuffer.wrap(magic);
        List<Borg> borgList = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            int borgId = buffer.getShort(0x0);
            if (borgId > 0) {
                BorgSpecies borgSpecies = BorgSpecies.getBorgSpecies(borgId);
                BorgColor color = BorgColor.values()[buffer.get(0x2)];
                int level = buffer.get(0x3);
                int timeObtained = buffer.getInt(0x4);
                int exp = buffer.getInt(0x8);
                boolean isNew = buffer.get(0xc) == 1;
                Borg borg = new Borg(borgSpecies, color);
                borg.setLevel(level);
                borg.setTimeObtained(timeObtained);
                borg.setExp(exp);
                borg.setNew(isNew);
                borgList.add(borg);
            }
            startAddress += 16;
            raf.seek(0xffffff & startAddress);
            raf.readFully(magic);
        }
        return borgList;
    }

    private List<DataCrystal> getDataCrystalList(int startAddress) throws IOException {
        RandomAccessFile raf = Utils.getDolRaf();
        byte[] magic = new byte[12];
        raf.seek(0xffffff & startAddress);
        raf.readFully(magic);
        ByteBuffer buffer = ByteBuffer.wrap(magic);
        List<DataCrystal> dataCrystalList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int borgId = buffer.getShort(0x0);
            if (borgId > 0) {
                BorgSpecies borgSpecies = BorgSpecies.getBorgSpecies(borgId);
                DataCrystalType type = DataCrystalType.values()[buffer.get(0x2)];
                BorgColor color = BorgColor.values()[buffer.get(0x3)];
                int timeObtained = buffer.getInt(0x4);
                boolean isNew = buffer.get(0x8) == 1;
                DataCrystal dataCrystal = new DataCrystal();
                dataCrystal.setSpecies(borgSpecies);
                dataCrystal.setType(type);
                dataCrystal.setColor(color);
                dataCrystal.setTimeObtained(timeObtained);
                dataCrystal.setNew(isNew);
                dataCrystalList.add(dataCrystal);
            }
            startAddress += 12;
            raf.seek(0xffffff & startAddress);
            raf.readFully(magic);
        }
        return dataCrystalList;
    }

    private void makeBoxCode(int startAddress, List<Borg> borgs) {
        int countAddress = startAddress + 0x2172;
        for (Borg borg : borgs) {
            int codeLine1 = borg.getSpecies().getId() << 16;
            codeLine1 = codeLine1 | borg.getColor().ordinal() << 8;
            codeLine1 = codeLine1 | borg.getLevel();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine1));
            startAddress+=4;
            int codeLine2 = borg.getTimeObtained();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine2));
            startAddress+=4;
            int codeLine3 = borg.getExp();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine3));
            startAddress+=4;
            int codeLine4 = (borg.isNew() ? 1 : 0) << 24;
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine4));
            startAddress+=20;
        }
        System.out.println(String.format("%08x %08x", (0x00FFFFFF & countAddress) | 0x2000000, borgs.size()));
    }

    private void makeWarehouseCode(int startAddress, List<Borg> borgs) {
        int countAddress = startAddress + 0xabe0;
        for (Borg borg : borgs) {
            int codeLine1 = borg.getSpecies().getId() << 16;
            codeLine1 = codeLine1 | borg.getColor().ordinal() << 8;
            codeLine1 = codeLine1 | borg.getLevel();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine1));
            startAddress+=4;
            int codeLine2 = borg.getTimeObtained();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine2));
            startAddress+=4;
            int codeLine3 = borg.getExp();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine3));
            startAddress+=4;
            int codeLine4 = (borg.isNew() ? 1 : 0) << 24;
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine4));
            startAddress+=4;
        }
        System.out.println(String.format("%08x %08x", (0x00FFFFFF & countAddress) | 0x2000000, borgs.size()));
    }

    private void makeDataCrystalCode(int startAddress, List<DataCrystal> dataCrystals) {
        int countAddress = startAddress + 0xabe2;
        for (DataCrystal dataCrystal : dataCrystals) {
            int codeLine1 = dataCrystal.getSpecies().getId() << 16;
            codeLine1 = codeLine1 | dataCrystal.getType().ordinal() << 8;
            codeLine1 = codeLine1 | dataCrystal.getColor().ordinal();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine1));
            startAddress+=4;
            int codeLine2 = dataCrystal.getTimeObtained();
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine2));
            startAddress+=4;
            int codeLine3 = (dataCrystal.isNew() ? 1 : 0) << 24;
            System.out.println(String.format("%08x %08x", (0x00FFFFFF & startAddress) | 0x4000000, codeLine3));
            startAddress+=4;
        }
        System.out.println(String.format("%08x %08x", (0x00FFFFFF & countAddress) | 0x2000000, dataCrystals.size()));
    }

    private class BorgBox {

        private Borg borg;

        public ByteBuffer getAsBytes() {
            ByteBuffer buffer = ByteBuffer.allocate(32);
            buffer.putShort(0x0, (short) borg.getSpecies().getId());
            buffer.put(0x2, (byte) borg.getColor().ordinal());
            buffer.put(0x3, (byte) borg.getLevel());
            buffer.putInt(0x4, borg.getTimeObtained());
            buffer.putInt(0x8, borg.getExp());
            buffer.put(0xc, (byte) (borg.isNew() ? 1 : 0));
            return buffer;
        }
    }

    public class DataCrystal {

        private BorgSpecies species;
        private DataCrystalType type;
        private BorgColor color;
        private int timeObtained;
        private boolean isNew;

        public BorgSpecies getSpecies() {
            return species;
        }

        public void setSpecies(BorgSpecies species) {
            this.species = species;
        }

        public DataCrystalType getType() {
            return type;
        }

        public void setType(DataCrystalType type) {
            this.type = type;
        }

        public BorgColor getColor() {
            return color;
        }

        public void setColor(BorgColor color) {
            this.color = color;
        }

        public int getTimeObtained() {
            return timeObtained;
        }

        public void setTimeObtained(int timeObtained) {
            this.timeObtained = timeObtained;
        }

        public boolean isNew() {
            return isNew;
        }

        public void setNew(boolean aNew) {
            isNew = aNew;
        }

//        public ByteBuffer getAsBytes() {
//            ByteBuffer buffer = ByteBuffer.allocate(16);
//            buffer.putShort(0x0, (short) borg.getSpecies().getId());
//            buffer.put(0x2, (byte) borg.getColor().ordinal());
//            buffer.put(0x3, (byte) borg.getLevel());
//            buffer.putInt(0x4, borg.getTimeObtained());
//            buffer.putInt(0x8, borg.getExp());
//            buffer.put(0xc, (byte) (borg.isNew() ? 1 : 0));
//            return buffer;
//        }
    }
}
