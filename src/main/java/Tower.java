public abstract class Tower {
    private int cost;
    private String name;
    private String type;
    private int health;
    private GameDifficulty difficulty;

    protected Tower() {
        this("a","b",TowerDefense.getDifficulty());
    }

    protected Tower(String name, String type, GameDifficulty difficulty) {
        this.name = name;
        this.type = type;
        if (difficulty == GameDifficulty.EASY) {
        	cost = 50;
        }
        else if (difficulty == GameDifficulty.MEDIUM) {
        	cost = 100;
        }
        else {
        	cost = 150;
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
    
}
