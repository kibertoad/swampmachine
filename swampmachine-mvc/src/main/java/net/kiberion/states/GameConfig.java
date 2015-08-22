package net.kiberion.states;

import com.google.inject.Singleton;

import lombok.Getter;
import lombok.Setter;

@Singleton
public class GameConfig {

	@Setter
	@Getter
	private boolean musicEnabled;
	
}
