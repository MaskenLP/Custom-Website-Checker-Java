package de.NiDaKu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Main {

	public static void main(String[] args) throws IOException {

		try {
			AnsiConsole.systemInstall();
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(GREEN).a(" : Starting Checker ...").reset());
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(CYAN).a(" : by MaskenLP Version 1.0").reset());

			ArrayList<String> Output = new ArrayList<String>();

			String path = "in.txt";
			String path2 = "out.txt";
			ArrayList<String> Temp = new ArrayList<String>();
			

			File file = new File(path);
			try {
				Scanner s = new Scanner(file);

				while (s.hasNextLine()) {
					Temp.add(s.nextLine());

				}
				s.close();

			} catch (FileNotFoundException e) {
				System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(RED).a(" : The File in.txt was not found! Generating!").reset());
				File f = new File(path);
				try {
					f.createNewFile();
				} catch (IOException e1) {
					System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(RED).a(" : The file in.txt was not found! Could not create file! Is the disk full or do you have forgot chmod all the permissions given the server ? Or maybe you have to create the folders :D If so and you have Linux type : apt-get coffe :D").reset());
					
				}
			}

			String test = Temp.get(0);
			Temp.remove(0);
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(YELLOW).a(" : Checking for the following  String: ").fg(MAGENTA).a(test).reset());
			
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(GREEN).a(" : Catching all Malformed URLs!").fg(YELLOW).a(" It might take a long time ...").reset());
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(GREEN).a(" : =========================================================================================").reset());
			for (int ix = 0; ix <= Temp.size() - 1; ix++) {
				try {
					URL url;
					InputStream input;
					url = new URL(Temp.get(ix));

					input = url.openStream();

					input.close();
				} catch (Exception ex) {
					System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(RED).a(" : Error the following URL does not exist! : ").fg(MAGENTA).a(ex.getMessage()).fg(RED).a(" The URL will be ignored .... This time").reset());
					Temp.remove(ix);
				}

			}
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(GREEN).a(" : =========================================================================================").reset());
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(YELLOW).a(" : =========================================================================================").reset());

			for (int i = 0; i <= Temp.size() - 1; i++) {

				boolean contains = false;

				URL yahoo = new URL(Temp.get(i));
				BufferedReader in = new BufferedReader(new InputStreamReader(yahoo.openStream()));

				String inputLine;

				while ((inputLine = in.readLine()) != null) {

					if (inputLine.contains(test)) {
						contains = true;
					}
				}
				in.close();
				if (contains) {
					System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(YELLOW).a(" : Checking ").fg(MAGENTA).a(Temp.get(i)).fg(YELLOW).a(" ... Awnser is: ").fg(GREEN).a(contains).reset());
				} else {
					System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(YELLOW).a(" : Checking ").fg(MAGENTA).a(Temp.get(i)).fg(YELLOW).a(" ... Awnser is: ").fg(RED).a(contains).reset());
				}
				Output.add(Temp.get(i) + " " + contains);
			}
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(YELLOW).a(" : =========================================================================================").reset());
			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(GREEN).a(" : Writing results to file ...").reset());
			File ff = new File(path2);
			FileOutputStream fo = new FileOutputStream(ff);
			PrintWriter pw = new PrintWriter(fo);

			for (String elem : Output) {
				pw.println(elem);
			}
			pw.close();
			fo.close();

			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(GREEN).a(" : Stopping ...").reset());
			AnsiConsole.systemUninstall();
		} catch (Exception e) {

			System.out.println(ansi().fg(BLUE).a("[").fg(CYAN).a("Webseite-Check").fg(BLUE).a("]").fg(RED).a(" : Error The following Error occoured: ").fg(MAGENTA).a(e.getMessage()).fg(RED).a("  Attention! It could be that the results would not appear in the out.txt").reset());

		}
	}
}
