package cs3500.music.tests;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.controller.Controller;

import cs3500.music.model.AbsolutePitchFactory;
import cs3500.music.model.MusicPieceFactory;
import cs3500.music.model.MusicPieceModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteFactory;
import cs3500.music.model.RelativePitch;
import cs3500.music.view.MusicOutputDevice;
import cs3500.music.view.MusicOutputDeviceFactory;
import cs3500.music.view.MutableViewModel;

import static cs3500.music.controller.ControllerFactory.newInstantController;
import static org.junit.Assert.assertEquals;

/**
 * Tests the music printer
 */
public class MusicPrintingTest {
    Note noteG3_0 = NoteFactory.newNote(0, 7, 3, RelativePitch.G, 0, 0);
    Note noteG3_8 = NoteFactory.newNote(8, 7, 3, RelativePitch.G, 0, 0);
    Note noteG3_16 = NoteFactory.newNote(16, 7, 3, RelativePitch.G, 0, 0);
    Note noteG3_24 = NoteFactory.newNote(24, 2, 3, RelativePitch.G, 0, 0);
    Note noteG3_32 = NoteFactory.newNote(32, 8, 3, RelativePitch.G, 0, 0);
    Note noteG3_40 = NoteFactory.newNote(40, 8, 3, RelativePitch.G, 0, 0);
    Note noteG3_48 = NoteFactory.newNote(48, 8, 3, RelativePitch.G, 0, 0);
    Note noteG4_26 = NoteFactory.newNote(26, 2, 4, RelativePitch.G, 0, 0);
    Note noteG4_28 = NoteFactory.newNote(28, 4, 4, RelativePitch.G, 0, 0);
    Note noteE3_56 = NoteFactory.newNote(56, 8, 3, RelativePitch.E, 0, 0);

