Files:
model:
-interface AbsolutePitch
-class AbsolutePitchImpl
-class AbsolutePitchRange
-class MusicPieceFactory
-interface MusicPieceModel
-class MusicPieceModelImpl
-interface Note
-class NoteImpl
-class NoteComparator
-enum RelativePitch
-class NoteFactory
-class AbsolutePitchFactory

view:
-class ConcreteGuiViewPanel
-class GuiViewFrame
-class MidiViewImpl
-interface MusicOutputDevice
-class MusicOutputDeviceFactory
-class MusicPrinter
-interface BeatListener
-class CompositeView
-interface GuiView
-interface MutableViewModel
-interface ViewModel
-class ViewModleImpl

util:
-interface CompositionBuilder
-class MusicReader

Controller: 

-interface Controller 
-interface BeatController
-class ControllerFactory
-class EditorController
-class InstantController
-class KeyboardHandler
-class MouseHandler
-class MidiController
-class StaticController
-class TimedController


default package:
-class MusicEditor

Test Files:

model:
-class PackageNoteComparatorTest

tests:
-class AbsolutePitchRangeTest
-class AbsolutePitchTest
-class MockMidiSyth
-class MockMidiSythTest
-class MusicOutputDeviceFactoryTest
-class MusicPieceModelFactoryTest
-class MusicPieceModelImplTest
-class MusicPrintingTest
-class NoteImplTest
-class RelativePitchTest
-class SamplePitches

controllers:
-class KeyboardHandlerTest
-class MouseHandlerTest
-class MockGuiView
-class EditorControllerTest
-class MutableViewModelTest
-class StaticControllerTest

view:
-class CompositeViewTest

Model:

    We made few changes to our model for this assignment. Those changes we did
make consisted primarily of bug fixes, improved documentation, and a couple
instances of renaming for clarity and ease of use.

View:

    As we predicted on the self-evaluation for the last homework, we abstracted
the Timer out of our MidiView and into the controller. We facilitated interaction
between the views and the controller using a combination of listeners and a
viewmodel, as suggested by the grader on our last assignment.
    Our viewmodel served to statically enforce good design by disallowing our
view from modifying the program state. This was accomplished by placing all
setter methods in a secondary MutableViewModel interface. While the controller
interacted with these MutableViewModels, the view interface accepted a standard
ViewModel. Thus, even 3rd party views must respect this invariant. The viewmodel
also served as an intermediary between the controller and the view by calling
the view's update callbacks when the controller altered its data.
    Playback was handled through the BeatListener and BeatController interfaces.
These interfaces provided an abstract specification for communication between
the view and the controller. They allowed us to use the callback-based MIDI
implementation we had developed without depending on a swing Timer and to
simply tie multiple views to the same controller, making the composite view
trivial to implement.

Controller:
    Our controller used the KeyHandler class as recommended. In addition, we
implemented a similar helper class for handling mouse and mouse motion events.
Our controller also responded to adjustment events as sent by the scrollbars
using simple lambdas for callbacks. Upon receiving an event, our controller
would compute the necessary state updates and mutate the viewmodel. The
setters in the viewmodel would then alert the view that an update had occured
and the view would update its rendering.

Users can use several key and mouse event combinations to edit the music piece:
Instructions:
1.	Click the head of a note to select it.
2.	When a note is selected, the head of that note has a "magenta" colored border.
3.	Press “enter” to add a note at the currently selected position.
4.	After pressing “enter,” a new window will pop up for you
    to input the “instrument,” “volume” and “duration.”
5.	Press “delete” to delete the selected note.
6.	Use “[” and “]” to decrease and increase a note’s duration (Open and closed braces).
7.	Can not decrease the note’s duration below 1.
8.	Drag note using left mouse button, release the left mouse button to the position
    that you wan to put your note at.
9.	Use “space” to pause the music.
10.	Use “shift” + “arrow right” to fast forward.
11.	Use “shift” + “arrow left” to rewind.
12.	Use “Home” to replay the music back from the beginning.
13.	Use “End” to jump the music to the end.
14.	Use “arrow” keys to move the screen.


 View Package:



                   +--------------------+                         +---------------+
            +-----X|I: MusicOutputDevice| <----------+------------+C: MusicPrinter|
            |      +--------------------+            |            +---------------+
            |                                        |
        +---+-------+           +----------------+   |  +-------------+    +--------------------+
        |I: GuiView |           |I: BeatListener |X-----+I: ViewModel |X---+I: MutableViewModel |
        +------+-+--+           +----------------+   |  +-------------+    +--------------+-----+
               ^ ^                                   |                                    ^
               | +------------------+                +--------+                           |
               |                    |                         |                           |
         +-----+----------+     +---+------------+     +------+--------+       +----------+------+
         |C: CompositeView|     |C: GuiViewFrame |     |C: MidiViewImpl|       |C: ViewModelImpl |
         +----------------+     +----------------+     +---------------+       +-----------------+




Controller Package:     +------------------------+-----------+
                        v                        v           |
                                                             |
         +------------------+           +---------------+    |
         |I: BeatController |           |I: Controller  |    |
         +--------+---------+           +----+----------+    |
                  ^                          ^               |
                  +---------+----------------+               |
+--------------------+      |   +---------------------+      |     +-----------------+
|C: EditorController +--+   |   |C: InstantController |      |     |C: MidiController|
+--------------------+  |   |   +-----------------+---+      |     +--------+--------+
                        |   |                     |          |              |
                        +------------------+      +---+      |              |
                            |              |          X      |              |
                 +----------+--------+     |     +-----------+------+       |
                 |C: StaticController|     +---X |C: TimedController| X-----+
                 +-------------------+           +------------------+

Model Package:

        +---------------------+              +--------------------+
        |I: CompositionBuilder| X------------+I: MusicPieceModel  |
        +---------------------+              +----------+---------+
                                                        ^
                                                        |
                                                        |
                                                        |
                                                        |
                                            +-----------+----------+
                                            |C: MusicPieceModelImpl|
                                            +----------------------+



        +-----------------+             +----------------------------+
        |I: AbsolutePitch +-----------X |I: Comparable<AbsolutePitch>|
        +-------+---------+             +----------------------------+
                ^
                |                                                      +---------------------------+
                |                                 +------------------> |I: Iterable<AbsolutePitch> |
                |                                 |                    +---------------------------+
       +--------+-----------+         +-----------+----------+           +------------------------+
       |C: AbsolutePitchImpl|         |C: AbsolutePitchRange |           | enum: RelativePitch    |
       +--------------------+         +----------------------+           +------------------------+



       +---------+              +--------------------+
       |I: Note  |              |I: Comparator<Note> |
       +----+----+              +-------+------------+
            ^                           ^
            |                           |
      +-----+-------+            +------+-----------+
      |C: NoteImpl  |            |C: NoteComparator |
      +-------------+            +------------------+
