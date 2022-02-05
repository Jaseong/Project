package project;

public class CupThread extends Thread {
	Cup cup1;
	Cup cup2;
	Cup cup3;
	int r;

	public CupThread(Cup cup1, Cup cup2, Cup cup3, int r) {
		this.cup1 = cup1;
		this.cup2 = cup2;
		this.cup3 = cup3;
		this.r = r;
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

			if (i < 180) {
				if (d1 != 0) {
					cup1.x = (cup1x + d1 * r) - (int) (d1 * r * Math.cos(i * Math.PI / 180));
					cup1.y = (y - (int) (d1 * r * Math.sin(i * Math.PI / 180)));
				} else {
					cup1.y = (y - (int) (r * Math.sin(i * Math.PI / 180)));
				}
				if (d2 != 0) {
					cup2.x = (cup2x + d2 * r) - (int) (d2 * r * Math.cos(i * Math.PI / 180));
					cup2.y = (y - (int) (d2 * r * Math.sin(i * Math.PI / 180)));
				} else {
					cup2.y = (y - (int) (r * Math.sin(i * Math.PI / 180)));
				}
				if (d3 != 0) {
					cup3.x = (cup3x + d3 * r) - (int) (d3 * r * Math.cos(i * Math.PI / 180));
					cup3.y = (y - (int) (d3 * r * Math.sin(i * Math.PI / 180)));
				} else {
					cup3.y = (y - (int) (r * Math.sin(i * Math.PI / 180)));
				}

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
				break;
			}
			if (i < 180) {
				i++;
			} else if (i == 180) {
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
		}
	}
}