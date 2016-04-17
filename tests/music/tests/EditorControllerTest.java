package cs3500.music.tests;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerFactory;
import cs3500.music.model.Note;
import cs3500.music.view.MutableViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Create tests for EditorController
 */
public class EditorControllerTest {

  private final MutableViewModel viewModel =
          MutableViewModel.newViewModel(SamplePitches.LightlyRow4Measures());
  private final MockGuiView mock = new MockGuiView();
  private final Component mockComponent = new JPanel();

  @Test(expected = NullPointerException.class)
  public void testMakeControllerNullView() {
    ControllerFactory.newEditorController(viewModel, null);
  }

  @Test(expected = NullPointerException.class)
  public void testMakeControllerNullViewModel() {
    ControllerFactory.newEditorController(null, mock);
  }

  @Test
  public void testPauseController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    mock.keyTyped(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_SPACE, ' '));
    int measurement = viewModel.getCurrentBeat();
    Thread.sleep(1000);
    assertEquals(measurement, viewModel.getCurrentBeat());
    mock.keyTyped(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_SPACE, ' '));
    Thread.sleep(1000);
    assertNotEquals(measurement, viewModel.getCurrentBeat());

  }

  @Test
  public void testSelectPointController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    mock.keyTyped(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_SPACE, ' '));

    mock.mousePressed(new MouseEvent
        (mockComponent, 0, 0, 0, 32, 64, 1, false, MouseEvent.BUTTON1));
    assertEquals(new Point(32, 64), viewModel.getSelectedPoint());
  }

  @Test
  public void testMoveNoteController() throws InterruptedException {
    Note first = viewModel.getNotesAt(0).get(0);
    viewModel.setHeldNote(first);

    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    mock.keyTyped(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_SPACE, ' '));

    mock.mousePressed(new MouseEvent(mockComponent, 0, 0, 0, 0, first.getPitch().getIntValue(),
            1, false, MouseEvent.BUTTON1));
    mock.mouseDragged(new MouseEvent(mockComponent, 0, 0, 0, 2, SamplePitches.A4.getIntValue(),
            1, false, MouseEvent.BUTTON1));
    assertEquals(true, viewModel.getNotesAt(2).contains(first.addStartTime(2)));
  }

  @Test
  public void testShiftandRightarrowController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    int currentbeat = viewModel.getCurrentBeat();
    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_SHIFT, 's'));
    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_RIGHT, 'r'));

    assertEquals(currentbeat + 2, viewModel.getCurrentBeat());
  }

  @Test
  public void testShiftandleftarrowController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    int currentbeat = viewModel.getCurrentBeat();
    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_SHIFT, 's'));
    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_LEFT, 'r'));
    assertEquals(currentbeat - 2, viewModel.getCurrentBeat());
  }

  @Test
  public void testrightarrowController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    int currentview = viewModel.getViewStart();

    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_RIGHT, 'r'));
    assertEquals(currentview + 1, viewModel.getViewStart());
  }

  @Test
  public void testleftarrowController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    int currentview = viewModel.getViewStart();

    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_LEFT, 'r'));
    assertEquals(currentview - 1, viewModel.getViewStart());
  }
  @Test
  public void testuparrowController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    int currentviewtop = viewModel.getViewTop();

    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_UP, 'r'));
    assertEquals(currentviewtop - 1, viewModel.getViewTop());
  }

  @Test
  public void testdownarrowController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();
    int currentviewtop = viewModel.getViewTop();

    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_DOWN, 'r'));
    assertEquals(currentviewtop + 1, viewModel.getViewTop());
  }

  @Test
  public void testadddurationController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();

    viewModel.setSelectedPoint(new Point(0, SamplePitches.A4.getIntValue()));
    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_CLOSE_BRACKET, 'r'));
    assertEquals(1 + 1, viewModel.getNotesAt(0).get(0).getDuration());
  }

  @Test
  public void testreducedurationController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();

    viewModel.setSelectedPoint(new Point(2, SamplePitches.FSharp4.getIntValue()));
    mock.keyPressed(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_OPEN_BRACKET, 'r'));
    assertEquals(2 - 1, viewModel.getNotesAt(0).get(0).getDuration());
  }

  @Test
  public void testhomekeyController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();

    mock.keyReleased(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_HOME, 'r'));
    assertEquals(0, viewModel.getCurrentBeat());
  }

  @Test
  public void testEndkeyController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();

    mock.keyReleased(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_END, 'r'));
    assertEquals(viewModel.getLength(), viewModel.getCurrentBeat());
  }

  @Test
  public void testbackspacekeyController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();

    viewModel.setSelectedPoint(new Point(2, SamplePitches.FSharp4.getIntValue()));
    mock.keyTyped(new KeyEvent(mockComponent, 0, 0, 0, KeyEvent.VK_BACK_SPACE, 'r'));

    assertEquals(0, viewModel.getNotesAt(2).size());
  }

  @Test
  public void testmouseReleasedController() throws InterruptedException {
    Controller c = ControllerFactory.newEditorController(viewModel, mock);
    c.init();

    viewModel.setSelectedPoint(new Point(2, SamplePitches.FSharp4.getIntValue()));
    mock.mouseReleased(new MouseEvent(mockComponent, 0, 0, 0, 0, SamplePitches.A4.getIntValue(),
        1, false, MouseEvent.BUTTON1));

    assertEquals(null, viewModel.getHeldNote());
  }



}