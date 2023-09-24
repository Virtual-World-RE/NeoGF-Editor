package com.crystalpixel.neogfutils.system;

import java.io.IOException;
import java.util.Objects;

import com.crystalpixel.neogfutils.battle.Battle;
import com.crystalpixel.neogfutils.battle.entity.Commander;
import com.crystalpixel.neogfutils.utils.story.StoryUtils;

public class test {
    public static void main(String[] args) throws IOException {
        Battle battle = StoryUtils.readBattle(0x80384158);
        System.out.println(battle.getEnabledOptions() + " Opponent : " + battle.getOpponents().stream()
        .filter(Objects::nonNull)
        .findAny()
        .map(opponent -> opponent.getCommander() != null ? opponent.getCommander().getName() : Commander.NONE.getName())
        .orElse("No opponent found"));


    }
}
