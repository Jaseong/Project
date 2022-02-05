package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CupGame extends GameContainer {

	static ImageIcon backIcon = new ImageIcon("images/background.png");
	static ImageIcon gameBagIcon = new ImageIcon("images/cupgamebackImg.png");
	static ImageIcon cupIcon = new ImageIcon("images/cup.png");
	static ImageIcon ballIcon = new ImageIcon("images/ball.png");

	static JLabel backLabel;
	static JLabel gameBackLabel;

	static JButton playBtn;

	static Timer timer;

	static int startBtn;

	static int click0 = 0;
	static int click1 = 0;
	static int click2 = 0;

	static JLabel[] balls = new JLabel[3];

	static Cup[] cups = new Cup[3];

	public CupGame() {
		gamePlay();
	}

	@Override
	public void gamePlay() {

		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);

		// 시작하기 버튼
		playBtn = new JButton("시작하기");
		playBtn.setBounds(435, 530, 150, 50);
		playBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (startBtn == 1) {
					return;
				}

				cupUpDown();

				startBtn++;
			}
		});

		// 컵 생성
		for (int i = 0; i < cups.length; i++) {
			cups[i] = new Cup();
			cups[i].addActionListener(this);
			this.add(cups[i]);
		}

		cups[0].x = 230;
		cups[1].x = 430;
		cups[2].x = 630;

		// 컵 위치
		cups[0].setBounds(cups[0].x, cups[0].y, cups[0].w, cups[0].h);
		cups[1].setBounds(cups[1].x, cups[1].y, cups[0].w, cups[0].h);
		cups[2].setBounds(cups[2].x, cups[2].y, cups[0].w, cups[0].h);

		// 공 생성
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new JLabel(ballIcon);
			this.add(balls[i]);
			if (!(i == 1)) {
				balls[i].setVisible(false);
			}
		}

		// 공 위치
		balls[0].setBounds(280, 350, 50, 50);
		balls[1].setBounds(480, 350, 50, 50);
		balls[2].setBounds(680, 350, 50, 50);

		// 흰색 배경
		gameBackLabel = new JLabel(gameBagIcon);
		gameBackLabel.setBounds(50, 20, 900, 700);

		// 초록 배경
		backLabel = new JLabel(backIcon);
		backLabel.setBounds(0, 0, 1024, 768);

		this.add(playBtn);
		this.add(gameBackLabel);
		this.add(backLabel);
	}

	// 컵 내리기
	public void cupUpDown() {

		timer = new Timer();

		// 3초 카운트
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 8;

			public void run() {

				if (i > 4) {

					cups[1].y -= 25;

					cups[1].setBounds(cups[1].x, cups[1].y, cups[1].w, cups[1].h);

					i--;

				} else if (i <= 4 && i > 0) {

					cups[1].y += 25;

					cups[1].setBounds(cups[1].x, cups[1].y, cups[1].w, cups[1].h);

					i--;

				} else if (i == 0) {

					timer.cancel();

					balls[1].setVisible(false); // 컵이 내려간 후 공 지우기

					changeCup();
				}

			}
		}, 0, 50);
	}

	// 컵 올리기
	public void cupUp(int index) {

		timer = new Timer();

		// 3초 카운트
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 3;

			public void run() {

				// 들어오는 인덱스에 따른 컵 올리기
				if (index == 0) {
					cups[0].y -= 25;
					cups[0].setBounds(cups[0].x, cups[0].y, cups[0].w, cups[0].h);
				}

				else if (index == 1) {
					cups[1].y -= 25;
					cups[1].setBounds(cups[1].x, cups[1].y, cups[0].w, cups[0].h);
				}

				else if (index == 2) {
					cups[2].y -= 25;
					cups[2].setBounds(cups[2].x, cups[2].y, cups[0].w, cups[0].h);
				}

				i--;

				if (i < 0) {

					timer.cancel();

					if (index == 0) {
						click0++;
					}

					// 1번 컵의 x 위치에 맞는 공 보여주기
					else if (index == 1) {
						click1++;
						if (cups[1].getX()==230) {
							balls[0].setVisible(true);
						} else if (cups[1].getX()==430 ) {
							balls[1].setVisible(true);
						} else if (cups[1].getX()==630 ) {
							balls[2].setVisible(true);
						}
					}

					else if (index == 2) {
						click2++;
					}
				}
			}
		}, 0, 50);
	}

	// 컵 섞기
	public void changeCup() {
		
		int r = 100;
		
		CupRoad cr = new CupRoad();
		
		cups[0].road = cr.cupRoadArr[0];
		cups[1].road = cr.cupRoadArr[1];
		cups[2].road = cr.cupRoadArr[2];
		
		CupThread ct = new CupThread(cups[0], cups[1], cups[2], r);
		
		ct.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();

		if (cups[0]==btn) {
			if (click0 == 1) {
				return;
			}
			cupUp(0);
		} else if (cups[1].equals(btn)) {
			if (click1 == 1) {
				return;
			}
			cupUp(1);
		} else if (cups[2].equals(btn)) {
			if (click2 == 1) {
				return;
			}
			cupUp(2);
		}
	}

}