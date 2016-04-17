package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerFactory;
import cs3500.music.view.MutableViewModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the MIDI controller
 */
public class MidiControllerTest {

  private final MutableViewModel viewModel =
          MutableViewModel.newViewModel(SamplePitches.LightlyRow4Measures());
  private final MockGuiView mock = new MockGuiView(viewModel);

  @Test(expected = NullPointerException.class)
  public void testMakeNullViewController() {
    ControllerFactory.newMidiController(viewModel, null);
  }

  @Test(expected = NullPointerException.class)
  public void testMakeNullModelController() {
    ControllerFactory.newMidiController(null, mock);
  }

  @Test
  public void testRunControllerEnds() throws InterruptedException {
    Controller c = ControllerFactory.newMidiController(viewModel, mock);
    List<Integer> beats = new ArrayList<>();
    viewModel.registerUpdates(t -> beats.add(viewModel.getCurrentBeat()), ()-> {});
    c.init();
    Thread.sleep(2000);
    assertEquals(16, viewModel.getCurrentBeat());
    assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), beats);

  }

}