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

	public static String randomizeProvince(boolean lessRandomMode, File src, File dst, Holding holding, Culture culture, Random random)
			throws IOException {
		String title = "";
		String religion = "";
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
								if (lessRandomMode) {
									holding = Holding.valueOf(line.split("=")[1].split("#")[0].trim());
									int rand_holding;
									if ((rand_holding = random.nextInt(100)) > 55){
										if (rand_holding < 67)
											holding = Holding.castle;
										else if (rand_holding < 78)
											holding = Holding.temple;
										else if (rand_holding < 89)
											holding = Holding.city;
										else
											holding = Holding.tribal;
									}
								}
								
								if (ProvinceUtility.isHolySite(line.split("=")[0].trim()))
									holding = Holding.temple;

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
										if (ProvinceUtility.isHolySite(line.split("=")[0].trim()))
											writer.append(line + "\n");
										else
											writer.append("#" + line + "\n");
									} else if (line.trim().startsWith("culture")) {
										
										// LessRandomMode
										//
										
										writer.append("culture = " + culture + "\n");
										Statistics.countCulture(culture.ordinal());
									} else if (line.trim().startsWith("religion")) {

										// LessRandomMode
										//
										
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
		return title.concat(";").concat(religion).concat(";").concat(number);
	}

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
