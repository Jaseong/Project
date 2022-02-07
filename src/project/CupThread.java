package project;

import javax.swing.JLabel;

public class CupThread extends Thread {
	Cup cup1;
	Cup cup2;
	Cup cup3;
	int r;
	JLabel manualJLabel;

	public CupThread(Cup cup1, Cup cup2, Cup cup3, int r,JLabel manualJLabel) {
		this.cup1 = cup1;
		this.cup2 = cup2;
		this.cup3 = cup3;
		this.r = r;
		this.manualJLabel = manualJLabel;
	}

	@Override
	public void run() {

		int i = 0;
		int cup1x = cup1.x;
		int cup2x = cup2.x;
		int cup3x = cup3.x;

		int y = 300;

		int roop = 0;

		cup1.now = cup1.road[0];
		cup1.next = cup1.road[1];
		cup2.now = cup2.road[0];
		cup2.next = cup2.road[1];
		cup3.now = cup3.road[0];
		cup3.next = cup3.road[1];

		int d1 = cup1.next - cup1.now;
		int d2 = cup2.next - cup2.now;
		int d3 = cup3.next - cup3.now;

		while (true) {

			if (i < 181) {
				
				road(cup1, d1, cup1x, i);
				road(cup2, d2, cup2x, i);
				road(cup3, d3, cup3x, i);
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				cup1.setBounds(cup1.x, cup1.y, cup1.w, cup1.h);
				cup2.setBounds(cup2.x, cup2.y, cup2.w, cup2.h);
				cup3.setBounds(cup3.x, cup3.y, cup3.w, cup3.h);
			}

			if (roop == 8) {
				System.out.println(cup2.x);
				manualJLabel.setVisible(true);
				break;
			}
			if (i < 181) {
				i++;
			} else if (i == 181) {
				roop++;
				
				cup1x = cup1.x;
				cup2x = cup2.x;
				cup3x = cup3.x;
				
				cup1.now = cup1.road[roop];
				cup1.next = cup1.road[roop + 1];

				cup2.now = cup2.road[roop];
				cup2.next = cup2.road[roop + 1];

				cup3.now = cup3.road[roop];
				cup3.next = cup3.road[roop + 1];

				d1 = cup1.next - cup1.now;
				d2 = cup2.next - cup2.now;
				d3 = cup3.next - cup3.now;
				
				i = 0;
			}
		} // end of while
	} // end of run
	
	public void road(Cup cup, int d, int x, int i ) {
		if (d != 0) {
			cup.x = (x + d * r) - (int) (d * r * Math.cos(Math.toRadians(i)));
			cup.y = (300 - (int) (d * r * Math.sin(Math.toRadians(i))));
		} else {
			cup.y = (300 - (int) (r * Math.sin(Math.toRadians(i))));
		}
	}
	
	
}