package cs3500.music.controller;

/**
 * Represents a controller that can connect a view ({@link cs3500.music.view.MusicOutputDevice})
 * to a model ({@link cs3500.music.model.MusicPieceModel})
 * through a {@link cs3500.music.view.ViewModel}.
 * See {@link ControllerFactory} for how to create new controllers.
 */
public interface Controller {
  /**
   * Starts running the controller. This may perform actions such as
   * launching a GUI, printing to standard I/O or a file, or
   * playing audio.
   */
  void init();
}
