package wctool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WC {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Charset.defaultCharset().displayName());
		// This property refers to the default character
		// encoding used by JVM for I/O operations
		System.out.println(System.getProperty("file.encoding"));
		// This property refers to the character encoding used by the native
		// platform, i.e., the operating system. It's relevant for interactions
		// with the operating system, such as executing external commands or interacting
		// with the file system.
		System.out.println(System.getProperty("sun.jnu.encoding"));
		System.out.println("😃😃");
		System.out.println(Charset.availableCharsets());

		boolean c = false;
		boolean l = false;
		boolean w = false;
		boolean m = false;
		args = new String[] { "ccwc", "\\C:\\Users\\KIIT\\Desktop\\test.txt" };
		System.out.println(Arrays.toString(args));
		if ("ccwc".equalsIgnoreCase(args[0])) {
			if (args.length == 2) {
				c = l = w = m = true;
			} else {
				for (int i = 1; i < args.length - 1; i++) {
					if ("-c".equalsIgnoreCase(args[i])) {
						c = true;
					} else if ("-l".equalsIgnoreCase(args[i])) {
						l = true;
					} else if ("-w".equalsIgnoreCase(args[i])) {
						w = true;
					} else if ("-m".equalsIgnoreCase(args[i])) {
						m = true;
					} else {
						throw new Exception(args[i] + " is not a command line option");
					}
				}
			}
			String output = solve(c, l, w, m, args[args.length - 1]);
			System.out.println(output);
		}

	}

	public static String solve(boolean c, boolean l, boolean w, boolean m, String fileName) {
		StringBuilder output = new StringBuilder();
		File file = new File(fileName);
		int lines = 0;
		int words = 0;
		long characters = 0;
		boolean word = false;
		try (BufferedReader r = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8), 1024)) {

			int k;
			while ((k = r.read()) != -1) {
				char ch = (char) k;
				characters++;
				if (ch <= 32) {
					if (ch == '\n') {
						lines++;
					}
					if (word) {
						word = false;
						words++;
					}
				} else if (!word) {
					word = true;
				}
			}
			if (word) {
				words++;
			}

			if (l) {
				output.append(lines);
				output.append(" ");
			}
			if (w) {
				output.append(words);
				output.append(" ");
			}
			if (m) {
				output.append(characters);
				output.append(" ");
			}
			if (c) {
				output.append(file.length());
				output.append(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		output.append(file.getName());
		return new String(output);
	}

}
