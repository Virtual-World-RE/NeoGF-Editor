package com.crystalpixel.neogfutils.battle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import com.crystalpixel.neogfutils.game.entity.Position;
import com.crystalpixel.neogfutils.system.BorgSpecies;
import com.crystalpixel.neogfutils.utils.Utils;
import com.crystalpixel.neogfutils.utils.borg.BorgListUtils;

public class Opponent {

    private Commander commander;
    private int difficulty;
    private int borgListId;
    private int level;
    private Position coordinates;
    private int entrance;

    public Opponent(Commander commander, int difficulty, int borgListId, int level, Position coordinates, int entrance) {
        this.commander = commander;
        this.difficulty = difficulty;
        this.borgListId = borgListId;
        this.level = level;
        this.coordinates = coordinates;
        this.entrance = entrance;
    }    

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getBorgListId() {
        return borgListId;
    }

    public void setBorgListId(int borgListId) {
        this.borgListId = borgListId;
    }

    public List<BorgSpecies> getBorgList() throws IOException {
        return BorgListUtils.getOpponentBorgList(borgListId);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Position getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Position coordinates) {
        this.coordinates = coordinates;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public String getDetails(boolean simple) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("Commander: ").append(getCommander().getName()).append("\n");
        builder.append(String.format("Force: %s ", Utils.getAsHexString(getBorgListId())))
                .append(BorgListUtils.getBorgListAsString(getBorgList())).append("\n");
        if (!simple) {
            builder.append("Difficulty: ").append(getDifficulty()).append("\n");
            builder.append("Level: ").append(getLevel()).append("\n");
            builder.append("Entrance: ").append(Utils.getAsHexString(getEntrance())).append("\n");
            builder.append("X co-ordinate: ").append(getCoordinates().getX()).append("\n");
            builder.append("Y co-ordinate: ").append(getCoordinates().getY()).append("\n");
            builder.append("Z co-ordinate: ").append(getCoordinates().getZ()).append("\n");
        }
        return builder.toString();
    }

    public byte[] getAsBytes() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        byteBuffer.put((byte) this.commander.ordinal());
        byteBuffer.put((byte) this.difficulty);
        byteBuffer.put((byte) this.borgListId);
        byteBuffer.put((byte) this.level);
        byteBuffer.putFloat((float) this.coordinates.getX());
        byteBuffer.putFloat((float) this.coordinates.getY());
        byteBuffer.putFloat((float) this.coordinates.getZ());
        byteBuffer.put((byte) this.entrance);
        return byteBuffer.array();
    }
}