    Note noteE4_0 = NoteFactory.newNote(0, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_8 = NoteFactory.newNote(8, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_10 = NoteFactory.newNote(10, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_12 = NoteFactory.newNote(12, 3, 4, RelativePitch.E, 0, 0);
    Note noteE4_24 = NoteFactory.newNote(24, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_32 = NoteFactory.newNote(32, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_40 = NoteFactory.newNote(40, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_42 = NoteFactory.newNote(42, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_44 = NoteFactory.newNote(44, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_46 = NoteFactory.newNote(46, 2, 4, RelativePitch.E, 0, 0);
    Note noteE4_52 = NoteFactory.newNote(52, 2, 4, RelativePitch.E, 0, 0);

    Note noteD4_2 = NoteFactory.newNote(2, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_6 = NoteFactory.newNote(6, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_16 = NoteFactory.newNote(16, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_18 = NoteFactory.newNote(18, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_20 = NoteFactory.newNote(20, 4, 4, RelativePitch.D, 0, 0);
    Note noteD4_34 = NoteFactory.newNote(34, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_38 = NoteFactory.newNote(38, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_48 = NoteFactory.newNote(48, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_50 = NoteFactory.newNote(50, 2, 4, RelativePitch.D, 0, 0);
    Note noteD4_54 = NoteFactory.newNote(54, 2, 4, RelativePitch.D, 0, 0);

    Note noteC4_4 = NoteFactory.newNote(4, 2, 4, RelativePitch.C, 0, 0);
    Note noteC4_36 = NoteFactory.newNote(36, 2, 4, RelativePitch.C, 0, 0);
    Note noteC4_56 = NoteFactory.newNote(56, 8, 4, RelativePitch.C, 0, 0);
    List<Note> listnoteD = new ArrayList<>(Arrays.asList(noteD4_2, noteD4_16, noteD4_6, noteD4_18,
        noteD4_20, noteD4_34, noteD4_38, noteD4_18, noteD4_48, noteD4_50, noteD4_54));
    List<Note> listnoteE = new ArrayList<>(Arrays.asList(noteE4_0, noteE4_8, noteE4_10, noteE4_12,
        noteE4_24, noteE4_32, noteE4_40, noteE4_42, noteE4_44, noteE4_46, noteE4_52));
    List<Note> listnoteC = new ArrayList<>(Arrays.asList(noteC4_4, noteC4_36, noteC4_56));
    List<Note> listnoteG = new ArrayList<>(Arrays.asList(noteG3_0, noteG3_8, noteG3_16,
        noteG3_24, noteG3_32, noteG3_40, noteG3_48, noteE3_56));
    List<Note> listallnotes = new ArrayList<>(Arrays.asList(noteD4_2, noteD4_16, noteD4_6,
        noteD4_18, noteD4_20, noteD4_34, noteD4_38, noteD4_18, noteD4_48, noteD4_50, noteD4_54,
        noteE4_0, noteE4_8, noteE4_10, noteE4_12, noteE4_24, noteE4_32, noteE4_40, noteE4_42,
        noteE4_44, noteE4_46, noteE4_52, noteC4_4, noteC4_36, noteC4_56, noteG3_0, noteG3_8,
        noteG3_16, noteG3_24, noteG3_24, noteG3_32, noteG3_40, noteG3_48, noteE3_56, noteG4_26,
            noteG4_28));

    MusicPieceModel modelD = MusicPieceFactory.newPieceFromList(listnoteD, 8888);
    MusicPieceModel model2 = MusicPieceFactory.newPieceFromList(new ArrayList<>(), 8888);
    MusicPieceModel modelC = MusicPieceFactory.newPieceFromList(listnoteC, 8888);
    MusicPieceModel model4 = MusicPieceFactory.newPieceFromList(listallnotes, 8888);
    MusicPieceModel modelG = MusicPieceFactory.newPieceFromList(listnoteG, 8888);
    MusicPieceModel modelE = MusicPieceFactory.newPieceFromList(listnoteE, 8888);

    @Test
    public void testplaySimultaneouslysamemodel() throws IOException {
      model4.playSimultaneously(model4);
      StringBuilder out = new StringBuilder();
        MutableViewModel viewModel = MutableViewModel.newViewModel(model4);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
        Controller controller = newInstantController(viewModel, print);
        controller.init();
      assertEquals(
           "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
           " 0                 X                                            X                 \n" +
           " 1                 |                                            |                 \n" +
           " 2                 |                                  X                           \n" +
           " 3                 |                                  |                           \n" +
           " 4                 |                        X                                     \n" +
           " 5                 |                        |                                     \n" +
           " 6                 |                                  X                           \n" +
           " 7                                                    |                           \n" +
           " 8                 X                                            X                 \n" +
           " 9                 |                                            |                 \n" +
           "10                 |                                            X                 \n" +
           "11                 |                                            |                 \n" +
           "12                 |                                            X                 \n" +
           "13                 |                                            |                 \n" +
           "14                 |                                            |                 \n" +
           "15                                                                                \n" +
           "16                 X                                  X                           \n" +
           "17                 |                                  |                           \n" +
           "18                 |                                  X                           \n" +
           "19                 |                                  |                           \n" +
           "20                 |                                  X                           \n" +
           "21                 |                                  |                           \n" +
           "22                 |                                  |                           \n" +
           "23                                                    |                           \n" +
           "24                 X                                            X                 \n" +
           "25                 |                                            |                 \n" +
           "26                                                                             X  \n" +
           "27                                                                             |  \n" +
           "28                                                                             X  \n" +
           "29                                                                             |  \n" +
           "30                                                                             |  \n" +
           "31                                                                             |  \n" +
           "32                 X                                            X                 \n" +
           "33                 |                                            |                 \n" +
           "34                 |                                  X                           \n" +
           "35                 |                                  |                           \n" +
           "36                 |                        X                                     \n" +
           "37                 |                        |                                     \n" +
           "38                 |                                  X                           \n" +
           "39                 |                                  |                           \n" +
           "40                 X                                            X                 \n" +
           "41                 |                                            |                 \n" +
           "42                 |                                            X                 \n" +
           "43                 |                                            |                 \n" +
           "44                 |                                            X                 \n" +
           "45                 |                                            |                 \n" +
           "46                 |                                            X                 \n" +
           "47                 |                                            |                 \n" +
           "48                 X                                  X                           \n" +
           "49                 |                                  |                           \n" +
           "50                 |                                  X                           \n" +
           "51                 |                                  |                           \n" +
           "52                 |                                            X                 \n" +
           "53                 |                                            |                 \n" +
           "54                 |                                  X                           \n" +
           "55                 |                                  |                           \n" +
           "56  X                                       X                                     \n" +
           "57  |                                       |                                     \n" +
           "58  |                                       |                                     \n" +
           "59  |                                       |                                     \n" +
           "60  |                                       |                                     \n" +
           "61  |                                       |                                     \n" +
           "62  |                                       |                                     \n" +
           "63  |                                       |                                     \n",
            out.toString());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4),
            model4.highestPitch());
        assertEquals(64, model4.getLength());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3),
            model4.lowestPitch());
        modelC.playSimultaneously(modelC);
        StringBuilder out1 = new StringBuilder();

        MutableViewModel viewModel1 = MutableViewModel.newViewModel(modelC);
        MusicOutputDevice print1 = MusicOutputDeviceFactory.newMusicPrinter(out1, viewModel1);
        Controller controller2 = newInstantController(viewModel1, print1);
        controller2.init();
        assertEquals("    C4 \n" +
            " 0     \n" +
            " 1     \n" +
            " 2     \n" +
            " 3     \n" +
            " 4  X  \n" +
            " 5  |  \n" +
            " 6     \n" +
            " 7     \n" +
            " 8     \n" +
            " 9     \n" +
            "10     \n" +
            "11     \n" +
            "12     \n" +
            "13     \n" +
            "14     \n" +
            "15     \n" +
            "16     \n" +
            "17     \n" +
            "18     \n" +
            "19     \n" +
            "20     \n" +
            "21     \n" +
            "22     \n" +
            "23     \n" +
            "24     \n" +
            "25     \n" +
            "26     \n" +
            "27     \n" +
            "28     \n" +
            "29     \n" +
            "30     \n" +
            "31     \n" +
            "32     \n" +
            "33     \n" +
            "34     \n" +
            "35     \n" +
            "36  X  \n" +
            "37  |  \n" +
            "38     \n" +
            "39     \n" +
            "40     \n" +
            "41     \n" +
            "42     \n" +
            "43     \n" +
            "44     \n" +
            "45     \n" +
            "46     \n" +
            "47     \n" +
            "48     \n" +
            "49     \n" +
            "50     \n" +
            "51     \n" +
            "52     \n" +
            "53     \n" +
            "54     \n" +
            "55     \n" +
            "56  X  \n" +
            "57  |  \n" +
            "58  |  \n" +
            "59  |  \n" +
            "60  |  \n" +
            "61  |  \n" +
            "62  |  \n" +
            "63  |  \n", out1.toString());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4),
            modelC.highestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4),
            modelC.lowestPitch());
        assertEquals(64, modelC.getLength());
    }

    @Test
    public void testplaySimultaneouslydifferentmodels() throws IOException {
        modelC.playSimultaneously(modelD);
        StringBuilder out = new StringBuilder();

      MutableViewModel viewModel = MutableViewModel.newViewModel(modelC);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();
        assertEquals("    C4  C#4   D4 \n" +
            " 0               \n" +
            " 1               \n" +
            " 2            X  \n" +
            " 3            |  \n" +
            " 4  X            \n" +
            " 5  |            \n" +
            " 6            X  \n" +
            " 7            |  \n" +
            " 8               \n" +
            " 9               \n" +
            "10               \n" +
            "11               \n" +
            "12               \n" +
            "13               \n" +
            "14               \n" +
            "15               \n" +
            "16            X  \n" +
            "17            |  \n" +
            "18            X  \n" +
            "19            |  \n" +
            "20            X  \n" +
            "21            |  \n" +
            "22            |  \n" +
            "23            |  \n" +
            "24               \n" +
            "25               \n" +
            "26               \n" +
            "27               \n" +
            "28               \n" +
            "29               \n" +
            "30               \n" +
            "31               \n" +
            "32               \n" +
            "33               \n" +
            "34            X  \n" +
            "35            |  \n" +
            "36  X            \n" +
            "37  |            \n" +
            "38            X  \n" +
            "39            |  \n" +
            "40               \n" +
            "41               \n" +
            "42               \n" +
            "43               \n" +
            "44               \n" +
            "45               \n" +
            "46               \n" +
            "47               \n" +
            "48            X  \n" +
            "49            |  \n" +
            "50            X  \n" +
            "51            |  \n" +
            "52               \n" +
            "53               \n" +
            "54            X  \n" +
            "55            |  \n" +
            "56  X            \n" +
            "57  |            \n" +
            "58  |            \n" +
            "59  |            \n" +
            "60  |            \n" +
            "61  |            \n" +
            "62  |            \n" +
            "63  |            \n", out.toString());
        assertEquals(64, modelC.getLength());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4),
            modelC.highestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4),
            modelC.lowestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4),
            modelE.lowestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4),
            modelE.highestPitch());
        assertEquals(54, modelE.getLength());
        modelE.playSimultaneously(modelG);   //play modelE and modelG simultaneously
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3),
            modelE.lowestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4),
            modelE.highestPitch());
        assertEquals(64, modelE.getLength());
        StringBuilder out1 = new StringBuilder();
      MutableViewModel viewModel1 = MutableViewModel.newViewModel(modelE);
      MusicOutputDevice print1 = MusicOutputDeviceFactory.newMusicPrinter(out1, viewModel1);
      Controller controller1 = newInstantController(viewModel1, print1);
      controller1.init();

        assertEquals("    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
                " 0                 X                                            X  \n" +
                " 1                 |                                            |  \n" +
                " 2                 |                                               \n" +
                " 3                 |                                               \n" +
                " 4                 |                                               \n" +
                " 5                 |                                               \n" +
                " 6                 |                                               \n" +
                " 7                                                                 \n" +
                " 8                 X                                            X  \n" +
                " 9                 |                                            |  \n" +
                "10                 |                                            X  \n" +
                "11                 |                                            |  \n" +
                "12                 |                                            X  \n" +
                "13                 |                                            |  \n" +
                "14                 |                                            |  \n" +
                "15                                                                 \n" +
                "16                 X                                               \n" +
                "17                 |                                               \n" +
                "18                 |                                               \n" +
                "19                 |                                               \n" +
                "20                 |                                               \n" +
                "21                 |                                               \n" +
                "22                 |                                               \n" +
                "23                                                                 \n" +
                "24                 X                                            X  \n" +
                "25                 |                                            |  \n" +
                "26                                                                 \n" +
                "27                                                                 \n" +
                "28                                                                 \n" +
                "29                                                                 \n" +
                "30                                                                 \n" +
                "31                                                                 \n" +
                "32                 X                                            X  \n" +
                "33                 |                                            |  \n" +
                "34                 |                                               \n" +
                "35                 |                                               \n" +
                "36                 |                                               \n" +
                "37                 |                                               \n" +
                "38                 |                                               \n" +
                "39                 |                                               \n" +
                "40                 X                                            X  \n" +
                "41                 |                                            |  \n" +
                "42                 |                                            X  \n" +
                "43                 |                                            |  \n" +
                "44                 |                                            X  \n" +
                "45                 |                                            |  \n" +
                "46                 |                                            X  \n" +
                "47                 |                                            |  \n" +
                "48                 X                                               \n" +
                "49                 |                                               \n" +
                "50                 |                                               \n" +
                "51                 |                                               \n" +
                "52                 |                                            X  \n" +
                "53                 |                                            |  \n" +
                "54                 |                                               \n" +
                "55                 |                                               \n" +
                "56  X                                                              \n" +
                "57  |                                                              \n" +
                "58  |                                                              \n" +
                "59  |                                                              \n" +
                "60  |                                                              \n" +
                "61  |                                                              \n" +
                "62  |                                                              \n" +
                "63  |                                                              \n",
            out1.toString());
    }

