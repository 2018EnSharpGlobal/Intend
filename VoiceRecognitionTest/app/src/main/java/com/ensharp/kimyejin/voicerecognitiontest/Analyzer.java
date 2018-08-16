package com.ensharp.kimyejin.voicerecognitiontest;

import android.widget.EditText;

import java.util.Scanner;

public class Analyzer {

	private MainActivity main;

	private Scanner reader = new Scanner(System.in);
	private String line;
	private String[] words;
	private String departuralJosa = "에서";
	private String[] josas = {"에서", "에", "으로", "로", "을", "를"};

	private String departure;
	private String destination;
	private String intend;	
	
	public Analyzer(MainActivity main) {
		this.main = main;
	}

	public void analyzeLine(String text) {
		clearAllInformation();
		line = text;

	}
	
	public void clearAllInformation() {
		line = "";
		departure = "";
		destination = "";
		intend = "";
	}
	
	public void analyzeWords() {		
		words = line.split(" ");
		
		for (int i = 0; i < words.length; i++) {
			separateIntoMorpheme(words[i]);
		}
	}
	
	public void separateIntoMorpheme(String word) {
		for (int i = 0; i < josas.length; i++) {
			if (word.contains(josas[i])) {
				if (word.indexOf(josas[i]) == word.length() - josas[i].length())
				{
					destination = word.substring(0, word.indexOf(josas[i]));
					return;
				}	
			}

			checkNoun(word);
			checkVerb(word);
		}
		
		if (word.contains(departuralJosa)) {
			if (word.indexOf(departuralJosa) == word.length() - departuralJosa.length())
				departure = word.substring(0, word.indexOf(departuralJosa));
		}
	}
	
	public void printWords() {
		StringBuilder stringBuilder = new StringBuilder();

		if (departure.isEmpty())
			departure = "없음";
		stringBuilder.append(String.format("출발지 : %s\n", departure));

		if (destination.isEmpty())
			destination = "없음";
		stringBuilder.append(String.format("도착지 : %s\n", destination));
		
		if (intend.isEmpty())
			intend = "없음";
		stringBuilder.append(String.format("의도 : %s\n", intend));

		((EditText)main.findViewById(R.id.detailInformation)).setText(stringBuilder.toString());
	}

	public void checkPoints() {
		departure = checkPoint(departure);
		destination = checkPoint(destination);
	}
	
	public boolean isLackOfInformation() {
		if (departure.isEmpty() || intend.isEmpty()) return true;
		return false;
	}
	
	public String checkPoint(String word) {
		int count = DBManager.count("noun", "name=\"" + word + "\"");
		String original;
		
		if (count > 0) {
			original = DBManager.getOriginal("noun", "name=\""+word+"\"");
			
			return original;
		}
		else return "없음";
	}
	
	public void checkNoun(String word) {
		int count = DBManager.count("noun", "name=\"" + word + "\"");
		
		if (count > 0)
			destination = DBManager.getOriginal("noun", "name=\"" + word + "\"");
	}
	
	public void checkVerb(String word) {
		int count = DBManager.count("verb", "name=\"" + word + "\"");
		
		if (count > 0) 
			intend = DBManager.getOriginal("verb", "name=\""+word+"\"");
	}
}
