import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TowerDefenseFactory implements EntityFactory {
    @Spawns("ice tower")
    public Entity newIceTower(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.TOWER)
                .view(new Rectangle(40, 40, data.get("color")))
                .build();
    }

    @Spawns("earth tower")
    public Entity newEarthTower(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.TOWER)
                .view(new Rectangle(40, 40, data.get("color")))
                .build();
    }

    @Spawns("fire tower")
    public Entity newFireTower(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.TOWER)
                .view(new Rectangle(40, 40, data.get("color")))
                .build();
    }

    @Spawns("grunt enemy")
    public Entity newGruntEnemy(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .view(new Circle(10, Color.YELLOW))
                .with(new GruntEnemyFunctionality())
                .build();
    }

    @Spawns("brute enemy")
    public Entity newBruteEnemy(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .view(new Circle(30, Color.DARKRED))
                .with(new BruteEnemyFunctionality())
                .build();
    }

    @Spawns("tactical enemy")
    public Entity newTacticalEnemy(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .view(new Circle(20, Color.BLUE))
                .with(new TacticalEnemyFunctionality())
                .build();
    }

    @Spawns("boss enemy")
    public Entity newBossEnemy(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .view(new Circle(50, Color.LIGHTGOLDENRODYELLOW))
                .with(new BossEnemyFunctionality())
                .build();
    }
}
