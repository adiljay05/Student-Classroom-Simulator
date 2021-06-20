package Assignment01;

import java.util.concurrent.Semaphore;

class Classroom {
    // Classroom variables
    public String className;
    public String lecturer;
    public Semaphore lecturerSemaphore = new Semaphore(1);
    public Semaphore studentVisitorSemaphore;
    public boolean inClass = false;
    public int filled;
    public int capacity;
    public int filledVisitor = 0;

    // Constructor
    public Classroom(int permit, String className, int capacity) {
        this.studentVisitorSemaphore = new Semaphore(permit);
        this.filled = 0;
        this.className = className;
        this.capacity = capacity;
    }

    // Check if the class is full
    public boolean checkClassFull() {
        return capacity == filled;
    }
}