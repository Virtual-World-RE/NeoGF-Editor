package structures;

import javafx.geometry.Point3D;
import structures.Opponent;

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
}
