package video.rental.demo;

import video.rental.demo.application.Interactor;
import video.rental.demo.domain.Repository;
import video.rental.demo.domain.VideoFactory;
import video.rental.demo.infrastructure.RepositoryMemImpl;
import video.rental.demo.presentation.GraphicUI;
import video.rental.demo.utils.SampleGenerator;

public class Main {
	private static GraphicUI ui;

	public static void main(String[] args) {
		Repository repository = new RepositoryMemImpl();
		VideoFactory facotry = new VideoFactory();
		Interactor interactor = new Interactor(repository, facotry);
		new SampleGenerator(repository, facotry).generateSamples();;
		ui = new GraphicUI(interactor);
		ui.start();
	}
}
