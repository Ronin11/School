package main;

import java.util.Random;

import algos.Algo;
import algos.DynamicProgramming;
import algos.Recursive;
import algos.RecursiveMemoized;

public class Main {
	
	public static void main(String[] args) {
		firstTest();
		System.out.println("Second Test");
		secondTest();

	}
	
	public static void firstTest(){
		for(int i = 0; i < 10; i++){
			Random rand = new Random();
			int a = rand.nextInt(5);
			int b = rand.nextInt(5);
			int c = rand.nextInt(5);
			Algo algo1 = new Recursive();
			Algo algo2 = new RecursiveMemoized(a, b, c);
			Algo algo3 = new DynamicProgramming(a, b, c);
			System.out.println(algo1.win(a, b, c));
			System.out.println(algo2.win(a, b, c));
			System.out.println(algo3.win(a, b, c));
			System.out.println();
		}
	}
	
	public static void secondTest(){
		for(int i = 0; i < 10; i++){
			Random rand = new Random();
			int a = rand.nextInt(100);
			int b = rand.nextInt(100);
			int c = rand.nextInt(100);
			Algo algo2 = new RecursiveMemoized(a, b, c);
			Algo algo3 = new DynamicProgramming(a, b, c);
			System.out.println(algo2.win(a, b, c));
			System.out.println(algo3.win(a, b, c));
			System.out.println();
		}
	}

}
