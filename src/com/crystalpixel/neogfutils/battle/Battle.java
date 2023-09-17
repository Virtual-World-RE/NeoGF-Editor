package com.crystalpixel.neogfutils.battle;

import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Battle {

    private List<Opponent> opponents = new ArrayList<>();
    private Point3D playerCoordinates;
    private Point3D allyCoordinates;
    private int playerEntrance;
    private int allyEntrance;
    private int timer;
    private int battleOptions;
    private int allyScore;
    private int enemyScore;
    private int allyHandicap;
    private int enemyHandicap;
    private int music;

    public Battle(Point3D playerCoordinates, Point3D allyCoordinates, int playerEntrance, int allyEntrance, int timer,
            int battleOptions, int allyScore, int enemyScore, int allyHandicap, int enemyHandicap, int music) {
        this.playerCoordinates = playerCoordinates;
        this.allyCoordinates = allyCoordinates;
        this.playerEntrance = playerEntrance;
        this.allyEntrance = allyEntrance;
        this.timer = timer;
        this.battleOptions = battleOptions;
        this.allyScore = allyScore;
        this.enemyScore = enemyScore;
        this.allyHandicap = allyHandicap;
        this.enemyHandicap = enemyHandicap;
        this.music = music;
    }

    public List<Opponent> getOpponents() {
        return opponents;
    }

    public void addOpponent(Opponent opponent) {
        opponents.add(opponent);
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

    public int getAllyScore() {
        return allyScore;
    }

    public void setAllyScore(int allyScore) {
        this.allyScore = allyScore;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public void setEnemyScore(int enemyScore) {
        this.enemyScore = enemyScore;
    }

    public int getAllyHandicap() {
        return allyHandicap;
    }

    public void setAllyHandicap(int allyHandicap) {
        this.allyHandicap = allyHandicap;
    }

    public int getEnemyHandicap() {
        return enemyHandicap;
    }

    public void setEnemyHandicap(int enemyHandicap) {
        this.enemyHandicap = enemyHandicap;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }
}
