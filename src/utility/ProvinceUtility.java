package utility;

import java.io.*;
import java.util.Random;

import types.*;

public class ProvinceUtility {

	private static int[] coastalProvinces = { 1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 14, 15, 16, 18, 19, 20, 21, 25, 26, 27,
			28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 43, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 56, 57, 58,
			59, 60, 61, 64, 70, 71, 72, 73, 74, 75, 76, 78, 79, 80, 81, 82, 83, 84, 85, 86, 92, 93, 96, 97, 99, 100,
			101, 102, 103, 104, 105, 106, 142, 143, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161,
			162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 180, 204, 205, 211, 212, 220, 221, 230, 233,
			243, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 274, 275, 277, 278, 280, 281,
			283, 285, 286, 289, 290, 291, 292, 293, 296, 297, 300, 301, 302, 303, 304, 305, 306, 307, 308, 323, 324,
			325, 326, 327, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348,
			350, 351, 353, 355, 356, 357, 358, 366, 367, 368, 370, 371, 372, 373, 374, 375, 376, 377, 378, 380, 381,
			382, 384, 385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 411, 421, 422, 458, 459, 464, 465,
			467, 468, 469, 470, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488,
			489, 490, 491, 493, 494, 495, 496, 497, 498, 509, 510, 511, 512, 542, 543, 557, 558, 559, 560, 561, 562,
			563, 596, 597, 598, 600, 601, 641, 642, 643, 645, 648, 649, 650, 651, 652, 653, 677, 678, 705, 718, 719,
			720, 735, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 755, 756, 757, 758, 761, 762, 763, 764, 766,
			767, 770, 771, 772, 775, 780, 781, 784, 785, 786, 787, 788, 789, 790, 791, 794, 798, 799, 802, 804, 805,
			806, 807, 808, 809, 810, 811, 812, 813, 814, 816, 817, 819, 820, 822, 825, 826, 827, 831, 832, 834, 836,
			838, 839, 841, 842, 844, 845, 846, 847, 848, 849, 850, 851, 854, 855, 856, 857, 858, 859, 860, 861, 866,
			867, 868, 869, 870, 871, 872, 874, 899, 929, 931, 932, 935, 1112, 1114, 1115, 1117, 1119, 1123, 1124, 1125,
			1126, 1127, 1128, 1129, 1134, 1135, 1136, 1139, 1194, 1195, 1200, 1204, 1214, 1215, 1220, 1221, 1224, 1225,
			1231, 1232, 1235, 1236, 1266, 1268, 1279, 1290, 1292, 1294, 1295, 1297, 1318, 1320, 1330, 1331, 1332, 1343,
			1344, 1360, 1369, 1370, 1373, 1413, 1414, 1415 };

	public static String[] holySites = { "b_tholen", "b_paderborn", "b_koln", "b_lebedos", "b_lampsakos",
			"b_hagiasophia", "b_mntathos", "b_thesedessa", "b_piraeaus", "b_roma", "b_uppsala", "b_lejre", "b_maere",
			"b_raivola", "b_pruszkow", "b_arkona", "b_hiiumaa", "b_briesen", "b_sejny", "b_tikhvin", "b_riga",
			"b_yuriev", "b_pochep", "b_solotcha", "b_perm", "b_saqsin", "b_isilkul", "b_aqmescit", "b_syganak",
			"b_jajarm", "b_kakhesasan", "b_skara", "b_hamadan", "b_roudehen", "b_takhtesoleyman", "b_nagarahara",
			"b_zamindawar", "b_nok_kundi", "b_takhtisangin", "b_nadjaf", "b_bagdad", "b_karamlish", "b_husi", "b_vac",
			"b_stdenis", "b_santiago", "b_cordoba", "b_medina", "b_mecca", "b_heliopolis", "b_salalah", "b_alexandria",
			"b_attur", "b_jerusalem", "b_antiocheia", "b_nizwa", "b_damascus", "b_sidirahhal", "b_bordj", "b_siliana",
			"b_aksum", "b_ghazali", "b_st_pauls", "b_salam", "b_canterbury", "b_silla", "b_bamako", "b_mulasthana",
			"b_oddiyana", "b_krishnajanmabhoomi", "b_vadnagar", "b_dwarakadheesh", "b_girnar", "b_bhillamala",
			"b_mahakaleshwar", "b_sanchi", "b_ram_janmabhoomi", "b_varanasi", "b_sarnath", "b_rajrappa", "b_gaya",
			"b_ellora", "b_shravanabelagola" };

