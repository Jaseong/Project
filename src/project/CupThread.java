package project;

import javax.swing.JLabel;

public class CupThread extends Thread {
	Cup[] cup = new Cup[3];
	int r;
	JLabel manualJLabel;

	public CupThread(Cup[] cup, int r, JLabel manualJLabel) {
		this.cup = cup;
		this.r = r;
		this.manualJLabel = manualJLabel;
	}
	
	@Override
	public void run() {

		int i = 0;
		int cup1x = cup[0].x;
		int cup2x = cup[1].x;
		int cup3x = cup[2].x;

		int y = 300;

		int roop = 0;

		cup[0].now = cup[0].road[0];
		cup[0].next = cup[0].road[1];
		cup[1].now = cup[1].road[0];
		cup[1].next = cup[1].road[1];
		cup[2].now = cup[2].road[0];
		cup[2].next = cup[2].road[1];
	

		int d1 = cup[0].next - cup[0].now;
		int d2 = cup[1].next - cup[1].now;
		int d3 = cup[2].next - cup[2].now;

		while (true) {

			if (i < 181) {

				road(cup[0], d1, cup1x, i);
				road(cup[1], d2, cup2x, i);
				road(cup[2], d3, cup3x, i);

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				cup[0].setBounds(cup[0].x, cup[0].y, cup[0].w, cup[0].h);
				cup[1].setBounds(cup[1].x, cup[1].y, cup[1].w, cup[1].h);
				cup[2].setBounds(cup[2].x, cup[2].y, cup[2].w, cup[2].h);
			}

			if (roop == 8) {
				for(int j = 0; j < cup.length; j++) {
					cup[j].setEnabled(true);
				}
				System.out.println(cup[1].x);
				manualJLabel.setVisible(true);
				break;
			}
			if (i < 181) {
				i++;
			} else if (i == 181) {
				roop++;

				cup1x = cup[0].x;
				cup2x = cup[1].x;
				cup3x = cup[2].x;

				cup[0].now = cup[0].road[roop];
				cup[0].next = cup[0].road[roop + 1];

				cup[1].now = cup[1].road[roop];
				cup[1].next = cup[1].road[roop + 1];

				cup[2].now = cup[2].road[roop];
				cup[2].next = cup[2].road[roop + 1];

				d1 = cup[0].next - cup[0].now;
				d2 = cup[1].next - cup[1].now;
				d3 = cup[2].next - cup[2].now;

				i = 0;
			}
		} // end of while
	} // end of run

	public void road(Cup cup, int d, int x, int i) {
		if (d != 0) {
			cup.x = (x + d * r) - (int) (d * r * Math.cos(Math.toRadians(i)));
			cup.y = (300 - (int) (d * r * Math.sin(Math.toRadians(i))));
		} else {
			cup.y = (300 - (int) (r * Math.sin(Math.toRadians(i))));
		}
	}

}