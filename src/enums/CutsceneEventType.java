package enums;

public enum CutsceneEventType {

    INPUT_NAME(0),
    DISPLAY_ART(1),
    EPILOGUE(0),
    PORTRAIT_NAME(2),
    PORTRAIT_IMAGE(2),
    PORTRAIT_REMOVE(1),
    BACKDROP_REMOVE(0),
    ANNOUNCEMENT(1),
    PORTRAIT_TEXT(2),
    PORTRAIT_EMPTY(1),
    MUSIC_MAP(1),
    MUSIC_CUTSCENE(1),
    PORTRAIT_SOUND(2),
    ENABLE_LOCATION(1),
    ALLY_UNLOCK(2),
    ALLY_UPDATE(2),
    LOCATION_HIGHLIGHT(2),
    LOCATION_DARKEN(2),
    LOCATION_CLEAR(1),
    CUTSCENE_BORG_UNLOCK(2),
    CUTSCENE_BORG_FORMATION(2),
    CUTSCENE_POWER_UP(0),
    GF_ENERGY_SET_LIMIT(1),
    GF_ENERGY_SET(1),
    GF_ENERGY_INCREMENT(1),
    CHAPTER_UNLIST(1),
    TERMINATOR(0);

    private int params;

    CutsceneEventType(int params) {
        this.params = params;
    }

    public int getParams() {
        return params;
    }
}
