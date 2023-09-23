package com.crystalpixel.neogfutils.battle;

import com.crystalpixel.neogfutils.battle.entity.Commander;

public enum MapLocation {

    KOUS_HOME("Kou's Home", new String[] {
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "Protect your room!",
            getTrainingName(Commander.NEKOBE, Commander.MANA) + ".",
            getTrainingName(Commander.KOTARO) + ".",
            "Defeat the Knight Borg unit!",
            "Protect the Death Crystal!",
            "A mysterious crystal.",
            getTrainingName(Commander.KAKERU) + ".",
            "Cooperate with Usagi to retake your home!",
            MapLocation.HERO_BORG_UNIT,
            getTrainingName(Commander.TETSUYA, Commander.KOTARO) + ".",
            getTrainingName(Commander.USAGI, Commander.YUJI) + ".",
            getTrainingName(Commander.SHO, Commander.OROCHI) + ".",
            MapLocation.DEATH_FORCE_UNIT,
            getTrainingName()
    }),

    NEO_ELEMENTARY("Neo Elementary", new String[] {
            "Cooperate with Usagi to defeat the Gun Borg unit!",
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "The Red Attackers go boom!!",
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "Cooperate with Tsutomu to protect the school!",
            "Defeat the Death Force special unit!",
            "Cooperate with Kotaro to retake the school!",
            MapLocation.HERO_BORG_UNIT,
            "Training with girls.",
            MapLocation.DEATH_FORCE_UNIT,
            getTrainingName()
    }),

    SUNNYSIDE_PARK("Sunnyside Park", new String[] {
            getBattleName(Commander.KITSUNE) + "!",
            "Defeat Nekobe & Kitsune!!",
            "Another Death Commander!",
            MapLocation.DEATH_FORCE_UNIT,
            "Drill Robot, the king of the underground!",
            "Protect the park at any cost!!",
            "Kotaro has arrived.",
            "Defeat the Girl Borg unit!",
            "Cooperate with Kitsune to retake the park!",
            "The Oriental Battle.",
            getTrainingName(),
            getTrainingName(Commander.KOTARO, Commander.NEKOBE) + "."
    }),

    CONSTRUCTION_ZONE("Construction Zone", new String[] {
            "Defeat Nekobe & Kitsune!",
            "Watch out! Demon Samurai!",
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "Cooperate with Kitsune to protect the Construction Zone!",
            "Cooperate with Tsutomu to fight in the air battle!",
            "Defeat the Vehicle unit!",
            "Defeat Death Commander Tama!",
            "Defeat Death Commander Tama!",
            getBattleName(Commander.YUJI, Commander.MET),
            "Cooperate with Tetsuya to retake the Construction Zone!",
            "Defeat the combined Machine Borgs!",
            getTrainingName()
    }),

    SERENITY_STREET("Serenity Street", new String[] {
            "Death Force Ninja unit!",
            "Protect Serenity Street!",
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "The Big Battle!",
            "Cooperate with Sho to defeat Orochi!",
            "Cooperate with Kakeru to retake Serenity Street!",
            "Retake Serenity Street!",
            "The Cold Battle.",
            getTrainingName()
    }),

    KAKERUS_HOME("Kakeru's Home", new String[] {
            getBattleName(Commander.KAKERU) + "!",
            getTrainingName(Commander.KAKERU) + ".",
            MapLocation.DEATH_FORCE_UNIT,
            getTrainingName(Commander.KAKERU, Commander.USAGI) + ".",
            "Cooperate with Kakeru to protect his home!",
            getTrainingName(Commander.NEKOBE, Commander.USAGI) + ".",
            getTrainingName(Commander.KAKERU, Commander.MANA) + ".",
            getTrainingName(Commander.KAKERU, Commander.MANA) + ".",
            getTrainingName(Commander.KAKERU) + ".",
            "Defeat the Death Eye unit!",
            "Defeat the Machine Borg unit!",
            "Cooperate with Kakeru to retake his home!",
            "\"Mr. Reverse Bolt\"",
            MapLocation.DEATH_FORCE_UNIT,
            getTrainingName(),
            getTrainingName(Commander.KAKERU, Commander.MANA) + ".",
    }),

    THE_PIT("The Pit", new String[] {
            "Cooperate with Usagi to defeat the ICBM Tanks!",
            "The Ultimate Cannon, light of destruction!",
            MapLocation.DEATH_FORCE_UNIT,
            "Cooperate with Met to protect the Pit!",
            getBattleName(Commander.SHO) + "!",
            "Defeat the Angel Borg unit!",
            getBattleName(Commander.SHO, Commander.YUJI) + "!",
            "Defeat the Diver unit!",
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "Cooperate with Yuji to retake the Pit!",
            getTrainingName()
    }),

    GREATER_TRICITY_AREA("Greater Tricity Area", new String[] {
            "Destroy the Flame Dragon!!",
            "Destroy the Flame Dragon!!",
            "The Gigantic Battle! Defeat the Cyber Death Dragon!!",
            "The Final Battle! Defeat the Galactic Emperor!!",
            "The Decisive Battle! Defeat the Cosmic Dragon!",
            "The gravity trap! Battle the Cosmic Dragon!",
            "The dragon's den!",
            "Defeat the Wing & Angel Borg unit!",
            "The end of Death Commander Tama!?",
            "The Final Battle! Defeat the Galactic Emperor!!",
            "Defeat the Death Commander Tama!",
            "The end of Death Commander Tama!?",
            "The Final Battle! Defeat the Galactic Emperor!!",
            "The Final Battle! Defeat the Galactic Emperor!!",
            MapLocation.DEATH_FORCE_UNIT,
            getTrainingName(),
            "Defeat the deadly UFOs!"
    }),

