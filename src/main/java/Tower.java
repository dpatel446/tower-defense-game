import javafx.scene.paint.Color;

public abstract class Tower {
    private int cost;
    private String name;
    private String type;
    private int health;
    private GameDifficulty difficulty;

    protected Tower() {
        this("a", "b", TowerDefense.getDifficulty());
    }

    protected Tower(String name, String type, GameDifficulty difficulty) {
        this.name = name;
        this.type = type;
        if (difficulty == GameDifficulty.EASY) {
            cost = 10;
        } else if (difficulty == GameDifficulty.MEDIUM) {
            cost = 20;
        } else {
            cost = 30;
        }
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public String getType() {
        return this.type;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public abstract String getDescription();

    public abstract Color getColor();
    
}
