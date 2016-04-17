package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerFactory;
import cs3500.music.view.MusicOutputDevice;
import cs3500.music.view.MusicOutputDeviceFactory;
import cs3500.music.view.MutableViewModel;
import cs3500.music.view.ViewModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests methods of static controllers
 */
public class StaticControllerTest {

  private final ViewModel viewModel =
          MutableViewModel.newViewModel(SamplePitches.LightlyRow4Measures());

  @Test(expected = NullPointerException.class)
  public void testMakeNullController() {
    ControllerFactory.newStaticController(null);
  }

  @Test
  public void testController() {
    StringBuilder out = new StringBuilder();
    MusicOutputDevice view = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
    Controller c = ControllerFactory.newStaticController(view);
    c.init();
    assertEquals(SamplePitches.LightlyRow4Measures().getLength(),
            viewModel.getLength());
    assertEquals("    D4  D#4   E4   F4  F#4   G4  G#4   A4 \n" +
            " 0                                     X  \n" +
            " 1                      X                 \n" +
            " 2                      X                 \n" +
            " 3                      |                 \n" +
            " 4                           X            \n" +
            " 5            X                           \n" +
            " 6            X                           \n" +
            " 7            |                           \n" +
            " 8  X                                     \n" +
            " 9            X                           \n" +
            "10                      X                 \n" +
            "11                           X            \n" +
            "12                                     X  \n" +
            "13                                     X  \n" +
            "14                                     X  \n" +
            "15                                     |  \n", out.toString());
  }


}