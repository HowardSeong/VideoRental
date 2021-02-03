package video.rental.demo;

import video.rental.demo.application.Interactor;
import video.rental.demo.domain.Repository;
import video.rental.demo.infrastructure.RepositoryMemImpl;
import video.rental.demo.presentation.GraphicUI;

public class Main {
	private static GraphicUI ui;

	public static void main(String[] args) {
		Repository repository = new RepositoryMemImpl();
		Interactor interactor = new Interactor(repository);
		ui = new GraphicUI(interactor);
		ui.start();
	}
}
