package graphics;

import entity.mob.Player;
import level.tile.Tile;

import java.util.Random;

public class Screen {

    // Pixels, tiles, and dimensions
    public int width;
    public int height;
    public final int MAP_SIZE = 16;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    public int xOffset;
    public int yOffset;

    private Random random = new Random();

    // Instantiate screen with given height and width
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels =  new int[width * height];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    // Renders tiles
    public void renderTile(int x, int y, Tile tile) {
        x -= xOffset;
        y -= yOffset;
        for (int i = 0; i < tile.sprite.SIZE; i++) {
            int yAbs = i + y;
            for (int j = 0; j < tile.sprite.SIZE; j++) {
                int xAbs = j + x;
                if (xAbs < (0 - tile.sprite.SIZE) || xAbs >= width || yAbs < 0 || yAbs >= height) { break; }
                if (xAbs < 0) { xAbs = 0; }
                pixels[xAbs + yAbs * width] = tile.sprite.pixels[j + i * tile.sprite.SIZE];
            }
        }
    }

    // Renders player
    public void renderPlayer(int x, int y, Sprite sprite) {
        x -= xOffset;
        y -= yOffset;
        for (int i = 0; i < sprite.SIZE; i++) {
            int yAbs = i + y;
            for (int j = 0; j < sprite.SIZE; j++) {
                int xAbs = j + x;
                if (xAbs < (0 - sprite.SIZE) || xAbs >= width || yAbs < 0 || yAbs >= height) { break; }
                if (xAbs < 0) { xAbs = 0; }
                int color = sprite.pixels[j + i * sprite.SIZE];
                if (color != 0xfffb0007) { pixels[xAbs + yAbs * width] = color; }
            }
        }
    }

    // Clears each pixel
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void setOffsets(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
