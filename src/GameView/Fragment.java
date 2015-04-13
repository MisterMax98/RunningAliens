package GameView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.mrmax.runningaliens.R;

public class Fragment {
	int x, W;
	Tile[][] map = null;
	int tileSize;
	private Context cont;

	final static int START_FRAGMENT = 0, DEFAULT_FRAGMENT = 1;

	public Fragment(int x, int tileSize, int fragmentType, Context c) {
		this.cont = c;
		this.x = x;
		this.tileSize = tileSize;
		switch (fragmentType) {
		case START_FRAGMENT: {
			map = new Tile[][] {
					new Tile[] { new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(0),
							new Tile(4, true, true), new Tile(0), new Tile(0),
							new Tile(4, true, true), new Tile(0), new Tile(0),
							new Tile(4, true, true), new Tile(0), new Tile(0),
							new Tile(4, true, true), new Tile(0), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0), new Tile(0) },
					new Tile[] { new Tile(2), new Tile(2), new Tile(2),
							new Tile(2), new Tile(2), new Tile(2), new Tile(2),
							new Tile(2), new Tile(2), new Tile(2), new Tile(2),
							new Tile(2), new Tile(2), new Tile(2) },
					new Tile[] { new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1), new Tile(1) },
					new Tile[] { new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1), new Tile(1) } };
			break;
		}
		case DEFAULT_FRAGMENT: {
			map = new Tile[][] {
					new Tile[] { new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(4, true, true),
							new Tile(0), new Tile(4, true, true), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(0), new Tile(0),
							new Tile(0), new Tile(0) },
					new Tile[] { new Tile(0), new Tile(0), new Tile(3),
							new Tile(0), new Tile(0) },
					new Tile[] { new Tile(2), new Tile(2), new Tile(2),
							new Tile(2), new Tile(2) },
					new Tile[] { new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1) },
					new Tile[] { new Tile(1), new Tile(1), new Tile(1),
							new Tile(1), new Tile(1) }, };
			break;
		}
		}
		this.W = map[0].length * tileSize;
	}

	public void draw(Canvas c) {
		Paint p = new Paint();
		for (int i = 0; i < map.length; i++) {
			for (int u = 0; u < map[0].length; u++) {
				if (map[i][u].tileType != 0) {
					c.drawBitmap(map[i][u].tileBitmap, (x + u * tileSize), i
							* tileSize, p);
				}
			}
		}
	}

	class Tile {
		final static int CASTLE_MID = 1, AIR = 0, CASTLE_UP = 2, BOX = 3,
				WINDOW = 4, GRASS_MID = 5, GRASS_UP = 6;
		boolean canMoveUp = true, canMove = false;
		int tileType = 0;
		Bitmap tileBitmap;

		private void TileTypeToBmp(int tileType) {
			switch (tileType) {
			case CASTLE_MID: {
				this.tileBitmap = BitmapFactory.decodeResource(
						cont.getResources(), R.drawable.castle_mid);
				break;
			}
			case CASTLE_UP: {
				this.tileBitmap = BitmapFactory.decodeResource(
						cont.getResources(), R.drawable.castle_up);
				break;
			}
			case GRASS_MID: {
				this.tileBitmap = BitmapFactory.decodeResource(
						cont.getResources(), R.drawable.grass_mid);
				break;
			}
			case GRASS_UP: {
				this.tileBitmap = BitmapFactory.decodeResource(
						cont.getResources(), R.drawable.grass_up);
				break;
			}
			case BOX: {
				this.tileBitmap = BitmapFactory.decodeResource(
						cont.getResources(), R.drawable.box);
				break;
			}
			case WINDOW: {
				this.tileBitmap = BitmapFactory.decodeResource(
						cont.getResources(), R.drawable.window);
				break;
			}
			default: {
				this.tileBitmap = null;
				break;
			}
			}
			if (tileBitmap != null) {
				this.tileBitmap = Bitmap.createScaledBitmap(tileBitmap,
						tileSize, tileSize, true);
			}
		}

		private void setTileT(int tileType) {
			switch (tileType) {
			case GRASS_MID: {
				this.tileType = GRASS_MID;
				break;
			}
			case CASTLE_MID: {
				this.tileType = CASTLE_MID;
				break;
			}
			case CASTLE_UP: {
				this.tileType = CASTLE_UP;
				break;
			}
			case GRASS_UP: {
				this.tileType = GRASS_UP;
				break;
			}
			case BOX: {
				this.tileType = BOX;
				break;
			}
			case WINDOW: {
				this.tileType = WINDOW;
				break;
			}
			default: {
				this.tileType = AIR;
				break;
			}
			}
			TileTypeToBmp(this.tileType);
		}

		private Tile(int tileType, boolean canMoveUp, boolean canMove) {
			this.canMoveUp = canMoveUp;
			this.canMove = canMove;
			setTileT(tileType);
		}

		private Tile(int tileType) {
			setTileT(tileType);
		}
	}
}
