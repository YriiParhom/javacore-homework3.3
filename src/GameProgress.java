import java.io.File;
import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1l;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;
    String separator = File.separator;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public String saveGame(String fileName) {
        return "D:" + separator + separator + "Games" + separator + "SaveGames" + separator + fileName;
    }

    public String zipFiles(String zipName) {
        return "D:" + separator + separator + "Games" + separator + "SaveGames" + separator + zipName;
    }

    @Override
    public String toString() {
        return "GameProgress{" + "health=" + health + ", weapons=" + weapons +
                ", lvl=" + lvl + ", distance=" + distance + '}';
    }
}

