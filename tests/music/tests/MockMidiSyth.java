package cs3500.music.tests;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.midi.VoiceStatus;

/**
 * A mock class that imitates a synthesizer
 */
public class MockMidiSyth implements Synthesizer {

  private final Appendable out;

  MockMidiSyth(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public int getMaxPolyphony() {
    throw new NotImplementedException();
  }

  @Override
  public long getLatency() {
    throw new NotImplementedException();
  }

  /**
   * Used by the program to determine the number of usable channels
   * @return a new empty array with a length of 10
   */
  @Override
  public MidiChannel[] getChannels() {
    return new MidiChannel[10];
  }

  @Override
  public VoiceStatus[] getVoiceStatus() {
    throw new NotImplementedException();
  }

  @Override
  public boolean isSoundbankSupported(Soundbank soundbank) {
    throw new NotImplementedException();
  }

  @Override
  public boolean loadInstrument(Instrument instrument) {
    throw new NotImplementedException();
  }

  @Override
  public void unloadInstrument(Instrument instrument) {
    throw new NotImplementedException();
  }

  @Override
  public boolean remapInstrument(Instrument from, Instrument to) {
    throw new NotImplementedException();
  }

  @Override
  public Soundbank getDefaultSoundbank() {
    throw new NotImplementedException();
  }

  @Override
  public Instrument[] getAvailableInstruments() {
    throw new NotImplementedException();
  }

  @Override
  public Instrument[] getLoadedInstruments() {
    throw new NotImplementedException();
  }

  @Override
  public boolean loadAllInstruments(Soundbank soundbank) {
    throw new NotImplementedException();
  }

  @Override
  public void unloadAllInstruments(Soundbank soundbank) {
    throw new NotImplementedException();
  }

  @Override
  public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    throw new NotImplementedException();
  }

  @Override
  public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {
    throw new NotImplementedException();
  }

  @Override
  public Info getDeviceInfo() {
    throw new NotImplementedException();
  }

  @Override
  public void open() throws MidiUnavailableException {

  }

  @Override
  public void close() {

  }

  @Override
  public boolean isOpen() {
    throw new NotImplementedException();
  }

  @Override
  public long getMicrosecondPosition() {
    throw new NotImplementedException();
  }

  @Override
  public int getMaxReceivers() {
    return -1;
  }

  @Override
  public int getMaxTransmitters() {
    throw new NotImplementedException();
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return new MockReceiver();
  }

  @Override
  public List<Receiver> getReceivers() {
    throw new NotImplementedException();
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    throw new NotImplementedException();
  }

  @Override
  public List<Transmitter> getTransmitters() {
    throw new NotImplementedException();
  }

  private class MockReceiver implements Receiver {

    @Override
    public void send(MidiMessage message, long timeStamp) {
      try {
        switch (Byte.toUnsignedInt(message.getMessage()[0])) {
          case ShortMessage.PROGRAM_CHANGE:
            out.append("message: Program Change")
                    .append(", instrument: ")
                    .append(Integer.toString(Byte
                            .toUnsignedInt(message.getMessage()[1])))
                    .append(" with timestamp ")
                    .append(Long.toString(timeStamp))
                    .append('\n');
            break;
          case ShortMessage.NOTE_ON:
            out.append("message: Note On")
                    .append(", pitch: ")
                    .append(Integer.toString(Byte
                            .toUnsignedInt(message.getMessage()[1])))
                    .append(", volume: ")
                    .append(Integer.toString(Byte
                            .toUnsignedInt(message.getMessage()[2])))
                    .append(" with timestamp ")
                    .append(Long.toString(timeStamp))
                    .append('\n');
            break;
          case ShortMessage.NOTE_OFF:
            out.append("message: Note Off")
                    .append(", pitch: ")
                    .append(Integer.toString(Byte
                            .toUnsignedInt(message.getMessage()[1])))
                    .append(", volume: ")
                    .append(Integer.toString(Byte
                            .toUnsignedInt(message.getMessage()[2])))
                    .append(" with timestamp ")
                    .append(Long.toString(timeStamp))
                    .append('\n');
            break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void close() {

    }
  }

}
