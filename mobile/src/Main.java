import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Affichage TA = new Affichage("AAA");
		Affichage TB = new Affichage("BB");
		Affichage TC = new Affichage("CCCCCC");
		Affichage TD = new Affichage("DDDDD");
		semaphore sem = new semaphoreBinaire(1);



		TB.start();

		TA.start();
		TC.start();
		TD.start();

	}

}
