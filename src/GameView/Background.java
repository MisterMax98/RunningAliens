package GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Background {
	private BackBTMP background1, background2;
	int W;

	public Background(Bitmap background, int W) {
		this.W = W;
		background1 = new BackBTMP(background, 0);
		background2 = new BackBTMP(background, W);
	}

	void draw(Canvas c) {
		Paint p = new Paint();
		c.drawBitmap(background1.backgr, background1.x, 0, p);
		c.drawBitmap(background2.backgr, background2.x, 0, p);
	}

	void update(int vx) {
		background1.x -= vx;
		background2.x -= vx;
		if (background1.x + W <= 0) {
			background1.x += 2 * W;
		}
		if (background2.x + W <= 0) {
			background2.x += 2 * W;
		}
	}

	private class BackBTMP {
		Bitmap backgr;
		int x;

		private BackBTMP(Bitmap bmp, int x) {
			this.backgr = bmp;
			this.x = x;
		}
	}
}
