package com.djdduty.wgc01.core;

public class StateManager {
	private Game game;
	private State activeState;
	
	public StateManager(Game game, State state) {
		activeState = state;
		this.game = game;
	}
	
	public void start() {
		activeState.start(this);
	}
	
	public void update(long time) {
		activeState.update(time);
	}
	
	public void draw() {
		activeState.draw();
	}
	
	public void changeState(State newState) {
		if(newState == null)
			game.writeMessage("New screen cannot be null!");
		else
		activeState = newState;
		start();
	}
	
	public State getActiveState() {
		return activeState;
	}
	
	public Game getGame() {
		return game;
	}
}
