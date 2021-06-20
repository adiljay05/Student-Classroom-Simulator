package Assignment01;

public class Student extends Thread {

    public boolean isSitting = false;
    public Classroom obj;
    public int rollNo;

    // Constructor for student class
    Student(int rollNo, Classroom obj) {
        this.rollNo = rollNo;
        if (obj.studentVisitorSemaphore.availablePermits() > 0) this.obj = obj; // if the class prmits the student, assign him a class
    }

    // Enter class function
    public void enter() {
        if (this.obj != null) { //if class assigned to student
            if (!this.obj.checkClassFull()) {   //and if the class is not full
                sitDown();  //let the student sit down
            }
        }
    }

    // Sit down function
    public void sitDown() {
        try {
            this.isSitting = true;  //set student position
            this.obj.studentVisitorSemaphore.tryAcquire();  //lock the student in that position
            this.obj.filled++;// add the student to list of student who are sitting in the calss
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Leave class function
    public void leave() {
        if (!obj.inClass && obj.lecturer.equals("")) { //if the lecture is over and lecturer is gone
            isSitting = false;  //let the student stand up
            obj.studentVisitorSemaphore.release();// let the student leave the class by releasing semaphore.
        }
    }

    // call the enter function from run function as it was required in the PDF
    @Override
    public void run() {
        enter();
    }
}