	public static void countHoldings(File folder) throws IOException {

		int castle = 0;
		int city = 0;
		int temple = 0;
		int tribal = 0;
		int other = 0;

		if (folder != null) {
			for (File file : folder.listFiles()) {

				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.startsWith("b_")) {
						String holding = line.split("=")[1].split("#")[0].trim();
						if (holding.equals("castle"))
							castle++;
						else if (holding.equals("city"))
							city++;
						else if (holding.equals("temple"))
							temple++;
						else if (holding.equals("tribal"))
							tribal++;
						else {
							other++;
						}
						break;
					}
				}
				br.close();
			}
			int sum = castle + city + tribal + temple + other;
			System.out.println("Castle: " + castle + " --> " + castle * 100 / sum);
			System.out.println("City: " + city + " --> " + city * 100 / sum);
			System.out.println("Temple: " + temple + " --> " + temple * 100 / sum);
			System.out.println("Tribal: " + tribal + " --> " + tribal * 100 / sum);
			System.out.println("Other: " + other + " --> " + other * 100 / sum);
		}
	}

	/**
	 * Function to randomize a province features
	 * 
	 * @param lessRandomMode
	 *            - flag for random mode
	 * @param src
	 *            - source file
	 * @param dst
	 *            - destination file
	 * @param holding
	 *            - random holding already extracted
	 * @param culture
	 *            - random culture already extracted
	 * @param random
	 *            - randomizer
	 * @return a list of parameters
	 */
	public static String randomizeProvince(boolean lessRandomMode, File src, File dst, Holding holding,
			Culture culture, Random random) throws IOException {
		String title = "";
		String religion = "";
		// ID of the province
		String number = src.getName().split("-")[0].trim();

		if (src != null) {
			// Variabili per i flussi
			boolean flag_header = false;
			boolean flag_settle = false;
			boolean flag_spacer = false;
			boolean flag_holding = false;
			boolean flag_history = false;

			BufferedReader reader = new BufferedReader(new FileReader(src));
			BufferedWriter writer = new BufferedWriter(new FileWriter(dst + "\\history\\provinces\\" + src.getName()));
			String line = "";

			// Main loop
			while ((line = reader.readLine()) != null) {
				// Print header
				if (!line.contains("max_settlements") && !flag_header) {
					writer.append(line + "\n");
					if (line.contains("title"))
						title = line.split("=")[1].split("#")[0].trim();
				} else {
					flag_header = true;

					// Define max_settlements
					if (!flag_settle) {
						// At least 3 settlements
						if (Integer.parseInt(line.split("=")[1].split("#")[0].trim()) < 3)
							writer.append("max_settlements = 3" + "\n");
						else
							writer.append(line + "\n");
						flag_settle = true;

					} else {
						// Continue
						if (!line.startsWith("b_") && !flag_spacer)
							writer.append(line + "\n");
						else {
							flag_spacer = true;

							// Define main holding
							if (!flag_holding) {

								// LessRandomMode
								if (lessRandomMode && holding.equals(Holding.tribal)) {
									Holding originalHolding = Holding.valueOf(line.split("=")[1].split("#")[0].trim());
									// in 70% of cases we keep original holding
									if (random.nextInt(100) < 70) {
										holding = originalHolding;
									}
								}

								// Overwrite with a temple if barony is a holy
								// site
								if (ProvinceUtility.isHolySite(line.split("=")[0].trim()))
									holding = Holding.temple;

								// Assign holding typology
								switch (holding) {
								case castle:
									writer.append(line.split("=")[0] + "= castle\n");
									break;
								case tribal:
									writer.append(line.split("=")[0] + "= tribal\n");
									break;
								case city:
									writer.append(line.split("=")[0] + "= city\n");
									break;
								case temple:
									writer.append(line.split("=")[0] + "= temple\n");
								}
								Statistics.countHolding(holding.ordinal());
								flag_holding = true;

							} else {
								if (!line.contains("History") && !flag_history) {
									// Continue removing other holdings
									if (line.trim().startsWith("b_")) {
										// But preserve Holy Sites
										if (ProvinceUtility.isHolySite(line.split("=")[0].trim()))
											writer.append(line + "\n");
										else
											writer.append("#" + line + "\n");
									} else if (line.trim().startsWith("culture")) {

										// LessRandomMode
										if (lessRandomMode) {
											Culture currentCulture = Culture.valueOf(line.split("=")[1].split("#")[0]
													.trim());
											// Overwrite random culture with a
											// calculated one
											culture = ProvinceUtility.calculateBestCulture(currentCulture);
										}

										// Assign culture to province
										writer.append("culture = " + culture + "\n");
										Statistics.countCulture(culture.ordinal());
									} else if (line.trim().startsWith("religion")) {

										// LessRandomMode
										if (lessRandomMode) {
											GlobalReligion currentReligion = GlobalReligion.valueOf(line.split("=")[1]
													.split("#")[0].trim());

											GlobalReligion gReligion = ProvinceUtility
													.calculateBestReligion(currentReligion);
											writer.append("religion = " + gReligion.toString() + "\n");
											religion = gReligion.toString();

										} else {
											int rand = random.nextInt(100);
											if (rand < 15) {
												writer.append("religion = " + Religion.catholic + "\n");
												religion = Religion.catholic.toString();
												Statistics.countReligion(0);
											} else if (rand >= 15 && rand < 20) {
												writer.append("religion = " + Religion.orthodox + "\n");
												religion = Religion.orthodox.toString();
												Statistics.countReligion(1);
											} else if (rand >= 20 && rand < 25) {
												writer.append("religion = " + Religion.miaphysite + "\n");
												religion = Religion.miaphysite.toString();
												Statistics.countReligion(2);
											} else if (rand >= 25 && rand < 30) {
												writer.append("religion = " + Religion.nestorian + "\n");
												religion = Religion.nestorian.toString();
												Statistics.countReligion(3);
											} else if (rand >= 30 && rand < 35) {
												writer.append("religion = " + Religion.sunni + "\n");
												religion = Religion.sunni.toString();
												Statistics.countReligion(4);
											} else if (rand >= 35 && rand < 40) {
												writer.append("religion = " + Religion.shiite + "\n");
												religion = Religion.shiite.toString();
												Statistics.countReligion(6);
											} else if (rand >= 40 && rand < 45) {
												writer.append("religion = " + Religion.ibadi + "\n");
												religion = Religion.ibadi.toString();
												Statistics.countReligion(5);
											} else if (rand >= 45 && rand < 50) {
												writer.append("religion = " + Religion.zoroastrian + "\n");
												religion = Religion.zoroastrian.toString();
												Statistics.countReligion(7);
											} else if (rand >= 50 && rand < 55) {
												writer.append("religion = " + Religion.jewish + "\n");
												religion = Religion.jewish.toString();
												Statistics.countReligion(8);
											} else if (rand >= 55 && rand < 60) {
												writer.append("religion = " + Religion.hindu + "\n");
												religion = Religion.hindu.toString();
												Statistics.countReligion(9);
											} else if (rand >= 60 && rand < 65) {
												writer.append("religion = " + Religion.jain + "\n");
												religion = Religion.jain.toString();
												Statistics.countReligion(11);
											} else if (rand >= 65 && rand < 70) {
												writer.append("religion = " + Religion.buddhist + "\n");
												religion = Religion.buddhist.toString();
												Statistics.countReligion(10);
											} else if (rand >= 70 && rand < 90) {
												int pRand = random.nextInt(Pagans.values().length);
												writer.append("religion = " + Pagans.values()[pRand] + "\n");
												religion = Pagans.values()[pRand].toString();
												Statistics.countReligion(12);
												Statistics.countPagans(pRand);
											} else {
												int hRand = random.nextInt(Heresy.values().length);
												writer.append("religion = " + Heresy.values()[hRand] + "\n");
												religion = Heresy.values()[hRand].toString();
												Statistics.countReligion(13);
												Statistics.countHeresy(hRand);
											}
										}
									} else
										writer.append(line + "\n");
								} else {
									flag_history = true;

								}
							}
						}
					}
				}
			}
			reader.close();
			writer.close();
		}
		return title.concat(";").concat(religion).concat(";").concat(number).concat(";").concat(culture.toString());
	}

	/**
	 * calculate the best religion for province
	 * 
	 * @param currentReligion
	 *            - current religion of the province
	 * @return new religion for the province
	 */
	private static GlobalReligion calculateBestReligion(GlobalReligion currentReligion) {
		Random random = new Random();
		GlobalReligion religion = currentReligion;
		switch (currentReligion) {
		case aztec:
			// Do nothing
			break;
		case baltic_pagan:
			// Do nothing
			break;
		case bogomilist:
			// Do nothing
			break;
		case buddhist:
			int bud = random.nextInt(90);
			if (bud < 9)
				religion = GlobalReligion.sunni;
			else if (bud >= 9 && bud < 30)
				religion = GlobalReligion.zun_pagan;
			break;
		case cathar:
			// Do nothing
			break;
		case catholic:
			int cat = random.nextInt(304);
			if (cat < 7)
				religion = GlobalReligion.cathar;
			else if (cat >= 7 && cat < 14)
				religion = GlobalReligion.fraticelli;
			else if (cat >= 14 && cat < 21)
				religion = GlobalReligion.lollard;
			else if (cat >= 21 && cat < 28)
				religion = GlobalReligion.waldensian;
			else if (cat >= 28 && cat < 53)
				religion = GlobalReligion.aztec;
			else if (cat >= 53 && cat < 80)
				religion = GlobalReligion.jewish;
			else if (cat >= 80 && cat < 86)
				religion = GlobalReligion.iconoclast;
			else if (cat >= 86 && cat < 92)
				religion = GlobalReligion.paulician;
			else if (cat >= 92 && cat < 98)
				religion = GlobalReligion.monothelite;
			else if (cat >= 98 && cat < 108)
				religion = GlobalReligion.miaphysite;
			else if (cat >= 108 && cat < 123)
				religion = GlobalReligion.nestorian;
			break;
		case druze:
			// Do nothing
			break;
		case finnish_pagan:
			int fin = random.nextInt(69);
			if (fin < 24)
				religion = GlobalReligion.tengri_pagan;
			else if (fin >= 24 && fin < 45)
				religion = GlobalReligion.pagan;
			break;
		case fraticelli:
			// Do nothing
			break;
		case hellenic_pagan:
			// Do nothing
			break;
		case hindu:
			if (random.nextInt(66) < 6)
				religion = GlobalReligion.sunni;
			break;
		case hurufi:
			// Do nothing
			break;
		case ibadi:
			// Do nothing
			break;
		case iconoclast:
			// Do nothing
			break;
		case jain:
			if (random.nextInt(78) < 18)
				religion = GlobalReligion.ibadi;
			break;
		case jewish:
			// Do nothing
			break;
		case karaite:
			// Do nothing
			break;
		case kharijite:
			religion = GlobalReligion.west_african_pagan;
			break;
		case lollard:
			// Do nothing
			break;
		case manichean:
			if (random.nextInt(9) < 3)
				religion = GlobalReligion.zoroastrian;
			break;
		case mazdaki:
			religion = GlobalReligion.shiite;
			break;
		case messalian:
			// Do nothing
			break;
		case miaphysite:
			int mia = random.nextInt(56);
			if (mia < 24)
				religion = GlobalReligion.ibadi;
			else if (mia >= 24 && mia < 30)
				religion = GlobalReligion.kharijite;
			else if (mia >= 30 && mia < 36)
				religion = GlobalReligion.shiite;
			else if (mia >= 36 && mia < 42)
				religion = GlobalReligion.jewish;
			break;
		case monophysite:
			// Do nothing
			break;
		case monothelite:
			religion = GlobalReligion.jewish;
			break;
		case nestorian:
			religion = GlobalReligion.samaritan;
			break;
		case norse_pagan:
			int nor = random.nextInt(58);
			if (nor < 25)
				religion = GlobalReligion.slavic_pagan;
			else if (nor >= 25 && nor < 33)
				religion = GlobalReligion.baltic_pagan;
			break;
		case orthodox:
			int ort = random.nextInt(104);
			if (ort < 25)
				religion = GlobalReligion.hellenic_pagan;
			else if (ort >= 25 && ort < 35)
				religion = GlobalReligion.jewish;
			else if (ort >= 35 && ort < 71)
				religion = GlobalReligion.miaphysite;
			else if (ort >= 71 && ort < 77)
				religion = GlobalReligion.monophysite;
			break;
		case pagan:
			// Do nothing
			break;
		case paulician:
			// Do nothing
			break;
		case samaritan:
			// Do nothing
			break;
		case shiite:
			if (random.nextInt(8) < 6)
				religion = GlobalReligion.druze;
			break;
		case slavic_pagan:
			int sla = random.nextInt(94);
			if (sla < 45)
				religion = GlobalReligion.nestorian;
			else if (sla >= 45 && sla < 51)
				religion = GlobalReligion.messalian;
			else if (sla >= 51 && sla < 57)
				religion = GlobalReligion.bogomilist;
			else
				religion = GlobalReligion.orthodox;
			break;
		case sunni:
			int sun = random.nextInt(93);
			if (sun < 8)
				religion = GlobalReligion.ibadi;
			else if (sun >= 8 && sun < 14)
				religion = GlobalReligion.hurufi;
			else if (sun >= 14 && sun < 41)
				religion = GlobalReligion.shiite;
			else if (sun >= 41 && sun < 47)
				religion = GlobalReligion.yazidi;
			break;
		case tengri_pagan:
			int ten = random.nextInt(72);
			if (ten < 50)
				religion = GlobalReligion.zoroastrian;
			else if (ten >= 50 && ten < 53)
				religion = GlobalReligion.pagan;
			else if (ten >= 53 && ten < 59)
				religion = GlobalReligion.karaite;
			else
				religion = GlobalReligion.jewish;
			break;
		case waldensian:
			// Do nothing
			break;
		case west_african_pagan:
			// Do nothing
			break;
		case yazidi:
			// Do nothing
			break;
		case zikri:
			// Do nothing
			break;
		case zoroastrian:
			int zor = random.nextInt(42);
			if (zor < 22)
				religion = GlobalReligion.shiite;
			else if (zor >= 22 && zor < 28)
				religion = GlobalReligion.zikri;
			else if (zor >= 28 && zor < 34)
				religion = GlobalReligion.mazdaki;
			break;
		case zun_pagan:
			// Do nothing
			break;
		default:
			break;
		}
		return religion;
	}

	/**
	 * Calculate the best culture for LessRandomMode
	 * 
	 * @param currentCulture
	 *            - current culture of a province
	 * @return - new culture to apply to the province
	 */
	private static Culture calculateBestCulture(Culture currentCulture) {
		Random random = new Random();
		// By default we keep current culture
		Culture newCulture = currentCulture;
		switch (currentCulture) {
		case afghan:
			if (random.nextInt(8) < 4)
				newCulture = Culture.kirghiz;
			break;
		case alan:
			// Do nothing
			break;
		case andalusian_arabic:
			// Do nothing
			break;
		case armenian:
			int ar = random.nextInt(15);
			if (ar < 4)
				newCulture = Culture.alan;
			else if (ar >= 4 && ar < 8)
				newCulture = Culture.georgian;
			break;
		case ashkenazi:
			// Do nothing
			break;
		case assamese:
			// Do nothing
			break;
		case avar:
			if (random.nextInt(19) < 6)
				newCulture = Culture.hungarian;
			break;
		case baloch:
			newCulture = Culture.afghan;
			break;
		case basque:
			// Do nothing
			break;
		case bedouin_arabic:
			int bd = random.nextInt(24);
			if (bd < 3)
				newCulture = Culture.somali;
			else if (bd >= 3 && bd < 16)
				newCulture = Culture.baloch;
			break;
		case bengali:
			int be = random.nextInt(27);
			if (be < 8)
				newCulture = Culture.assamese;
			else if (be >= 8 && be < 12)
				newCulture = Culture.oriya;
			break;
		case bohemian:
			// Do nothing
			break;
		case bolghar:
			newCulture = Culture.mordvin;
			break;
		case breton:
			// Do nothing
			break;
		case bulgarian:
			// Do nothing
			break;
		case castillan:
			// Do nothing
			break;
		case catalan:
			// Do nothing
			break;
		case croatian:
			if (random.nextInt(17) < 4)
				newCulture = Culture.serbian;
			break;
		case cuman:
			newCulture = Culture.komi;
			break;
		case danish:
			// Do nothing
			break;
		case dutch:
			// Do nothing
			break;
		case egyptian_arabic:
			// Do nothing
			break;
		case english:
			// Do nothing
			break;
		case ethiopian:
			if (random.nextInt(17) < 7)
				newCulture = Culture.somali;
			break;
		case finnish:
			int fn = random.nextInt(15);
			if (fn < 2)
				newCulture = Culture.ugricbaltic;
			else if (fn >= 2 && fn < 6)
				newCulture = Culture.lappish;
			break;
		case frankish:
			// Do nothing
			break;
		case frisian:
			// Do nothing
			break;
		case georgian:
			if (random.nextInt(7) < 2)
				newCulture = Culture.alan;
			break;
		case german:
			int gr = random.nextInt(32);
			if (gr < 10)
				newCulture = Culture.old_frankish;
			else if (gr >= 10 && gr < 18)
				newCulture = Culture.old_saxon;
			break;
		case greek:
			int gk = random.nextInt(73);
			if (gk < 6)
				newCulture = Culture.bulgarian;
			else if (gk >= 6 && gk < 17)
				newCulture = Culture.romanian;
			else if (gk >= 17 && gk < 21)
				newCulture = Culture.georgian;
			else if (gk >= 21 && gk < 28)
				newCulture = Culture.armenian;
			else if (gk >= 28 && gk < 43)
				newCulture = Culture.pecheneg;
			else if (gk >= 43 && gk < 58)
				newCulture = Culture.turkish;
			break;
		case gujurati:
			// Do nothing
			break;
		case hindustani:
			int hi = random.nextInt(41);
			if (hi < 5)
				newCulture = Culture.oriya;
			else if (hi >= 5 && hi < 13)
				newCulture = Culture.rajput;
			else if (hi >= 13 && hi < 25)
				newCulture = Culture.karluk;
			break;
		case hungarian:
			// Do nothing
			break;
		case ilmenian:
			int il = random.nextInt(13);
			if (il < 7)
				newCulture = Culture.lettigallish;
			else if (il >= 7 && il < 12)
				newCulture = Culture.lithuanian;
			break;
		case irish:
			if (random.nextInt(15) < 4)
				newCulture = Culture.scottish;
			break;
		case italian:
			if (random.nextInt(18) < 4)
				newCulture = Culture.roman;
			break;
		case kannada:
			if (random.nextInt(19) < 3)
				newCulture = Culture.tamil;
			break;
		case karluk:
			newCulture = Culture.khanty;
			break;
		case khanty:
			if (random.nextInt(5) < 2)
				newCulture = Culture.komi;
			break;
		case khazar:
			int kh = random.nextInt(16);
			if (kh < 4)
				newCulture = Culture.mordvin;
			else
				newCulture = Culture.severian;
			break;
		case kirghiz:
			newCulture = Culture.khanty;
			break;
		case komi:
			newCulture = Culture.samoyed;
			break;
		case kurdish:
			if (random.nextInt(15) < 10)
				newCulture = Culture.persian;
			break;
		case lappish:
			// Do nothing
			break;
		case lettigallish:
			// Do nothing
			break;
		case levantine_arabic:
			int lv = random.nextInt(60);
			if (lv < 15)
				newCulture = Culture.ashkenazi;
			else if (lv >= 15 && lv < 30)
				newCulture = Culture.sephardi;
			else if (lv >= 30 && lv < 40)
				newCulture = Culture.kurdish;
			else if (lv >= 40 && lv < 45)
				newCulture = Culture.bedouin_arabic;
			break;
		case lithuanian:
			if (random.nextInt(7) < 2)
				newCulture = Culture.prussian;
			break;
		case lombard:
			if (random.nextInt(24) < 10)
				newCulture = Culture.roman;
			break;
		case maghreb_arabic:
			int mg = random.nextInt(46);
			if (mg < 15)
				newCulture = Culture.andalusian_arabic;
			else if (mg >= 15 && mg < 30)
				newCulture = Culture.nahuatl;
			else if (mg >= 30 && mg < 31)
				newCulture = Culture.manden;
			break;
		case manden:
			// Do nothing
			break;
		case marathi:
			if (random.nextInt(21) < 5)
				newCulture = Culture.gujurati;
			break;
		case mongol:
			// Do nothing
			break;
		case mordvin:
			int mr = random.nextInt(24);
			if (mr < 12)
				newCulture = Culture.russian;
			else if (mr >= 12 && mr < 23)
				newCulture = Culture.ilmenian;
			break;
		case nahuatl:
			// Do nothing
			break;
		case norman:
			// Do nothing
			break;
		case norse:
			int nr = random.nextInt(4);
			switch (nr) {
			case 1:
				newCulture = Culture.danish;
				break;
			case 2:
				newCulture = Culture.norwegian;
				break;
			case 3:
				newCulture = Culture.swedish;
				break;
			default:
				break;
			}
			break;
		case norwegian:
			// Do nothing
			break;
		case nubian:
			if (random.nextInt(16) < 3)
				newCulture = Culture.ethiopian;
			break;
		case occitan:
			// Do nothing
			break;
		case old_frankish:
			int of = random.nextInt(53);
			if (of < 6)
				newCulture = Culture.breton;
			else if (of >= 6 && of < 20)
				newCulture = Culture.norman;
			else if (of >= 20 && of < 34)
				newCulture = Culture.frankish;
			else if (of >= 34 && of < 48)
				newCulture = Culture.dutch;
			break;
		case old_saxon:
			int os = random.nextInt(13);
			if (os < 7)
				newCulture = Culture.frisian;
			break;
		case oriya:
			if (random.nextInt(17) < 9)
				newCulture = Culture.sinhala;
			break;
		case panjabi:
			int pj = random.nextInt(17);
			if (pj < 4)
				newCulture = Culture.karluk;
			else if (pj >= 4 && pj < 8)
				newCulture = Culture.kirghiz;
			break;
		case pecheneg:
			newCulture = Culture.mongol;
			break;
		case persian:
			int pe = random.nextInt(46);
			if (pe < 4)
				newCulture = Culture.kirghiz;
			else if (pe >= 4 && pe < 17)
				newCulture = Culture.bolghar;
			else if (pe >= 17 && pe < 30)
				newCulture = Culture.cuman;
			else if (pe >= 30 && pe < 43)
				newCulture = Culture.khazar;
			break;
		case pictish:
			// Do nothing
			break;
		case polish:
			// Do nothing
			break;
		case pommeranian:
			// Do nothing
			break;
		case portuguese:
			// Do nothing
			break;
		case prussian:
			// Do nothing
			break;
		case rajput:
			int rj = random.nextInt(17);
			if (rj < 3)
				newCulture = Culture.sindhi;
			else if (rj >= 3 && rj < 9)
				newCulture = Culture.panjabi;
			break;
		case roman:
			// Do nothing
			break;
		case romanian:
			// Do nothing
			break;
		case russian:
			// Do nothing
			break;
		case samoyed:
			if (random.nextInt(7) < 1)
				newCulture = Culture.finnish;
			break;
		case saxon:
			int sx = random.nextInt(28);
			if (sx > 11 && sx <= 23)
				newCulture = Culture.english;
			else if (sx > 23)
				newCulture = Culture.scottish;
			break;
		case scottish:
			// Do nothing
			break;
		case sephardi:
			// Do nothing
			break;
		case serbian:
			// Do nothing
			break;
		case severian:
			int sv = random.nextInt(7);
			if (sv < 4)
				newCulture = Culture.volhynian;
			else
				newCulture = Culture.ilmenian;
			break;
		case sindhi:
			// Do nothing
			break;
		case sinhala:
			// Do nothing
			break;
		case somali:
			// Do nothing
			break;
		case suebi:
			// Do nothing
			break;
		case swedish:
			// Do nothing
			break;
		case tamil:
			// Do nothing
			break;
		case telugu:
			if (random.nextInt(19) < 4)
				newCulture = Culture.sinhala;
			break;
		case turkish:
			newCulture = Culture.mongol;
			break;
		case ugricbaltic:
			// Do nothing
			break;
		case visigothic:
			int vs = random.nextInt(57);
			if (vs < 5)
				newCulture = Culture.suebi;
			else if (vs >= 5 && vs < 9)
				newCulture = Culture.basque;
			else if (vs >= 9 && vs < 21)
				newCulture = Culture.castillan;
			else if (vs >= 21 && vs < 33)
				newCulture = Culture.catalan;
			else if (vs >= 33 && vs < 45)
				newCulture = Culture.portuguese;
			break;
		case volhynian:
			if (random.nextInt(9) < 3)
				newCulture = Culture.prussian;
			break;
		case welsh:
			if (random.nextInt(14) > 10)
				newCulture = Culture.scottish;
			break;
		default:
			break;

		}

		return newCulture;
	}

	// DEPRECATED
	public static void randomizeProvinces(File folder) throws IOException {
		if (folder != null) {
			Random random = new Random();
			String line = "";

			// Statistics
			int files = 0;
			int[] stats_holding = new int[4];
			int[] stats_culture = new int[92];
			int[] stats_religion = new int[14];
			int[] stats_pagans = new int[10];
			int[] stats_heresy = new int[19];

			for (File file : folder.listFiles()) {
				files++;

				// Variabili per i flussi
				boolean header = false;
				boolean settlement = false;
				boolean spacer = false;
				boolean holding = false;
				boolean misc = false;
				boolean history = false;
				boolean cultureAssigned = false;
				boolean religionAssigned = false;

				BufferedReader reader = new BufferedReader(new FileReader(file));
				BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Repositories\\CK2-Randomizer"
						+ "\\CK2-Randomizer\\history\\provinces\\" + file.getName()));
				while ((line = reader.readLine()) != null) {
					// Print header
					if (!line.contains("max_settlements") && !header)
						writer.append(line + "\n");
					else {
						header = true;

						// Define max_settlements
						if (!settlement) {
							if (Integer.parseInt(line.split("=")[1].split("#")[0].trim()) < 3)
								writer.append("max_settlements = 3" + "\n");
							else
								writer.append(line + "\n");
							settlement = true;
						} else {
							// Continue
							if (!line.startsWith("b_") && !spacer)
								writer.append(line + "\n");
							else {
								spacer = true;

								// Define main holding
								if (!holding) {
									int rand = random.nextInt(100);
									if (rand < 65) {
										writer.append(line.split("=")[0] + "= " + Holding.castle + "\n");
										stats_holding[0]++;
									} else if (rand >= 65 && rand < 90) {
										writer.append(line.split("=")[0] + "= " + Holding.tribal + "\n");
										stats_holding[1]++;
									} else if (rand >= 90 && rand < 95) {
										writer.append(line.split("=")[0] + "= " + Holding.city + "\n");
										stats_holding[2]++;
									} else {
										writer.append(line.split("=")[0] + "= " + Holding.temple + "\n");
										stats_holding[3]++;
									}
									holding = true;

								} else {
									// Continue removing other holdings
									if (!misc && (line.contains("b_")))
										writer.append("#" + line + "\n");
									else {
										misc = true;
										// Define other characteristics
										if (!line.contains("History") && !history) {
											// Random Culture
											if (line.startsWith("culture")) {
												int cRand = random.nextInt(Culture.values().length);
												writer.append("culture = " + Culture.values()[cRand] + "\n");
												stats_culture[cRand]++;
												cultureAssigned = true;

											}
											// Random Religion
											else if (line.startsWith("religion")) {
												int rand = random.nextInt(100);
												if (rand < 15) {
													writer.append("religion = " + Religion.catholic + "\n");
													stats_religion[0]++;
												} else if (rand >= 15 && rand < 20) {
													writer.append("religion = " + Religion.orthodox + "\n");
													stats_religion[1]++;
												} else if (rand >= 20 && rand < 25) {
													writer.append("religion = " + Religion.miaphysite + "\n");
													stats_religion[2]++;
												} else if (rand >= 25 && rand < 30) {
													writer.append("religion = " + Religion.nestorian + "\n");
													stats_religion[3]++;
												} else if (rand >= 30 && rand < 35) {
													writer.append("religion = " + Religion.sunni + "\n");
													stats_religion[4]++;
												} else if (rand >= 35 && rand < 40) {
													writer.append("religion = " + Religion.shiite + "\n");
													stats_religion[6]++;
												} else if (rand >= 40 && rand < 45) {
													writer.append("religion = " + Religion.ibadi + "\n");
													stats_religion[5]++;
												} else if (rand >= 45 && rand < 50) {
													writer.append("religion = " + Religion.zoroastrian + "\n");
													stats_religion[7]++;
												} else if (rand >= 50 && rand < 55) {
													writer.append("religion = " + Religion.jewish + "\n");
													stats_religion[8]++;
												} else if (rand >= 55 && rand < 60) {
													writer.append("religion = " + Religion.hindu + "\n");
													stats_religion[9]++;
												} else if (rand >= 60 && rand < 65) {
													writer.append("religion = " + Religion.jain + "\n");
													stats_religion[11]++;
												} else if (rand >= 65 && rand < 70) {
													writer.append("religion = " + Religion.buddhist + "\n");
													stats_religion[10]++;
												} else if (rand >= 70 && rand < 90) {
													int pRand = random.nextInt(Pagans.values().length);
													writer.append("religion = " + Pagans.values()[pRand] + "\n");
													stats_religion[12]++;
													stats_pagans[pRand]++;
												} else {
													int hRand = random.nextInt(Heresy.values().length);
													writer.append("religion = " + Heresy.values()[hRand] + "\n");
													stats_religion[13]++;
													stats_heresy[hRand]++;
												}
												religionAssigned = true;
											} else
												writer.append(line + "\n");
										} else
											history = true;
									}
								}
							}
						}
					}
				}
				reader.close();
				writer.close();
				if (!cultureAssigned)
					System.out.println("NO Culture: " + file.getName());
				if (!religionAssigned)
					System.out.println("NO Religion: " + file.getName());
			}

			// Print statistics
			System.out.println("Tot Provinces: " + files);
			System.out.println("Holdings: " + sumArray(stats_holding));
			for (int i : stats_holding) {
				System.out.print(i + ", ");
			}
			System.out.println("\n");
			System.out.println("Culture: " + sumArray(stats_culture));
			for (int i : stats_culture) {
				System.out.print(i + ", ");
			}
			System.out.println("\n");
			System.out.println("Religion: " + sumArray(stats_religion));
			for (int i : stats_religion) {
				System.out.print(i + ", ");
			}
			System.out.println("\n");
			System.out.println("Pagans: " + sumArray(stats_pagans));
			for (int i : stats_pagans) {
				System.out.print(i + ", ");
			}
			System.out.println("\n");
			System.out.println("Heresy: " + sumArray(stats_heresy));
			for (int i : stats_heresy) {
				System.out.print(i + ", ");
			}
			System.out.println("\n");

		}
	}

	public static boolean isCoastalProvince(int id) {

		for (int province : coastalProvinces) {
			if (id == province)
				return true;
		}
		return false;
	}

	public static boolean isHolySite(String title) {

		for (String site : holySites) {
			if (title.equals(site))
				return true;
		}
		return false;
	}

	private static int sumArray(int[] array) {
		int sum = 0;
		for (int k = 0; k < array.length; k++) {
			sum += array[k];
		}
		return sum;
	}
}
