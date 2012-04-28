package com.djdduty.wgc01.game;

import com.djdduty.wgc01.core.Game;

public class Main {
	public static void main(String[] args) throws Exception {
		Game game = new Game(new MainState(), 800, 600);
		game.setTitle("Wild Game Challenge 01");
		game.init();
	}
}
