import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClock extends JPanel implements Runnable{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	JLabel timeView = new JLabel("00:00:00",JLabel.CENTER);	
	String txt ="";
	public DigitalClock() {}
	public DigitalClock(String txt) {
		this.txt = txt;
		add(timeView);
	}
	public void run() {	
		while(true) {
			TimeZone timezone = TimeZone.getTimeZone(txt);
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("HH:mm:ss");
			format.setTimeZone(timezone);
			String txt = format.format(date);
			timeView.setFont(fnt);
			timeView.setText(txt);
			try {Thread.sleep(1000);}catch(Exception e) {}
	
		}
	}
}
