package com.crystalpixel.neogfutils.system;

import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

import com.crystalpixel.neogfutils.battle.Opponent;

public class BattleConfiguration {

    private List<Opponent> opponents = new ArrayList<>();
    private Point3D playerCoordinates;
    private Point3D allyCoordinates;
    private int playerEntrance;
    private int allyEntrance;
    private int timer;
    private int battleOptions;
    private int greenScore;
    private int redScore;
    private int greenHandicap;
    private int redHandicap;
    private int music;

    public List<Opponent> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Opponent> opponents) {
        this.opponents = opponents;
    }

    public Point3D getPlayerCoordinates() {
        return playerCoordinates;
    }

    public void setPlayerCoordinates(Point3D playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }

    public Point3D getAllyCoordinates() {
        return allyCoordinates;
    }

    public void setAllyCoordinates(Point3D allyCoordinates) {
        this.allyCoordinates = allyCoordinates;
    }

    public int getPlayerEntrance() {
        return playerEntrance;
    }

    public void setPlayerEntrance(int playerEntrance) {
        this.playerEntrance = playerEntrance;
    }

    public int getAllyEntrance() {
        return allyEntrance;
    }

    public void setAllyEntrance(int allyEntrance) {
        this.allyEntrance = allyEntrance;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getBattleOptions() {
        return battleOptions;
    }

    public void setBattleOptions(int battleOptions) {
        this.battleOptions = battleOptions;
    }

    public int getGreenScore() {
        return greenScore;
    }

    public void setGreenScore(int greenScore) {
        this.greenScore = greenScore;
    }

    public int getRedScore() {
        return redScore;
    }

    public void setRedScore(int redScore) {
        this.redScore = redScore;
    }

    public int getGreenHandicap() {
        return greenHandicap;
    }

    public void setGreenHandicap(int greenHandicap) {
        this.greenHandicap = greenHandicap;
    }

    public int getRedHandicap() {
        return redHandicap;
    }

    public void setRedHandicap(int redHandicap) {
        this.redHandicap = redHandicap;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }
}
