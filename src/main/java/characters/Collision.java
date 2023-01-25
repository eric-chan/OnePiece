package characters;

public interface Collision {

    default void CheckCollisionTile(DynamicObject Do){

        float predictTileX = (Do.x + Do.speedX);
        float predictTileY = (Do.y + Do.speedY);
        if(Do.speedX != 0 &&
                Do.mp.mapGenerator.getTileOn( (int)predictTileX, (int)Do.y) < Do.mp.mapGenerator.getTileSize())
            if(Do.mp.mapGenerator.tile[ Do.mp.mapGenerator.getTileOn( (int)predictTileX, (int)Do.y) ].collision)
                Do.speedX = 0;
        if(Do.speedY != 0 &&
                Do.mp.mapGenerator.getTileOn((int)Do.x, (int)predictTileY) < Do.mp.mapGenerator.getTileSize())
            if(Do.mp.mapGenerator.tile[ Do.mp.mapGenerator.getTileOn((int)Do.x, (int)predictTileY) ].collision)
                Do.speedY = 0;
    }
}
