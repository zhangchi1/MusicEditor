package cs3500.music.tests;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.music.controller.BeatController;
import cs3500.music.model.MusicPieceFactory;
import cs3500.music.view.BeatListener;
import cs3500.music.view.GuiView;
import cs3500.music.view.MutableViewModel;

/**
 * Created MockGuiView
 */
public class MockGuiView implements GuiView, BeatListener {

  private final List<KeyListener> keyListeners;
  private final List<MouseListener> mouseListeners;
  private final List<MouseMotionListener> mouseMotionListeners;
  private final List<AdjustmentListener> horizAdjustmentListeners;
  private final List<AdjustmentListener> vertAdjustmentListeners;
  private final MutableViewModel viewModel;

  public MockGuiView() {
    this(MutableViewModel.newViewModel(MusicPieceFactory.newEmptyPiece(1000)));
  }


  public MockGuiView(MutableViewModel viewModel) {
    this.viewModel = viewModel;
    this.keyListeners = new ArrayList<>();
    this.mouseListeners = new ArrayList<>();
    this.mouseMotionListeners = new ArrayList<>();
    this.horizAdjustmentListeners = new ArrayList<>();
    this.vertAdjustmentListeners = new ArrayList<>();
  }

  @Override
  public void addKeyListener(KeyListener key) {
    this.keyListeners.add(key);
  }

  @Override
  public void removeKeyListener(KeyListener key) {
    this.keyListeners.remove(key);
  }

  @Override
  public void addMouseListener(MouseListener mouse) {
    this.mouseListeners.add(mouse);
  }

  @Override
  public void removeMouseListener(MouseListener mouse) {
    this.mouseListeners.remove(mouse);
  }

  @Override
  public void addMouseMotionListener(MouseMotionListener mouse) {
    this.mouseMotionListeners.add(mouse);
  }

  @Override
  public void removeMouseMotionListener(MouseMotionListener mouse) {
    this.mouseMotionListeners.remove(mouse);
  }

  @Override
  public void addHorizAdjustmentListener(AdjustmentListener listener) {
    this.horizAdjustmentListeners.add(listener);
  }

  @Override
  public void removeHorizAdjustmentListener(AdjustmentListener listener) {
    this.horizAdjustmentListeners.remove(listener);
  }

  @Override
  public void addVertAdjustmentListener(AdjustmentListener listener) {
    this.vertAdjustmentListeners.add(listener);
  }

  @Override
  public void removeVertAdjustmentListener(AdjustmentListener listener) {
    this.vertAdjustmentListeners.remove(listener);
  }

  @Override
  public Point fromScreenPoint(Point screenPoint) {
    return screenPoint;
  }

  @Override
  public void renderModel(BeatController timer) throws IOException {
    timer.addBeatListener(this, BeatController.Priority.LAST);
  }

  @Override
  public void initialize() {
    //no initialization
  }

  public void horizAdjustmentValueChanged(AdjustmentEvent e) {
    for (AdjustmentListener a : horizAdjustmentListeners) {
      a.adjustmentValueChanged(e);
    }
  }

  public void vertAdjustmentValueChanged(AdjustmentEvent e) {
    for (AdjustmentListener a : vertAdjustmentListeners) {
      a.adjustmentValueChanged(e);
    }
  }

  public void keyTyped(KeyEvent e) {
    for (KeyListener k : keyListeners) {
      k.keyTyped(e);
    }
  }


  public void keyPressed(KeyEvent e) {
    for (KeyListener k : keyListeners) {
      k.keyPressed(e);
    }
  }

  public void keyReleased(KeyEvent e) {
    for (KeyListener k : keyListeners) {
      k.keyReleased(e);
    }
  }

  public void mouseClicked(MouseEvent e) {
    for (MouseListener m : mouseListeners) {
      m.mouseClicked(e);
    }
  }

  public void mousePressed(MouseEvent e) {
    for (MouseListener m : mouseListeners) {
      m.mousePressed(e);
    }
  }

  public void mouseReleased(MouseEvent e) {
    for (MouseListener m : mouseListeners) {
      m.mouseReleased(e);
    }
  }

  public void mouseEntered(MouseEvent e) {
    for (MouseListener m : mouseListeners) {
      m.mouseEntered(e);
    }
  }

  public void mouseExited(MouseEvent e) {
    for (MouseListener m : mouseListeners) {
      m.mouseExited(e);
    }
  }

  public void mouseDragged(MouseEvent e) {
    for (MouseMotionListener m : mouseMotionListeners) {
      m.mouseDragged(e);
    }
  }

  public void mouseMoved(MouseEvent e) {
    for (MouseMotionListener m : mouseMotionListeners) {
      m.mouseMoved(e);
    }
  }

  @Override
  public void run(BeatController timer, int beat) {
    viewModel.run(timer, beat);
  }

  @Override
  public void stop() {

  }
}