    @Test
    public void testplayconsecutivemodelDandG() throws IOException {
        assertEquals(56, modelD.getLength());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4),
            modelD.highestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4),
            modelD.lowestPitch());
        modelD.playConsecutively(modelG); //play modelE and model4 consecutively
        assertEquals(64, modelG.getLength());
        assertEquals(120, modelD.getLength());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4),
            modelD.highestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3),
            modelD.lowestPitch());
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(modelD);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

        assertEquals("     E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4 \n" +
                "  0                                                       \n" +
                "  1                                                       \n" +
                "  2                                                    X  \n" +
                "  3                                                    |  \n" +
                "  4                                                       \n" +
                "  5                                                       \n" +
                "  6                                                    X  \n" +
                "  7                                                    |  \n" +
                "  8                                                       \n" +
                "  9                                                       \n" +
                " 10                                                       \n" +
                " 11                                                       \n" +
                " 12                                                       \n" +
                " 13                                                       \n" +
                " 14                                                       \n" +
                " 15                                                       \n" +
                " 16                                                    X  \n" +
                " 17                                                    |  \n" +
                " 18                                                    X  \n" +
                " 19                                                    |  \n" +
                " 20                                                    X  \n" +
                " 21                                                    |  \n" +
                " 22                                                    |  \n" +
                " 23                                                    |  \n" +
                " 24                                                       \n" +
                " 25                                                       \n" +
                " 26                                                       \n" +
                " 27                                                       \n" +
                " 28                                                       \n" +
                " 29                                                       \n" +
                " 30                                                       \n" +
                " 31                                                       \n" +
                " 32                                                       \n" +
                " 33                                                       \n" +
                " 34                                                    X  \n" +
                " 35                                                    |  \n" +
                " 36                                                       \n" +
                " 37                                                       \n" +
                " 38                                                    X  \n" +
                " 39                                                    |  \n" +
                " 40                                                       \n" +
                " 41                                                       \n" +
                " 42                                                       \n" +
                " 43                                                       \n" +
                " 44                                                       \n" +
                " 45                                                       \n" +
                " 46                                                       \n" +
                " 47                                                       \n" +
                " 48                                                    X  \n" +
                " 49                                                    |  \n" +
                " 50                                                    X  \n" +
                " 51                                                    |  \n" +
                " 52                                                       \n" +
                " 53                                                       \n" +
                " 54                                                    X  \n" +
                " 55                                                    |  \n" +
                " 56                 X                                     \n" +
                " 57                 |                                     \n" +
                " 58                 |                                     \n" +
                " 59                 |                                     \n" +
                " 60                 |                                     \n" +
                " 61                 |                                     \n" +
                " 62                 |                                     \n" +
                " 63                                                       \n" +
                " 64                 X                                     \n" +
                " 65                 |                                     \n" +
                " 66                 |                                     \n" +
                " 67                 |                                     \n" +
                " 68                 |                                     \n" +
                " 69                 |                                     \n" +
                " 70                 |                                     \n" +
                " 71                                                       \n" +
                " 72                 X                                     \n" +
                " 73                 |                                     \n" +
                " 74                 |                                     \n" +
                " 75                 |                                     \n" +
                " 76                 |                                     \n" +
                " 77                 |                                     \n" +
                " 78                 |                                     \n" +
                " 79                                                       \n" +
                " 80                 X                                     \n" +
                " 81                 |                                     \n" +
                " 82                                                       \n" +
                " 83                                                       \n" +
                " 84                                                       \n" +
                " 85                                                       \n" +
                " 86                                                       \n" +
                " 87                                                       \n" +
                " 88                 X                                     \n" +
                " 89                 |                                     \n" +
                " 90                 |                                     \n" +
                " 91                 |                                     \n" +
                " 92                 |                                     \n" +
                " 93                 |                                     \n" +
                " 94                 |                                     \n" +
                " 95                 |                                     \n" +
                " 96                 X                                     \n" +
                " 97                 |                                     \n" +
                " 98                 |                                     \n" +
                " 99                 |                                     \n" +
                "100                 |                                     \n" +
                "101                 |                                     \n" +
                "102                 |                                     \n" +
                "103                 |                                     \n" +
                "104                 X                                     \n" +
                "105                 |                                     \n" +
                "106                 |                                     \n" +
                "107                 |                                     \n" +
                "108                 |                                     \n" +
                "109                 |                                     \n" +
                "110                 |                                     \n" +
                "111                 |                                     \n" +
                "112  X                                                    \n" +
                "113  |                                                    \n" +
                "114  |                                                    \n" +
                "115  |                                                    \n" +
                "116  |                                                    \n" +
                "117  |                                                    \n" +
                "118  |                                                    \n" +
                "119  |                                                    \n",
            out.toString());
    }

    @Test
    public void testnonotesmodel() throws IOException {
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(model2);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();
        assertEquals(" \n", out.toString());  //may not right
        model2.playSimultaneously(model4);
        assertEquals(64, model2.getLength());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4),
            model2.highestPitch());
        assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3),
            model2.lowestPitch());
    }
    /**
     * Tests that init works on a printer
     * @throws IOException
     */
    @Test
    public void testInit() throws IOException {
        MusicPieceModel piece = MusicPieceFactory
                .newEmptyPiece(6666)
                .addNote(NoteFactory.newNote(0, 1, SamplePitches.D3, 0, 0))
                .addNote(NoteFactory.newNote(0, 1, SamplePitches.A4, 0, 0));
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

        assertEquals("   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3 " +
                "  C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4 \n" +
                "0  X                                                " +
                "                                              X  \n", out.toString());
    }

    /**
     * Tests that playing a single note works
     * @throws IOException
     */
    @Test
    public void testPlayOneNote() throws IOException {

        MusicPieceModel piece = MusicPieceFactory
                .newEmptyPiece(6666)
                .addNote(NoteFactory.newNote(0, 3, SamplePitches.D3, 0, 0));
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

        assertEquals("   D3 \n" +
                     "0  X  \n" +
                     "1  |  \n" +
                     "2  |  \n", out.toString());
    }

    /**
     * Tests that playing two notes works
     * @throws IOException
     */
    @Test
    public void testPlayTwoNotes() throws IOException {

        MusicPieceModel piece = MusicPieceFactory
                .newEmptyPiece(6666)
                .addNote(NoteFactory.newNote(0, 4, SamplePitches.D3, 0, 0))
                .addNote(NoteFactory.newNote(2, 2, SamplePitches.D3.changePitch(2), 0, 0));
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

        assertEquals("   D3  D#3   E3 \n" +
                     "0  X            \n" +
                     "1  |            \n" +
                     "2  |         X  \n" +
                     "3  |         |  \n", out.toString());
    }

    /**
     * Tests that playing two overlapping notes works
     * @throws IOException
     */
    @Test
    public void testPlayOverlappingNotes() throws IOException {

        MusicPieceModel piece = MusicPieceFactory
                .newEmptyPiece(6666)
                .addNote(NoteFactory.newNote(0, 4, SamplePitches.D3, 0, 0))
                .addNote(NoteFactory.newNote(2, 2, SamplePitches.D3, 0, 0))
                .addNote(NoteFactory.newNote(2, 2, SamplePitches.D3.changePitch(2), 0, 0));
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

        assertEquals("   D3  D#3   E3 \n" +
                     "0  X            \n" +
                     "1  |            \n" +
                     "2  X         X  \n" +
                     "3  |         |  \n", out.toString());
    }


    /**
     * Tests that a copy plays the same way
     * @throws IOException
     */
    @Test
    public void testCopy() throws IOException {
        MusicPieceModel piece = SamplePitches.LightlyRow4Measures();
        MusicPieceModel copy = SamplePitches.LightlyRow4Measures();
        StringBuilder out = new StringBuilder();
        StringBuilder outCopy = new StringBuilder();
        MutableViewModel viewModel1 = MutableViewModel.newViewModel(piece);
        MutableViewModel viewModel2 = MutableViewModel.newViewModel(copy);
        MusicOutputDevice printOrig = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel1);
        MusicOutputDevice printCopy = MusicOutputDeviceFactory.newMusicPrinter(outCopy, viewModel2);
        Controller controller1 = newInstantController(viewModel1, printOrig);
        Controller controller2 = newInstantController(viewModel2, printCopy);
        controller1.init();
        controller2.init();
        assertEquals(out.toString(), outCopy.toString());
    }

    /**
     * Tests that playing two overlapping notes works
     * @throws IOException
     */
    @Test
    public void testPlayPiece() throws IOException {

        MusicPieceModel piece = SamplePitches.LightlyRow4Measures();
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

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
                     "13                                     X  \n"+
                     "14                                     X  \n"+
                     "15                                     |  \n", out.toString());
    }

    /**
     * Tests that playing two overlapping pieces works
     * @throws IOException
     */
    @Test
    public void testOverlayPiece() throws IOException {

        MusicPieceModel piece = SamplePitches.LightlyRow4Measures()
                            .playSimultaneously(MusicPieceFactory
                                    .newMetronome(SamplePitches.D4, 4, 4, 6666));
        StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

        assertEquals("    D4  D#4   E4   F4  F#4   G4  G#4   A4 \n" +
                     " 0  X                                  X  \n" +
                     " 1  |                   X                 \n" +
                     " 2  |                   X                 \n" +
                     " 3  |                   |                 \n" +
                     " 4  X                        X            \n" +
                     " 5  |         X                           \n" +
                     " 6  |         X                           \n" +
                     " 7  |         |                           \n" +
                     " 8  X                                     \n" +
                     " 9  |         X                           \n" +
                     "10  |                   X                 \n" +
                     "11  |                        X            \n" +
                     "12  X                                  X  \n" +
                     "13  |                                  X  \n"+
                     "14  |                                  X  \n"+
                     "15  |                                  |  \n", out.toString());
    }

    /**
     * Tests that playing two overlapping notes works
     * @throws IOException
     */
    @Test
    public void testAppendPiece() throws IOException {

      MusicPieceModel piece = SamplePitches.LightlyRow4Measures()
          .playConsecutively(MusicPieceFactory
              .newEmptyPiece(6666)
              .addNote(NoteFactory.newNote(0, 1, SamplePitches.A4, 0, 0))
              .addNote(NoteFactory.newNote(1, 1, SamplePitches.FSharp4, 0, 0))
              .addNote(NoteFactory.newNote(2, 1, SamplePitches.FSharp4, 0, 0)));
      StringBuilder out = new StringBuilder();
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();

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
                     "15                                     |  \n" +
                     "16                                     X  \n" +
                     "17                      X                 \n" +
                     "18                      X                 \n", out.toString());
    }
  
    /**
     * Tests that IOExceptions are caught by the controller
     */
    @Test
    public void testIOException() {
        MusicPieceModel piece = SamplePitches.LightlyRow4Measures()
                .playSimultaneously(MusicPieceFactory.newMetronome(SamplePitches.D4, 4, 4, 6666));
        Appendable out = new Appendable() {
            @Override
            public Appendable append(CharSequence csq) throws IOException {
                throw new IOException("Auto-error");
            }

            @Override
            public Appendable append(CharSequence csq, int start, int end) throws IOException {
                throw new IOException("Auto-error");
            }

            @Override
            public Appendable append(char c) throws IOException {
                throw new IOException("Auto-error");
            }
        };
      MutableViewModel viewModel = MutableViewModel.newViewModel(piece);
      MusicOutputDevice print = MusicOutputDeviceFactory.newMusicPrinter(out, viewModel);
      Controller controller = newInstantController(viewModel, print);
      controller.init();
    }
}
