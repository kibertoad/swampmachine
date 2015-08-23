package net.kiberion.rlforj.examples;

import java.util.Random;

import net.kiberion.rlforj.los.ConePrecisePremisive;
import net.kiberion.rlforj.los.IConeFovAlgorithm;
import net.kiberion.rlforj.los.ShadowCasting;

public class ConeFovExample
{

	public static void main(String[] args)
	{
		ExampleBoard b = new ExampleBoard(21, 21);
		Random rand=new Random();
		for(int i=0; i<30; i++) {
			b.setObstacle(rand.nextInt(21), rand.nextInt(21));
		}
//		int startAngle=rand.nextInt(360), finishAngle=rand.nextInt(360);
		int startAngle=30, finishAngle=70;
		System.out.println(startAngle+" degrees to "+finishAngle+" degrees");
		System.out.println("ShadowCasting");
		IConeFovAlgorithm a=new ShadowCasting();
		a.visitConeFieldOfView(b, 10, 10, 9, startAngle, finishAngle);
		b.print(10, 10);
		
		b.resetVisitedAndMarks();
		System.out.println("Precise Permissive");
		a=new ConePrecisePremisive();
		a.visitConeFieldOfView(b, 10, 10, 10, startAngle, finishAngle);
		b.print(10, 10);
	}
}
