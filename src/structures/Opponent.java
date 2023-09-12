package structures;

import enums.Commander;
import javafx.geometry.Point3D;
import system.BorgSpecies;
import utils.BorgListUtils;
import utils.Utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class Opponent {

    private Commander commander;
    private int difficulty;
    private int borgListId;
    private int level;
    private Point3D coordinates;
    private int entrance;

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

    public Point3D getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point3D coordinates) {
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
