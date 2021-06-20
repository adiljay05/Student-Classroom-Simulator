package Assignment01;

public class Monitor extends Thread {

    public Classroom[] classroom;

    Monitor(Classroom[] classroom) {
        this.classroom = classroom;
    }
    static int counter = 1;

    @Override
    public void run() {
        System.out.println("  ----------------------------- ("+counter+++") ---------------------------------");
        for (Classroom c : classroom) {
            System.out.print(c.className);
            if (c.lecturer != null) System.out.print("\t\t" + c.lecturer); //if there is some lecturer, print his name
            else System.out.print("\t\tfalse"); //else print false for lecturer name on output
            System.out.print("\t\t" + c.inClass + "\t\t"); //inClass either true or false, just print it.
            if (c.lecturer != null) System.out.print(c.filled); //count of students in the class
            if (c.lecturer != null) System.out.print("\t\t\t" + c.filledVisitor);   //count of visitors in class
            if (c.inClass && c.lecturer == null) System.out.print("No Professor Assigned to this class yet");
            System.out.println();
        }
    }
}