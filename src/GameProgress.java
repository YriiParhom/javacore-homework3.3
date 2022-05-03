import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1l;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public String saveGame(String fileName) {
        return "D://Games/SaveGames//" + fileName;
    }

    public String zipFiles(String zipName) {
        return "D://Games/SaveGames//" + zipName;
    }

    @Override
    public String toString() {
        return "GameProgress{" + "health=" + health + ", weapons=" + weapons +
                ", lvl=" + lvl + ", distance=" + distance + '}';
    }
}

