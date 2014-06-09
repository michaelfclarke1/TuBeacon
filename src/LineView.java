import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class LineView extends View {
	
	private String stationName = null;
	
	public LineView(Context context) {
		super(context);
	}
	
	public LineView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	public LineView(Context context, AttributeSet attributeSet, int defaultStyle) {
		super(context, attributeSet, defaultStyle);
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	protected void onDraw(Canvas canvas) {
		
		float radius;
		if (this.getHeight() > this.getWidth()) {
			radius = this.getWidth() / 2;
		} else {
			radius = this.getHeight() / 2;
		}
		
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(30);
		p.setColor(Color.RED);
		canvas.drawCircle(this.getHeight()/2, this.getWidth()/2, radius, p);
	}
	
}
