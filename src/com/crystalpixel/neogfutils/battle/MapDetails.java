package com.crystalpixel.neogfutils.battle;

import com.crystalpixel.neogfutils.battle.entity.Commander;

public class MapDetails {

    private byte mapLocation;
    private byte battleLocation;
    private byte pillarFormation;
    private byte difficulty;
    private short mapScene;
    private byte nameIndex;
    private byte playUnlockCheck;
    private int gfEnergyOverride;
    private byte gRedHint;
    private byte missionObjective;
    private byte allyOption;
    private Commander allyCommander;
    private byte allyIntelligence;
    private Commander opponentCommander1;
    private Commander opponentCommander2;

    public byte getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(byte mapLocation) {
        this.mapLocation = mapLocation;
    }

    public byte getBattleLocation() {
        return battleLocation;
    }

    public void setBattleLocation(byte battleLocation) {
        this.battleLocation = battleLocation;
    }

    public byte getPillarFormation() {
        return pillarFormation;
    }

    public void setPillarFormation(byte pillarFormation) {
        this.pillarFormation = pillarFormation;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(byte difficulty) {
        this.difficulty = difficulty;
    }

    public short getMapScene() {
        return mapScene;
    }

    public void setMapScene(short mapScene) {
        this.mapScene = mapScene;
    }

    public byte getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte nameIndex) {
        this.nameIndex = nameIndex;
    }

    public byte getPlayUnlockCheck() {
        return playUnlockCheck;
    }

    public void setPlayUnlockCheck(byte playUnlockCheck) {
        this.playUnlockCheck = playUnlockCheck;
    }

    public int getGfEnergyOverride() {
        return gfEnergyOverride;
    }

    public void setGfEnergyOverride(int gfEnergyOverride) {
        this.gfEnergyOverride = gfEnergyOverride;
    }

    public byte getgRedHint() {
        return gRedHint;
    }

    public void setgRedHint(byte gRedHint) {
        this.gRedHint = gRedHint;
    }

    public byte getMissionObjective() {
        return missionObjective;
    }

    public void setMissionObjective(byte missionObjective) {
        this.missionObjective = missionObjective;
    }

    public byte getAllyOption() {
        return allyOption;
    }

    public void setAllyOption(byte allyOption) {
        this.allyOption = allyOption;
    }

    public Commander getAllyCommander() {
        return allyCommander;
    }

    public void setAllyCommander(Commander allyCommander) {
        this.allyCommander = allyCommander;
    }

    public byte getAllyIntelligence() {
        return allyIntelligence;
    }

    public void setAllyIntelligence(byte allyIntelligence) {
        this.allyIntelligence = allyIntelligence;
    }

    public Commander getOpponentCommander1() {
        return opponentCommander1;
    }

    public void setOpponentCommander1(Commander opponentCommander1) {
        this.opponentCommander1 = opponentCommander1;
    }

    public Commander getOpponentCommander2() {
        return opponentCommander2;
    }

    public void setOpponentCommander2(Commander opponentCommander2) {
        this.opponentCommander2 = opponentCommander2;
    }
}
