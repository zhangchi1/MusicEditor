package cs3500.music.tests;

import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created by dijamner on 3/22/16.
 */
public class MockMidiSythTest {

  @Test(expected = NotImplementedException.class)
  public void testGetMaxPolyphony() {
    new MockMidiSyth(new StringBuilder()).getMaxPolyphony();
  }

  @Test(expected = NotImplementedException.class)
  public void testGetLatency() {
    new MockMidiSyth(new StringBuilder()).getLatency();
  }

  @Test
  public void testGetChannels() {
    assertEquals(10, new MockMidiSyth(new StringBuilder()).getChannels().length);
  }

  @Test(expected = NotImplementedException.class)
  public void testGetVoiceStatus() {
    new MockMidiSyth(new StringBuilder()).getVoiceStatus();
  }

  @Test(expected = NotImplementedException.class)
  public void testIsSoundbankSupported() {
    new MockMidiSyth(new StringBuilder()).isSoundbankSupported(null);
  }

  @Test(expected = NotImplementedException.class)
  public void testLoadInstrument() {
    new MockMidiSyth(new StringBuilder()).loadInstrument(null);
  }

  @Test(expected = NotImplementedException.class)
  public void testUnloadInstrument() {
    new MockMidiSyth(new StringBuilder()).unloadInstrument(null);
  }

  @Test(expected = NotImplementedException.class)
  public void testRemapInstrument() {
    new MockMidiSyth(new StringBuilder()).remapInstrument(null, null);
  }

  @Test(expected = NotImplementedException.class)
  public void testGetDefaultSoundbank() {
    new MockMidiSyth(new StringBuilder()).getDefaultSoundbank();
  }

  @Test(expected = NotImplementedException.class)
  public void testGetAvailableInstruments() {
    new MockMidiSyth(new StringBuilder()).getAvailableInstruments();
  }

  @Test(expected = NotImplementedException.class)
  public void testGetLoadedInstruments() {
    new MockMidiSyth(new StringBuilder()).getLoadedInstruments();
  }

  @Test(expected = NotImplementedException.class)
  public void testLoadAllInstruments() {
    new MockMidiSyth(new StringBuilder()).loadAllInstruments(null);
  }

  @Test(expected = NotImplementedException.class)
  public void testUnloadAllInstruments() {
    new MockMidiSyth(new StringBuilder()).unloadAllInstruments(null);
  }

  @Test(expected = NotImplementedException.class)
  public void testLoadInstruments() {
    new MockMidiSyth(new StringBuilder()).loadInstruments(null, null);
  }

  @Test(expected = NotImplementedException.class)
  public void testUnloadInstruments() {
    new MockMidiSyth(new StringBuilder()).unloadInstruments(null, null);
  }

  @Test(expected = NotImplementedException.class)
  public void testGetDeviceInfo() {
    new MockMidiSyth(new StringBuilder()).getDeviceInfo();
  }

  /**
   * Test that open does not error
   */
  @Test
  public void testOpen() throws MidiUnavailableException {
    new MockMidiSyth(new StringBuilder()).open();
  }

  /**
   * Tests that close does not error
   */
  @Test
  public void testClose() {
    new MockMidiSyth(new StringBuilder()).close();
  }

  @Test(expected = NotImplementedException.class)
  public void testIsOpen() {
    new MockMidiSyth(new StringBuilder()).isOpen();
  }

  @Test(expected = NotImplementedException.class)
  public void testGetMicrosecondPosition() {
    new MockMidiSyth(new StringBuilder()).getMicrosecondPosition();
  }

  @Test
  public void testGetMaxReceivers() {
    assertEquals(-1, new MockMidiSyth(new StringBuilder()).getMaxReceivers());
  }

  @Test(expected = NotImplementedException.class)
  public void testGetMaxTransmitters() {
    new MockMidiSyth(new StringBuilder()).getMaxTransmitters();
  }

  /**
   * Tests that getReciever is implemented
   */
  @Test
  public void testGetReceiver() throws MidiUnavailableException {
    new MockMidiSyth(new StringBuilder()).getReceiver();
  }

  @Test(expected = NotImplementedException.class)
  public void testGetReceivers() {
    new MockMidiSyth(new StringBuilder()).getReceivers();
  }

  @Test(expected = NotImplementedException.class)
  public void testGetTransmitter() throws MidiUnavailableException {
    new MockMidiSyth(new StringBuilder()).getTransmitter();
  }

  @Test(expected = NotImplementedException.class)
  public void testGetTransmitters() {
    new MockMidiSyth(new StringBuilder()).getMaxTransmitters();
  }

  @Test
  public void testGetLog() throws MidiUnavailableException,
          InvalidMidiDataException {
    StringBuilder log = new StringBuilder();
    MockMidiSyth synth = new MockMidiSyth(log);
    StringBuilder out = new StringBuilder();
    Receiver rec = synth.getReceiver();
    for (int data1 = 0; data1 < 3; data1++) {
      for (int data2 = 0; data2 < 3; data2++) {
        for (int timestamp = -1; timestamp < 3; timestamp++) {
          ShortMessage msg = new ShortMessage();
          msg.setMessage(ShortMessage.NOTE_ON, 0, data1, data2);
          rec.send(msg, timestamp);
          out.append("message: Note On")
                  .append(", pitch: ")
                  .append(data1)
                  .append(", volume: ")
                  .append(data2)
                  .append(" with timestamp ")
                  .append(timestamp)
                  .append('\n');
        }
      }
    }
    assertEquals(out.toString(), log.toString());
  }

}