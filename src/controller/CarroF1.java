package controller;

import java.util.concurrent.Semaphore;

public class CarroF1 extends Thread {

	private static Semaphore maxPista = new Semaphore(5);
	private static Semaphore[] equipeSem = new Semaphore[7];
	private static int[] melhoresTempos = new int[14];

	private int idCarro;
	private int equipe;
	private int melhorVolta = Integer.MAX_VALUE;

	public CarroF1(int idCarro, int equipe) {
		this.idCarro = idCarro;
		this.equipe = equipe;
	}

	static {
		for (int i = 0; i < 7; i++) {
			equipeSem[i] = new Semaphore(1);
		}
	}

	public void run() {
		try {
			maxPista.acquire();
			equipeSem[equipe].acquire();

			System.out.println("Carro #" + idCarro + " da equipe " + equipe + " entrou na pista.");
			pista();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			maxPista.release();
			equipeSem[equipe].release();

		}
	}

	private void pista() {
		for (int i = 1; i < 4; i++) {
			int tempo = (int) ((Math.random() * 3001) + 5000);
			System.out.println("Carro #" + idCarro + " volta " + i + ": " + tempo + "ms");
			if (tempo < melhorVolta)
				melhorVolta = tempo;
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		melhoresTempos[idCarro - 1] = melhorVolta;
	}

	public static int[] getMelhoresTempos() {
		return melhoresTempos;
	}
}
