package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import model.Stage;
import model.StageLobby;
import model.StageTitle;

public class GameManager {
	Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);
	private static String nextStage = "";
	private String curStage = "";

	Map<String, Stage> stageList = new HashMap<>();

	public static String getNextStage() {
		return nextStage;
	}

	public static void setNextStage(String nextStage) {
		GameManager.nextStage = nextStage;
	}

	public String getCurStage() {
		return curStage;
	}

	public void setCurStage(String curStage) {
		this.curStage = curStage;
	}

	GameManager() {
		stageList.put("TITLE", new StageTitle());
		stageList.put("BATTLE", new StageBattle());
		stageList.put("LOBBY", new StageLobby());
		
		nextStage = "TITLE";
	}

	boolean changeStage() {
		if (curStage.equals(nextStage))
			return true;

		curStage = nextStage;
		Stage stage = stageList.get(curStage);
		stage.init();

		boolean run = true;
		while (true) {
			run = stage.update();
			if (run == false)
				break;
		}

		if (nextStage.equals(""))
			return false;
		else
			return true;
	}
}
