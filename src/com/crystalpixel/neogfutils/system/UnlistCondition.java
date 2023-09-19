package com.crystalpixel.neogfutils.system;

import com.crystalpixel.neogfutils.borg.BorgColor;
import com.crystalpixel.neogfutils.utils.story.StoryUtils;

import java.io.IOException;
import java.util.List;

public class UnlistCondition extends Condition {

    public UnlistCondition(Integer type, List<Integer> parameters) {
        super(type, parameters);
    }

    @Override
    public String toString() {
        switch (type) {
            case 0x0: return String.format("Battle Count is equal to %s", parameters.get(0));
            case 0x1: return String.format("GF Energy is in the range %s-%s", parameters.get(0), parameters.get(1));
            case 0x2:
                try {
                    return String.format("Battle %s's Win count is not 0, and it is unlisted %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 0x3:
                try {
                    return String.format("Battle %s's Win count is 0, and it is Unlisted %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 0x4:
                try {
                    return String.format("Battle %s is Unlisted %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 0x5:
                try {
                    return String.format("Battle %s is not Unlocked %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 0x6:
                try {
                    return String.format("Battle %s's Win count is 0 %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            case 0x2: return String.format("Battle %s's Win count is not 0, and it is unlisted %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
//            case 0x3: return String.format("Battle %s's Win count is 0, and it is Unlisted %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
//            case 0x4: return String.format("Battle %s is Unlisted %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
//            case 0x5: return String.format("Battle %s is not Unlocked %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
//            case 0x6: return String.format("Battle %s's Win count is 0 %s", parameters.get(0), StoryUtils.getMissionStringIdentifiers(StoryUtils.getStoryBattleConfigurationAddress(parameters.get(0))));
            case 0x7: return String.format("Chapter %s is Unlocked", parameters.get(0));
            case 0x8: return String.format("Chapter %s is not Unlocked", parameters.get(0));
            case 0xA:
                StringBuilder builder = null;
                builder = new StringBuilder(String.format("Gotcha Box or Warehouse contains a %s", BorgSpecies.getBorgSpecies(parameters.get(0)).getName()));
                if (parameters.get(1) != 0xFFFF) builder.append(String.format(", with the color %s", BorgColor.values()[parameters.get(1)].getName()));
                return builder.toString();
            case 0xD: return String.format("weird and long description");
            case 0xE: return String.format("Current Battle's Unlock Total is greater than or equal to %s", parameters.get(0));
            case 0xF: return String.format("Battle %s is Unlisted, and the current Battle's Lose count is greater than or equal to %s", parameters.get(0), parameters.get(0));
            case 0x10: return String.format("another long description");
            case 0x11: return String.format("Lose count is less than %s", parameters.get(0));
            case 0x12: return String.format("Lose count is greater than or equal to %s", parameters.get(0));
            case 0x13: return "OR";
        }
        return "Unknown Condition";
    }
}