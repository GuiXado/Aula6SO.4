package view;

import controller.CarroF1;

public class Principal {

	public static void main(String[] args) {

		CarroF1[] carros = new CarroF1[14];

		// 7 equipes, 2 carros por equipe
		int id = 1;
		for (int equipe = 0; equipe < 7; equipe++) {
			for (int j = 0; j < 2; j++) {
				carros[id - 1] = new CarroF1(id, equipe);
				carros[id - 1].start();
				id++;
			}
		}

		for (CarroF1 c : carros) {
		    try {
		        c.join();
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}

		int[] tempos = CarroF1.getMelhoresTempos().clone();
		int[] ids = new int[14];
		for (int i = 0; i < 14; i++)
			ids[i] = i + 1;

		for (int i = 0; i < tempos.length - 1; i++) {
			for (int j = 0; j < tempos.length - i - 1; j++) {
				if (tempos[j] > tempos[j + 1]) {
					int tmpTempo = tempos[j];
					tempos[j] = tempos[j + 1];
					tempos[j + 1] = tmpTempo;

					int tmpId = ids[j];
					ids[j] = ids[j + 1];
					ids[j + 1] = tmpId;
				}
			}
		}

		// mostra grid final
		System.out.println("\n=== GRID DE LARGADA ===");
		for (int i = 0; i < 14; i++) {
			System.out.println((i + 1) + "º - Carro #" + ids[i] + " | Melhor volta: " + tempos[i] + "ms");
		}

	}

}