package cs3500.music.tests;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import cs3500.music.model.AbsolutePitchFactory;
import cs3500.music.model.MusicPieceFactory;
import cs3500.music.model.RelativePitch;
import cs3500.music.view.MusicOutputDevice;
import cs3500.music.view.MusicOutputDeviceFactory;
import cs3500.music.view.MutableViewModel;

import static cs3500.music.controller.ControllerFactory.newInstantController;
import static org.junit.Assert.assertEquals;

/**
 * Tests the MusicOutputDeviceFactory
 */
public class MusicOutputDeviceFactoryTest {

  @Test
  public void testMIDI() throws IOException {
    StringBuilder log = new StringBuilder();
    MockMidiSyth mockSynth = new MockMidiSyth(log);
    MutableViewModel viewModel = MutableViewModel.newViewModel(MusicPieceFactory
            .newMetronome(AbsolutePitchFactory
                    .newAbsolutePitch(RelativePitch.A, 5), 5, 4, 9000)
            .setTempo(1000));
    MusicOutputDevice testMidi = MusicOutputDeviceFactory.newMidiFromSynth(mockSynth, viewModel);
    newInstantController(viewModel, testMidi).init();
    List<String> messages = Arrays.asList(log.toString().split("\n"));
    assertEquals(4, messages.stream().filter(s ->
            s.equals("message: Program Change, instrument: 0 with timestamp -1")).count());
    assertEquals(4, messages.stream().filter(s ->
            s.equals("message: Note On, pitch: 81, volume: 100 with timestamp -1")).count());
    //stop guards add 2 extra stops in total
    assertEquals(4 + 2, messages.stream().filter(s ->
            s.equals("message: Note Off, pitch: 81, volume: 100 with timestamp -1")).count());
  }

  /**
   * Test that this method successfully returns a view
   */
  @Test
  public void testMakeConsoleView() {
    assertEquals(true,
            Objects.nonNull(MusicOutputDeviceFactory
                    .newOutputDevice("console", MutableViewModel.newViewModel(
                            MusicPieceFactory.newEmptyPiece(1000)))));
  }

  @Test
  public void testMakeMidiView() {
    assertEquals(true,
            Objects.nonNull(MusicOutputDeviceFactory
                    .newOutputDevice("midi", MutableViewModel.newViewModel(
                            MusicPieceFactory.newEmptyPiece(1000)))));
  }

  @Test
  public void testMakeGUIView() {
    assertEquals(true,
            Objects.nonNull(MusicOutputDeviceFactory
                    .newOutputDevice("visual", MutableViewModel.newViewModel(
                            MusicPieceFactory.newEmptyPiece(1000)))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeInvalidView() {
    MusicOutputDeviceFactory
            .newOutputDevice("invalid", MutableViewModel.newViewModel(
                    MusicPieceFactory.newEmptyPiece(1000)));
  }

  /**
   * Test that a null parameter throws an exception
   */
  @Test(expected = NullPointerException.class)
  public void testMakeNullConsoleView() {
    assertEquals(true,
            Objects.nonNull(MusicOutputDeviceFactory
                    .newOutputDevice("console", null)));
  }

  /**
   * Test that a null parameter throws an exception
   */
  @Test(expected = NullPointerException.class)
  public void testMakeNullMidiView() {
    assertEquals(true,
            Objects.nonNull(MusicOutputDeviceFactory
                    .newOutputDevice("midi", null)));
  }

  /**
   * Test that a null parameter throws an exception
   */
  @Test(expected = NullPointerException.class)
  public void testMakeNullGUIView() {
    assertEquals(true,
            Objects.nonNull(MusicOutputDeviceFactory
                    .newOutputDevice("visual", null)));
  }

}