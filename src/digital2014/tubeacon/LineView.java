package digital2014.tubeacon;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class LineView extends View {
	
	private String stationName = "";
	
	private int height;
	private int width;
	
	private int colour = Color.RED;
	
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
		invalidate();
	}
	
	public void setHeight(int height) {
		super.setMinimumHeight(height);
		this.height = height;
	}
	
	public void setWidth(int width) {
		super.setMinimumWidth(width);
		this.width = width;
	}
	
	public void setColour(int colour) {
		this.colour = colour;
	}
	
	protected void onDraw(Canvas canvas) {
		
		float radius;
		if (this.height > this.width) {
			radius = this.width / 2.5f;
		} else {
			radius = this.height / 2.5f;
		}
		
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		p.setStrokeWidth(20);
		p.setColor(this.colour);
		canvas.drawColor(Color.WHITE);
		canvas.drawLine(0, this.height/2, this.width, this.height/2, p);
		canvas.drawCircle(this.width/2, this.height/2, radius, p);
		
		p.setColor(Color.WHITE);
		canvas.drawCircle(this.width/2, this.height/2, radius/1.2f, p);
		
		p.setColor(Color.BLACK);
		p.setStrokeWidth(1);
		p.setTextSize(20);
		canvas.drawText(this.stationName, this.height/2, this.width/4, p);
		
	}
	
}
