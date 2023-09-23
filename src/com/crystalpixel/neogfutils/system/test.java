package com.crystalpixel.neogfutils.system;

import java.io.IOException;

import com.crystalpixel.neogfutils.battle.Battle;
import com.crystalpixel.neogfutils.utils.story.StoryUtils;

public class test {
    public static void main(String[] args) throws IOException {
        Battle battle = StoryUtils.readBattle(0x803843c8);
        System.out.println(battle.getEnabledOptions());

    }
}
