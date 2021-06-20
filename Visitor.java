package Assignment01;

public class Visitor extends Thread {

    public boolean isSitting = false;
    public Classroom obj;

    // Constructor
    Visitor(Classroom obj) {
        if (obj.studentVisitorSemaphore.availablePermits() > 0) {
            this.obj = obj;
        }
    }

    // Enter class function
    public void enter() {
        if (!this.obj.checkClassFull()) {   //if class is not full
            this.obj.filledVisitor++;   //add the visitor to sit in the class
            sitDown();
        }
    }

    // sit function of visitor
    public void sitDown() {
        try {
            this.isSitting = true; //put visitor in sit position
            obj.studentVisitorSemaphore.acquire();// keep visitor in siting position
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Leave class function
    public void leave() {
        obj.studentVisitorSemaphore.release();// Semaphore released
    }

    // Override run method of Thread class
    @Override
    public void run() {
        enter();
    }
}