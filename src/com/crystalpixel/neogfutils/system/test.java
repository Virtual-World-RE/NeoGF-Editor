package com.crystalpixel.neogfutils.system;

import java.io.IOException;
import java.util.Objects;

import com.crystalpixel.neogfutils.battle.Battle;
import com.crystalpixel.neogfutils.utils.story.StoryUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
        jsonOutput.add("Options", optionsObject);

        JsonObject opponentObject = new JsonObject();
        battle.getOpponents().stream()
                .filter(Objects::nonNull)
                .findAny()
                .ifPresent(opponent -> {
                    String commanderName = opponent.getCommander() != null ? opponent.getCommander().getName() : "None";
                    opponentObject.addProperty("Opponent", commanderName);
                });

        jsonOutput.add("Opponents", opponentObject);

        String jsonString = gson.toJson(jsonOutput);

        System.out.println(jsonString);
    }
}
