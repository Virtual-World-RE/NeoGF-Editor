package com.crystalpixel.neogfutils.battle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.crystalpixel.neogfutils.battle.entity.Opponent;
import com.crystalpixel.neogfutils.battle.entity.Position;

public class Battle {

    private List<Opponent> opponents = new ArrayList<>();
    private Position playerCoordinates;
    private Position allyCoordinates;
    private int playerEntrance;
    private int allyEntrance;
    private int timer;
    private int battleOptions;
    private int allyScore;
    private int enemyScore;
    private int allyHandicap;
    private int enemyHandicap;
    private int music;

    public Battle(Position playerCoordinates, Position allyCoordinates, int playerEntrance, int allyEntrance, int timer,
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

    public Position getPlayerCoordinates() {
        return playerCoordinates;
    }

    public void setPlayerCoordinates(Position playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }

    public Position getAllyCoordinates() {
        return allyCoordinates;
    }

    public void setAllyCoordinates(Position allyCoordinates) {
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

    public Set<BattleOptions> getEnabledOptions() {
        Set<BattleOptions> enabledOptions = new HashSet<>();

        for (BattleOptions option : BattleOptions.values()) {
            if (getEnabledOption(option)) {
                enabledOptions.add(option);
            }
        }

        return enabledOptions;
    }

    public boolean getEnabledOption(BattleOptions battleOptions) {
        return BattleOptions.isOptionEnabled(this.battleOptions, battleOptions);
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
