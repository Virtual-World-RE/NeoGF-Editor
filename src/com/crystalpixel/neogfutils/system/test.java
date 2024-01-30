package com.crystalpixel.neogfutils.system;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.crystalpixel.neogfutils.battle.Battle;
import com.crystalpixel.neogfutils.battle.entity.Position;
import com.crystalpixel.neogfutils.utils.story.StoryUtils;

public class test {

    public static void main(String[] args) throws IOException {
        Battle battle = StoryUtils.readBattle(0x80384158);
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().enableComplexMapKeySerialization()
                .create();

        JsonObject optionsObject = new JsonObject();
        battle.getAllOptions().forEach((key, value) -> {
            optionsObject.addProperty(key, value);
        });

        JsonObject jsonOutput = new JsonObject();

        JsonArray opponentsArray = new JsonArray();

        System.out.println(battle.getOpponents().toString());

        battle.getOpponents().forEach(opponent -> {
            if (opponent != null) {
                JsonObject opponentObject = new JsonObject();
                String commanderName = opponent.getCommander() != null ? opponent.getCommander().getName() : "None";
                int difficulty = opponent.getDifficulty();
                int borgListId = opponent.getBorgListId();
                int level = opponent.getLevel();
                Position position = opponent.getCoordinates();
                int entrance = opponent.getEntrance();
                opponentObject.addProperty("Opponent", commanderName);
                opponentObject.addProperty("Difficulty", difficulty);
                opponentObject.addProperty("BorgListId", borgListId);
                opponentObject.addProperty("Level", level);
                opponentObject.add("Position", gson.toJsonTree(position));
                opponentObject.addProperty("Entrance", entrance);
                opponentsArray.add(opponentObject);
            }
        });

        jsonOutput.add("Opponents", opponentsArray);

        // Ajouter les propriétés Battle
        jsonOutput.add("PlayerCoordinates", gson.toJsonTree(battle.getPlayerCoordinates()));
        jsonOutput.add("AllyCoordinates", gson.toJsonTree(battle.getAllyCoordinates()));
        jsonOutput.addProperty("PlayerEntrance", battle.getPlayerEntrance());
        jsonOutput.addProperty("AllyEntrance", battle.getAllyEntrance());
        jsonOutput.addProperty("Timer", battle.getTimer());
        jsonOutput.add("Options", optionsObject);
        jsonOutput.addProperty("AllyScore", battle.getAllyScore());
        jsonOutput.addProperty("EnemyScore", battle.getEnemyScore());
        jsonOutput.addProperty("AllyHandicap", battle.getAllyHandicap());
        jsonOutput.addProperty("EnemyHandicap", battle.getEnemyHandicap());
        jsonOutput.addProperty("Music", battle.getMusic());

        String jsonString = gson.toJson(jsonOutput);

        System.out.println(jsonString);
    }
}