    SAFARITOWN_MARKET_PLACE("Safaritown Market Place", new String[] {
            getBattleName(Commander.USAGI, Commander.TSUTOMU) + "!",
            "The flying Death Force!",
            "Cooperate with Usagi to protect the Market Place!",
            getTrainingName(Commander.USAGI, Commander.TSUTOMU) + ".",
            getTrainingName(Commander.USAGI, Commander.TSUTOMU) + ".",
            "Defeat the Death Borg beta unit!",
            "Defeat the Tank Borg unit!",
            "The Small Battle.",
            "Cooperate with Tsutomu to retake the Market Place!",
            "Defeat the bombing unit!",
            "Defeat the Machine Borg unit!",
            MapLocation.HERO_BORG_UNIT,
            "Defeat the elite unit!",
            "Defeat the elite unit!",
            MapLocation.DEATH_FORCE_UNIT,
            getTrainingName(),
            "Defeat a Death Commander!"
    }),

    STONE_RIVER("Stone River", new String[] {
            getBattleName(Commander.TETSUYA, Commander.MET) + "!",
            MapLocation.DEATH_FORCE_UNIT,
            "Defeat Nekobe & Kitsune!!",
            "The decisive battle! Defeat the Dark Knight!!",
            "Defeat the Death Commander Orochi!",
            "100 rounds from the Gatling Gunners!",
            getBattleName(Commander.TETSUYA, Commander.MET) + "!",
            MapLocation.DEATH_FORCE_UNIT,
            "Cooperate with Tetsuya to protect the Stone River!",
            "Cooperate with Kotaro to defeat the Death Force unit!",
            "Defeat the Copy Man unit!",
            "Kotaro's crash course!",
            "Kotaro's crash course Part 2!!",
            "Kotaro's crash course Part 3!!",
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "Showdown at the Death Base!",
            "Showdown at the Death Base!",
            "Cooperate with Met to retake the Stone River!",
            "The decisive battle! Defeat the Dark Knight!!",
            MapLocation.HERO_BORG_UNIT,
            "Cooperate with ?? to defeat the Death Force unit!",
            getTrainingName(Commander.SHO, Commander.MET) + ".",
            getTrainingName(),
            getTrainingName(Commander.TETSUYA, Commander.KITSUNE) + "."
    }),

    LITTLE_HILL("Little Hill", new String[] {
            "The name is G Red!",
            "Showdown with Nekobe!!",
            getBattleName(Commander.SHO) + "!!",
            "The Death Commander Orochi appears!!",
            MapLocation.DEATH_FORCE_UNIT,
            "Defeat the Death Force bodyguards!",
            MapLocation.DEATH_FORCE_UNIT,
            "Protect Little Hill!",
            getBattleName(Commander.SHO, Commander.YUJI) + "!",
            "Defeat the Ultimate Cannon unit!",
            MapLocation.DEATH_FORCE_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            "Defeat a Death Commander!",
            "Defeat the combined Machine Borgs!!",
            "Cooperate with Nekobe to retake Little Hill!",
            "Cooperate with Orochi to defeat the Death Force unit!",
            getTrainingName(Commander.MET, Commander.TAMA) + ".",
            getTrainingName()
    }),

    SKY_FORTRESS("Sky Fortress", new String[] {
            "Save Mana's partner Nao!",
            getTrainingName(Commander.USAGI, Commander.MANA) + "!",
            "Cooperate with Mana to protect her room!",
            "The big cockroach parade.",
            "Defeat the Angel & Girl Borg unit!",
            "The Hot Battle!!",
            "The great dragon parade.",
            "Retake Mana's home!",
            MapLocation.HERO_BORG_UNIT,
            MapLocation.DEATH_FORCE_UNIT,
            getTrainingName(),
            "Defeat the Death Borg unit!"
    }),

    MANAS_HOME("Mana's Home", MapLocation.SKY_FORTRESS.battleNames),

    DEATH_BASE("Death Base", MapLocation.SKY_FORTRESS.battleNames);

    private static final String DEATH_FORCE_UNIT = "Defeat the Death Force unit!";
    private static final String HERO_BORG_UNIT = "Showdown! Hero Borg unit!!";

    private String name;
    private String[] battleNames;

    MapLocation(String name) {
        this(name, new String[0]);
    }

    MapLocation(String name, String[] battleNames) {
        this.name = name;
        this.battleNames = battleNames;
    }

    public static MapLocation get(int index) {
        return index > values().length ? null : values()[index];
    }

    public String getName() {
        return name;
    }

    public String[] getBattleNames() {
        return battleNames;
    }

    private static String getTrainingName() {
        return getVersusName(true, null, null);
    }

    private static String getTrainingName(Commander commander) {
        return getVersusName(true, commander, null);
    }

    private static String getTrainingName(Commander commander1, Commander commander2) {
        return getVersusName(true, commander1, commander2);
    }

    private static String getBattleName(Commander commander) {
        return getVersusName(false, commander, null);
    }

    private static String getBattleName(Commander commander1, Commander commander2) {
        return getVersusName(false, commander1, commander2);
    }

    private static String getVersusName(boolean training, Commander commander1, Commander commander2) {
        String format = "%s with %s";
        String battleType = training ? "Training" : "Battle";
        String commanders;
        if (commander1 == null && commander2 == null) {
            commanders = "your allies.";
        }
        else if (commander1 != null && commander2 == null) {
            commanders = commander1.getName();
        }
        else if (commander1 == null && commander2 != null) {
            commanders = commander2.getName();
        }
        else {
            commanders = commander1.getName() + " & " + commander2.getName();
        }
        return String.format(format, battleType, commanders);
    }
}
