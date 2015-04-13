package GameView;

import java.util.LinkedList;

import GameView.Fragment.Tile;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.mrmax.runningaliens.R;

public class Player {
	final static int RUN = 0, JUMP = 1;

	int x, y;
	int W, H;
	private int vy = 0, vg = 2;
	private int nv = 1;
	private int condit = 0, numBtmp = 1;
	private Bitmap p1_01, p1_02, p1_03, p1_04, p1_05, p1_06;
	private Context cont;
	private Paint p = new Paint();

	public Player(int x, int y, int W, int H, Context c) {
		this.cont = c;
		this.x = x;
		this.y = y;
		p1_01 = BitmapFactory.decodeResource(cont.getResources(),
				R.drawable.p1_01);
		p1_01 = Bitmap.createScaledBitmap(p1_01, W, H, true);

		p1_02 = BitmapFactory.decodeResource(cont.getResources(),
				R.drawable.p1_02);
		p1_02 = Bitmap.createScaledBitmap(p1_02, W, H, true);

		p1_03 = BitmapFactory.decodeResource(cont.getResources(),
				R.drawable.p1_03);
		p1_03 = Bitmap.createScaledBitmap(p1_03, W, H, true);

		p1_04 = BitmapFactory.decodeResource(cont.getResources(),
				R.drawable.p1_04);
		p1_04 = Bitmap.createScaledBitmap(p1_04, W, H, true);

		p1_05 = BitmapFactory.decodeResource(cont.getResources(),
				R.drawable.p1_05);
		p1_05 = Bitmap.createScaledBitmap(p1_05, W, H, true);
		
		p1_06 = BitmapFactory.decodeResource(cont.getResources(),
				R.drawable.p1_06);
		p1_06 = Bitmap.createScaledBitmap(p1_06, W, H, true);

		this.condit = RUN;
	}

	void jump(int vy) {
		this.y -= vy;
	}

	void draw(Canvas c) {
		switch (numBtmp) {
		case 1: {
			c.drawBitmap(p1_01, x, y, p);
			break;
		}
		case 2: {
			c.drawBitmap(p1_02, x, y, p);
			break;
		}
		case 3: {
			c.drawBitmap(p1_03, x, y, p);
			break;
		}
		case 4: {
			c.drawBitmap(p1_04, x, y, p);
			break;
		}
		case 5: {
			c.drawBitmap(p1_05, x, y, p);
			break;
		}
		case 6: {
			c.drawBitmap(p1_06, x, y, p);
			break;
		}
		}
	}

	boolean plStand(Game g) {
		LinkedList<Fragment> fList = g.fragments;
		for (Fragment fr : fList) {
			int x_1 = fr.x, x_2 = fr.x + fr.W;
			int x1_1 = this.x, x1_2 = this.x + this.W;
			if (((x_1 <= x1_1 && x_2 >= x1_2) || (x_1 <= x1_2 && x_2 >= x1_2))) {
				for (int i = 0; i < fr.map.length; i++) {
					for (int u = 0; u < fr.map[0].length; u++) {
						x_1 = fr.x + u * fr.tileSize;
						x_2 = fr.x + (u + 1) * fr.tileSize;
						if (!fr.map[i][u].canMove
								&& ((x_1 <= x1_1 && x_2 >= x1_2)
								|| (x_1 <= x1_2 && x_2 >= x1_2))) {
							if (i * fr.tileSize == this.y + this.H) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	void move(int pix, Game g) {
		for (int i = 0; i < pix; i++) {
			if (!plStand(g)) {
				this.y++;
			} else {
				return;
			}
		}
	}

	void update(Game g) {
		if (condit == RUN) {
			if (numBtmp == 1) {
				nv = 1;
			}
			if (numBtmp == 5) {
				nv = -1;
			}
			numBtmp += nv;
		}
		if (!plStand(g)) {
			condit = JUMP;
			numBtmp = 6;
		}
		if (this.condit == JUMP) {
			move(vg, g);
		}
		if (this.condit == JUMP && plStand(g)) {
			this.condit = RUN;
			numBtmp = 1;
		}

	}
}
