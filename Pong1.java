package pong1;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class Pong1 extends PApplet {
	
	public float rng() {
		float x = random(-5,5);
		if (-2.5 < x && x < 0) {
			x -= 2;
		}
		if (0 < x && x < 2.5) {
			x += 2;
		}
		return(x);
	}
	
	int gamescreen = 1;
	float player1Y = 380;
	float player2Y = 380;
	double player1V = 0;
	double player2V = 0;
	float ballX = 497;
	float ballY = random(150,750);
	float ballYV = rng();
	float ballXV = rng();
	int player1Score = 0;
	int player2Score = 0;
	
	public void settings() {
		
		size(1000,800);
		
	}

	public void setup() {
		
		PFont f = createFont("AtariST8x16SystemFont.ttf",10);
		textFont(f);
		
	}

	public void draw() {
		
		if (gamescreen == 2 || gamescreen == 7) {
			
			ballX += ballXV;
			ballY += ballYV;
			if (ballY <= 0 || ballY >= 794) {
				ballYV *= -1;
			}
			
			if ((ballY + 3) >= player1Y - 4 && (ballY + 3) <= (player1Y + 44) && ballX <= 108 && ballX >= 102) {
				ballXV *= -1;
			}
			else if (ballX <= 0) {
				player2Score += 1;
				ballX = 497;
				ballY = random(100,800);
				ballYV = rng();
				ballXV = rng();
			}
			if ((ballY + 3) >= player2Y - 4 && (ballY + 3) <= (player2Y + 44) && ballX >= 885 && ballX <= 891) {
				ballXV *= -1;
			}
			else if (ballX >= 1000) {
				player1Score += 1;
				ballX = 497;
				ballY = random(100,800);
				ballYV = rng();
				ballXV = rng();
			}
			
			if (player1Score == 10 || gamescreen == 4) {
				gamescreen = 4;
			}
			if (player2Score == 10 || gamescreen == 5) {
				gamescreen = 5;
			}
			
			fill(0,0,0,50);
			rect(0,0,width,height);
			fill(255);
			rect(ballX,ballY,6,6);
			rect(100,player1Y,6,40);
			rect(890,player2Y,6,40);
			fill(200,200,200,80);
			
			textSize(80);
			text(player1Score,225,100);
			text(player2Score,725,100);
			
			stroke(255);
			strokeWeight(5);
			for (int i = 0; i <= 20; i++) {
				  float y = lerp(1, 801, (float) (i/20.0));
				  float y1 = lerp(8,808, (float) (i/20.0));
				  line(500,y,500,y1);
			}
			stroke(0);
			strokeWeight(1);
			
			player1Y += player1V;
			player2Y += player2V;
		}
		else if (gamescreen == 1){
			
			background(0);
			textSize(300);
			fill(255);
			text("Pong",200,350);
			textSize(50);
			text("Press any key to begin",225,500);
			textSize(40);
			text("Press 'c' to see controls",250,580);
			text("Press 'v' to see rules",260,650);
		}
		else if (gamescreen == 3) {
			background(0);
			textSize(100);
			fill(255);
			text("Controls",200,200);
			textSize(30);
			text("Player 1",100,400);
			text("To move up, press 'w'",100,450);
			text("To move down, press 's'",100,500);
			text("Player 2",600,400);
			text("To move up, press 'p'",600,450);
			text("To move down, press 'l'",600,500);
			text("press 'b' to return",100,700);
		}
		
		if (gamescreen == 4) {
			background(0);
			textSize(100);
			fill(255);
			text("Player 1 has won",100,250);
			textSize(50);
			text("press 'b' to play again",100,500);
			player1Score = 0;
			player2Score = 0;
			player1Y = 380;
			player2Y = 380;
			
		}
		if (gamescreen == 5) {
			background(0);
			textSize(100);
			fill(255);
			text("Player 2 has won",100,250);
			textSize(50);
			text("press 'b' to play again",100,500);
			player1Score = 0;
			player2Score = 0;
			player1Y = 380;
			player2Y = 380;
		}
		if (gamescreen == 6) {
			background(0);
			textSize(150);
			fill(255);
			text("Rules",100,220);
			textSize(30);
			text("Each player controls one pattle",100,400);
			text("The ball will start somewhere along the dotted line",100,450);
			text("in the middle",100,500);
			text("The ball will bounce off both the walls and the paddles",100,550);
			text("When a player gets it past the opponent's paddle,",100,600);
			text("that player is awarded one point",100,650);
			text("The first player that gets 10 points wins.",100,700);
			text("Press 'b' to go back",100,750);
		}
		if (gamescreen == 8) {
			background(0);
			fill(255);
			textSize(30);
			text("Nice job Sarah,",100,150);
			text("You have discovered my easter egg",100,200);
			text("The night has a thousand eyes,",100,300);
			text("and the day but one.",100,350);
			text("Yet the light of the bright world dies,",100,400);
			text("With the dying sun",100,450);
			text("The mind has a thousand eyes,",100,550);
			text("and the heart but one.",100,600);
			text("Yet the light of a whole life dies,",100,650);
			text("____ ____ __ ____.",100,700);
		}
	}
	
	public void keyPressed() {
		
		if (key == 'w') {
			player1V = -6;
		}
		if (key == 's') {
			player1V = 6;
		}
		if (key == 'p') {
			player2V = -6;
		}
		if (key == 'l') {
			player2V = 6;
		}
		
		if (gamescreen == 1 && key != 'c' && key != 'v') {
			gamescreen = 2;
			background(0);
		}
		else if (gamescreen == 1 && key == 'c') {
			gamescreen = 3;
		}
		if (gamescreen == 3 && key == 'b') {
			gamescreen = 1;
		}
		if (gamescreen == 4 || gamescreen == 5) {
			if (key == 'b') {
				gamescreen = 1;
			}
		}
		if (gamescreen == 1 && key == 'v') {
			gamescreen = 6;
		}
		if (gamescreen == 6 && key == 'b') {
			gamescreen = 1;
		}
		if (gamescreen == 2 && key == '6') {
			gamescreen = 7;
		}
		if (gamescreen == 7 && key == '9') {
			gamescreen = 8;
		}
	}
	
	public void keyReleased() {
		
		if (key == 'w' || key == 's') {
			player1V = 0;
		}
		if (key == 'p' || key == 'l') {
			player2V = 0;
		}
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { pong1.Pong1.class.getName() });
	}
}

//This program, excluding any referenced libraries, is proprietary information owned by Jason Wiemels.