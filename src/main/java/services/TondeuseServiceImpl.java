package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import domain.Coordonees;
import domain.Pelouse;
import domain.Tondeuse;
import exceptions.TondeuseException;

public class TondeuseServiceImpl implements TondeuseService{
	private final String COORDONNEES_REGEXP = "^\\d+ \\d+$";
	private final String TONDEUSE_REGEXP = "^\\d+ \\d+ [NEWS]{1}$";
	private final String INSTRUCTIONS_REGEXP = "^[ADG]+$";

	
	/**
	 * à partir d'un nom de fichier, vérifie sa validité et affiche dans la console la position finale des tondeuses
	 * 
	 * @param fileName
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws TondeuseException 
	 */
	public void startProgram(String fileName) throws TondeuseException {
		try {
			File file = getFileFromResource(fileName);
			if (isNumberOfLinesValid(file)) {

				try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
					String coordonneesPelouse = scanner.nextLine();
					Pelouse pelouse = parseCoordonnees(coordonneesPelouse);

					while (scanner.hasNextLine()) {
						String coordonneesTondeuse = scanner.nextLine().toUpperCase();
						Tondeuse tondeuse = parseTondeuse(coordonneesTondeuse);

						String instructions = scanner.nextLine().toUpperCase();
						if (instructions.matches(INSTRUCTIONS_REGEXP)) {
							tondeuse.start(instructions, pelouse);
						} else {
							throw new TondeuseException("Instructions erronnées");
						}
					}
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File getFileFromResource(String fileName) throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		return new File(resource.toURI());
	}

	/**
	 * renvoi une pelouse à partir des coordonnées du coin supérieur droit de la pelouse 
	 * 
	 * @param coordonnees
	 * @return pelouse
	 * @throws TondeuseException : si les coordonnées sont < 1 ou si le format est erroné
	 */
	private Pelouse parseCoordonnees(String coordonnees) throws TondeuseException {
		if (coordonnees.matches(COORDONNEES_REGEXP)) {

			String[] coordsArray = coordonnees.split("\\s");
			int x = Integer.valueOf(coordsArray[0]);
			int y = Integer.valueOf(coordsArray[1]);
			// >
			if (x > 0 && y > 0) {
				Coordonees coord = new Coordonees(x, y);
				return new Pelouse(coord);
			} else {
				throw new TondeuseException("Les coordonnées de la pelouse doivent être supérieure à 0");
			}
		} else {
			throw new TondeuseException("Les coordonnées de la pelouse ne sont pas au format requis(ex: \"5 5\")");
		}
	}

	/**
	 * renvoi une tondeuse à partir de ses coordonnées
	 * 
	 * @param coordonneesTondeuse
	 * @return tondeuse
	 * @throws TondeuseException : si les coordonnées ne sont pas au bon format
	 */
	private Tondeuse parseTondeuse(String coordonneesTondeuse) throws TondeuseException{
		if (coordonneesTondeuse.matches(TONDEUSE_REGEXP)) {
			String[] coordonneesTondeuseArray = coordonneesTondeuse.split("\\s");
			int tondeuseX = Integer.valueOf(coordonneesTondeuseArray[0]);
			int tondeuseY = Integer.valueOf(coordonneesTondeuseArray[1]);
			char tondeuseO = coordonneesTondeuseArray[2].charAt(0);
			Tondeuse tondeuse = new Tondeuse(tondeuseX, tondeuseY, tondeuseO);
			return tondeuse;
		} else {
			throw new TondeuseException("Coordonnées de la tondeuse erronnées");
		}
	}
	
	/**
	 * vérifie si le nombre de ligne du fichier est valide ( au moins 3 : 1 pour les coordonnées de la pelouse,
	 * 1 pour les coordonnées de la tondeuse et 1 pour les instructions.
	 * le nombre de lignes doit être impair car les coordonnées de la tondeuse et ses instructions vont de paire
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws TondeuseException : si le fichier contient moins de 3 lignes ou son nombre de lignes est pair
	 */
	private boolean isNumberOfLinesValid(File file) throws IOException, TondeuseException {		
		List<String> fileStream = Files.readAllLines(file.toPath());
		int nbLines = fileStream.size();
		if (nbLines % 2 == 0 || nbLines < 3) {
			throw new TondeuseException("le nombre de lignes du fichier doit être impair et au minimum 3");
		}
		return true;
	}
}