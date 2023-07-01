package structures;

public class LevelProperties {

    private int hp;
    private int bAmmoStart;
    private int bAmmoMax;
    private int bAmmoType;
    private int bAmmoReload;
    private int xAmmoStart;
    private int xAmmoMax;
    private int xAmmoType;
    private int xAmmoReload;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getbAmmoStart() {
        return bAmmoStart;
    }

    public void setbAmmoStart(int bAmmoStart) {
        this.bAmmoStart = bAmmoStart;
    }

    public int getbAmmoMax() {
        return bAmmoMax;
    }

    public void setbAmmoMax(int bAmmoMax) {
        this.bAmmoMax = bAmmoMax;
    }

    public int getbAmmoType() {
        return bAmmoType;
    }

    public void setbAmmoType(int bAmmoType) {
        this.bAmmoType = bAmmoType;
    }

    public int getbAmmoReload() {
        return bAmmoReload;
    }

    public void setbAmmoReload(int bAmmoReload) {
        this.bAmmoReload = bAmmoReload;
    }

    public int getxAmmoStart() {
        return xAmmoStart;
    }

    public void setxAmmoStart(int xAmmoStart) {
        this.xAmmoStart = xAmmoStart;
    }

    public int getxAmmoMax() {
        return xAmmoMax;
    }

    public void setxAmmoMax(int xAmmoMax) {
        this.xAmmoMax = xAmmoMax;
    }

    public int getxAmmoType() {
        return xAmmoType;
    }

    public void setxAmmoType(int xAmmoType) {
        this.xAmmoType = xAmmoType;
    }

    public int getxAmmoReload() {
        return xAmmoReload;
    }

    public void setxAmmoReload(int xAmmoReload) {
        this.xAmmoReload = xAmmoReload;
    }

    public static String getPropertyNameByIndex(int index) {
        switch (index) {
            case 0: return "HP";
            case 1: return "B Ammo Start";
            case 2: return "B Ammo Max";
            case 3: return "B Ammo Type";
            case 4: return "B Ammo Reload";
            case 5: return "X Ammo Start";
            case 6: return "X Ammo Max";
            case 7: return "X Ammo Type";
            case 8: return "X Ammo Reload";
            default: throw new IllegalArgumentException();
        }
    }

    public int getPropertyValueByIndex(int index) {
        switch (index) {
            case 0: return hp;
            case 1: return bAmmoStart;
            case 2: return bAmmoMax;
            case 3: return bAmmoType;
            case 4: return bAmmoReload;
            case 5: return xAmmoStart;
            case 6: return xAmmoMax;
            case 7: return xAmmoType;
            case 8: return xAmmoReload;
            default: throw new IllegalArgumentException();
        }
    }
}
