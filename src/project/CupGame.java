package project;

import java.awt.Color;
import java.awt.Font;
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
	static ImageIcon checkIcon = new ImageIcon("images/checked.png");
	static ImageIcon pauseIcon = new ImageIcon("images/pause.png");
	static ImageIcon xIcon = new ImageIcon("images/x.png");
	
	static ImageIcon cupBorderIcon = new ImageIcon("images/cup_stroke.png");

	static JLabel backLabel;
	static JLabel gameBackLabel;
	static JLabel checkLabel;
	static JLabel xLabel;
	static JLabel manualLabel;

	static JButton pauseBtn;
	static JButton playBtn;

	static Timer timer;
	static javax.swing.Timer otherCupTtimer;

	static int startBtn;

	static int click = 0;

	static boolean flag;

	static JLabel[] balls = new JLabel[3];

	static Cup[] cups = new Cup[3];

	public CupGame() {
		
		playBtn = new RoundJButton("시작하기");
		pauseBtn = new JButton(pauseIcon);
		playBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		
		gamePlay();
	}

	@Override
	public void gamePlay() {

		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);

		// 설명 텍스트
		manualLabel = new JLabel("공이 들어있는 컵을 선택하세요");
		manualLabel.setBounds(230, 150, 550, 50);
		manualLabel.setHorizontalAlignment(JLabel.CENTER);
		manualLabel.setFont(new Font("맑은고딕", Font.BOLD, 25));

		// 엑스 이미지
		xLabel = new JLabel(xIcon);
		xLabel.setBounds(750, 20, 150, 150);
		xLabel.setVisible(false);

		// 체크 이미지
		checkLabel = new JLabel(checkIcon);
		checkLabel.setBounds(750, 20, 150, 150);
		checkLabel.setVisible(false);

		// 일시정지 버튼
		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false);
		pauseBtn.setContentAreaFilled(false);

		// 시작하기 버튼
		playBtn.setBounds(430, 480, 150, 50);
		playBtn.setBackground(Color.ORANGE);
		playBtn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		
		// 초록 배경
		backLabel = new JLabel(backIcon);
		backLabel.setBounds(0, 0, 1024, 768);
		
		for (int i = 0; i < cups.length; i++) {
			cups[i] = new Cup();
			cups[i].setIcon(cupIcon);
			cups[i].setDisabledIcon(cupIcon);
			cups[i].setEnabled(false);
			cups[i].addActionListener(this);
			cups[i].setBackground(Color.white);
			cups[i].setFocusPainted(false);
			cups[i].setBorder(null);

			backLabel.add(cups[i]);
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
			backLabel.add(balls[i]);
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

		backLabel.add(manualLabel);
		backLabel.add(checkLabel);
		backLabel.add(xLabel);
		backLabel.add(pauseBtn);
		backLabel.add(playBtn);
		backLabel.add(gameBackLabel);
		
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
						click++;
					}

					// 1번 컵의 x 위치에 맞는 공 보여주기
					else if (index == 1) {
						click++;
						if (cups[1].getX() == 230) {
							balls[0].setVisible(true);
						} else if (cups[1].getX() == 430) {
							balls[1].setVisible(true);
						} else if (cups[1].getX() == 630) {
							balls[2].setVisible(true);
						}
					}

					else if (index == 2) {
						click++;
					}

				}
			}
		}, 0, 50);
	}

	//
	public void otherCupUp(int index1, int index2) {
		otherCupTtimer = new javax.swing.Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (index1 == 1) {
					if (cups[1].getX() == 230) {
						balls[0].setVisible(true);
					} else if (cups[1].getX() == 430) {
						balls[1].setVisible(true);
					} else if (cups[1].getX() == 630) {
						balls[2].setVisible(true);
					}
				}

				cups[index1].y -= 25;
				cups[index1].setBounds(cups[index1].x, cups[index1].y, cups[index1].w, cups[index1].h);

				cups[index2].y -= 25;
				cups[index2].setBounds(cups[index2].x, cups[index2].y, cups[index2].w, cups[index2].h);

				if (cups[index1].y == 200) {
					otherCupTtimer.stop();
				}

			}
		});
		otherCupTtimer.start();
	}

	// 컵 섞기
	public void changeCup() {

		int r = 100;

		CupRoad cr = new CupRoad();

		cups[0].road = cr.cupRoadArr[0];
		cups[1].road = cr.cupRoadArr[1];
		cups[2].road = cr.cupRoadArr[2];

		CupThread ct = new CupThread(cups[0], cups[1], cups[2], r, manualLabel, cups);

		ct.start();
	}

	public void labelBorder(boolean flag, JLabel JLabel, JButton JButton) {
		flag = true;
		JLabel.setVisible(flag);
		JButton.setIcon(cupBorderIcon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();

		if (cups[0] == btn) {
			if (click == 1) {
				return;
			}
			cupUp(0);
			labelBorder(flag, xLabel, cups[0]);
			otherCupUp(1, 2);
		} else if (cups[1].equals(btn)) {
			if (click == 1) {
				return;
			}
			cupUp(1);
			labelBorder(flag, checkLabel, cups[1]);
			otherCupUp(0, 2);
		} else if (cups[2].equals(btn)) {
			if (click == 1) {
				return;
			}
			cupUp(2);
			labelBorder(flag, xLabel, cups[2]);
			otherCupUp(1, 0);
			
		} else if (playBtn.equals(btn)) {
			if (startBtn == 1) {
				return;
			}

			for (int i = 0; i < cups.length; i++) {
				cups[i].setEnabled(true);
			}
			
			playBtn.setVisible(false);
			manualLabel.setVisible(false);
			cupUpDown();

			startBtn++;
			
		} else if (pauseBtn.equals(btn)) {
			
		}
	}

}