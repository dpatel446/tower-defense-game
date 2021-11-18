import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Tower {
    private int cost;
    private String name;
    private String type;
    private int health;
    private GameDifficulty difficulty;
    private Point2D location = null;
    private double radius;
    private int damage;
    private long delay;

    protected Tower(String name, String type, GameDifficulty difficulty) {
        this.damage = 0;
        this.radius = 0;
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

    protected Tower(String name, String type, GameDifficulty difficulty,
                    double radius, int damage, long delay) {
        this.radius = radius;
        this.delay = delay;
        this.damage = damage;
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

    public void setLocation(double x, double y) {
        location = new Point2D(x, y);
    }

    public Point2D getLocation() {
        return location;
    }

    public int getDamage() {
        return damage;
    }

    public double getRadius() {
        return radius;
    }

    public void setDelay(long millis) {
        this.delay = millis;
    }

    public long getDelay() {
        return delay;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attack() {
        if (location != null && delay != 0L) {
            TowerAttackController.attackController(this);
        }
    }
    
}
