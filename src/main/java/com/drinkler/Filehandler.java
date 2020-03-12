// package com.drinkler;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.ArrayList;
// import java.util.HashMap;

// public class Filehandler {

// private int MAX_ITEMS = 100; // The input file can have a max of MAX_ITEMS
// lines
// private int MAX_IDENT = 100; // Max length of the node identifier/name

// // ID of Node, and the Node itself
// private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

// public void readFile(String filename) throws IOException {
// try {
// checkMaxLinesInFile(filename);
// } catch (IOException e) {
// e.printStackTrace();
// }

// File file = new File("res/" + filename);
// FileReader fr = new FileReader(file);
// BufferedReader br = new BufferedReader(fr);

// String line;
// while ((line = br.readLine()) != null) {

// // Kommentarzeilen beginnen mit „//“
// if (line.contains("//") || line.contains("{") || line.contains("}"))
// continue;

// // Leerzeilen, führende Tabs oder Blanks werden ignoriert
// String[] lineSplitted = line.trim().split(" ");
// int lastElement = lineSplitted.length - 1;
// lineSplitted[lastElement] = lineSplitted[lastElement].replace(";", "");

// int value = Integer.parseInt(lineSplitted[lastElement]);

// // "[A-Z][A-Z,0-9]{0," + (MAX_IDENT - 1) + "}"
// // Die Graph- bzw. Knotenbezeichner beginnen mit einem Buchstaben gefolgt von
// // alphanumerischen Zeichen bis zu einer über die Präprozessorkonstante
// // MAX_IDENT vorgegebenen Maximallänge

// // Zeilen mit Definition der Knotengewichte und Zeilen mit Definition einer
// // Kante können bel. gemischt werden.
// String regex = "[A-Z][A-Z,0-9]{0," + (MAX_IDENT - 1) + "}";
// // Edges
// if (lineSplitted[0].matches(regex) && lineSplitted[1].matches("-") &&
// lineSplitted[2].matches(regex)) {
// // TODO: check if node already exists, then create new node with id -1 or
// save
// // TODO node in link
// // getNodeById(getNodeIdByName(lineSplitted[0])).setLink(lineSplitted[2],
// // value);
// // getNodeById(getNodeIdByName(lineSplitted[2])).setLink(lineSplitted[0],
// // value);

// }
// // Nodes
// else if (lineSplitted[0].matches(regex)) {
// // TODO: check if node already exists without id, then save id or new node
// // nodes.add(new Node(value, lineSplitted[0]));

// nodes.put(value, new Node(value, lineSplitted[0]));
// } else {
// System.err.println("Falsch formatierte Knotenbezeichner oder Überschreitung
// der Maximallänge von "
// + MAX_IDENT + "!");
// System.exit(2);
// }
// }
// br.close();
// }

// /*
// * Checks if the file doesn't exceeds MAX_ITEMS of lines
// */
// private void checkMaxLinesInFile(String filename) throws IOException {
// Path path = Paths.get("res/" + filename);
// if (Files.lines(path).count() > MAX_ITEMS) {
// System.err.println("Die Eingabedatei hat zu viele Zeilen. Erlaubtes Maximum
// ist " + MAX_ITEMS + "!");
// System.exit(1);
// }
// }

// }