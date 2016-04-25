package cs3500.music.view;

import java.io.IOException;

import cs3500.music.controller.BeatController;

/**
 * Represents a way to output music. The view component of a music application.
 */
public interface MusicOutputDevice {

  /**
   * Renders the given viewModel in this view
   *
   * @param timer the beat controller
   */
  void renderModel(BeatController timer) throws IOException;


  /**
   * Perform any necessary setup that this view requires
   */
  void initialize();

}
