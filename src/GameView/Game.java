package GameView;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.mrmax.runningaliens.R;

public class Game extends View implements OnTouchListener {
	Context cont;
	boolean start = false;
	int H, W;
	LinkedList<Fragment> fragments;
	Background backgr;
	GameTimer gt;
	Player pl;

	public Game(Context context) {
		super(context);
		this.cont = context;
		this.setOnTouchListener(this);
		// Размеры экрана
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		H = dm.heightPixels;
		W = dm.widthPixels;
		// Фон и стартовый фрагмент
		Bitmap background = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.bg_castle);
		background = Bitmap.createScaledBitmap(background, W, H, false);
		backgr = new Background(background, W);
		int tileSize = H / 8;
		fragments = new LinkedList<Fragment>();
		fragments.add(new Fragment(0, tileSize, Fragment.START_FRAGMENT,
				context));
		// Персонаж
		int plH = H / 6, plW = H / 8;
		pl = new Player(tileSize * 2, H - tileSize * 3 - plH, plW, plH, context);
		// Запуск таймера
		gt = new GameTimer(25, this);
		gt.start();
	}

	protected void onDraw(Canvas c) {
		Paint p = new Paint();
		p.setTextSize(20);
		backgr.draw(c);
		pl.draw(c);
		c.drawText("" + pl.plStand(this), 0, 20, p);
		for (Fragment fr : fragments) {
			fr.draw(c);
		}
	}

	class GameTimer extends CountDownTimer {
		Game game;

		public GameTimer(long countDownInterval, Game g) {
			super(1000, countDownInterval);
			this.game = g;
		}

		@Override
		public void onFinish() {
			gt = new GameTimer(25, game);
			gt.start();
		}

		@Override
		public void onTick(long arg0) {
			backgr.update(5);
			pl.update(game);
			for (Fragment fr : fragments) {
				fr.x -= 5;
			}
			if (fragments.getLast().x + fragments.getLast().W < W) {
				fragments.add(new Fragment(fragments.getLast().x
						+ fragments.getLast().W, H / 8,
						Fragment.DEFAULT_FRAGMENT, cont));
			}
			if (fragments.getFirst().x + fragments.getFirst().W < 0) {
				fragments.removeFirst();
			}
			invalidate();

		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			pl.jump(20);
			pl.update(this);
			break;
		}
		}
		return false;
	}
}
