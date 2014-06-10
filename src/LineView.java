import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class LineView extends View {
	
	private String stationName = "Uknown";
	
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
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		float radius;
		if (this.getHeight() > this.getWidth()) {
			radius = this.getWidth() / 2.5f;
		} else {
			radius = this.getHeight() / 2.5f;
		}
		
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		p.setStrokeWidth(20);
		p.setColor(Color.RED);
		canvas.drawColor(Color.WHITE);
		canvas.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2, p);
		canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, radius, p);
		
		p.setColor(Color.WHITE);
		canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, radius/1.2f, p);
		
		p.setColor(Color.BLACK);
		canvas.drawText("Hello World", 0, 0, this.getHeight(), this.getWidth(), p);
		
	}
	
}
