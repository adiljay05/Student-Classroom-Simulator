package Assignment01;

public class Lecturer extends Thread {

    public boolean isLectureRunning = false;
    public String lecturerName;
    public Classroom obj;
    public Student[] array;

    // Constructor for Lecturer Class
    Lecturer(String lecturerName, Classroom obj, Student[] array) {
        this.lecturerName = lecturerName;   //lecturer name
        if (obj.studentVisitorSemaphore.availablePermits() > 0) this.obj = obj; //if no professor holds a class, assign it
        this.array = array; //student array for class
    }

    // Enter class function
    public void enter() {
        if (obj.lecturerSemaphore.availablePermits() > 0) //if class not already occupied/filled
            try {
                this.obj.lecturer = lecturerName;
                obj.lecturerSemaphore.acquire();// lock a professor on it.
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    // Start lecture function
    public void startLecture() {
        int count = 0;
        // count the number of students sitting in class
        for (Student stu : array) //for every student
            if (stu.obj != null && obj != null)     //if student class is not null and professor class is also not null
                if (stu.obj.className.equals(obj.className))    //if the student and professor are in same class
                    if (stu.isSitting) count++;     //increase the student count

        if (obj != null) {
            if (obj.filled == count) {  //if all present students are sitting,
                this.obj.inClass = true;   //start the lecture
                isLectureRunning = true;
            }
        }
    }

    // stop the lecture
    public void leave() {
        if (obj.inClass) {     //if lecture is running
            obj.inClass = false;   //stop the lecture
            obj.lecturer = "";  //empty the lecturer
            obj.lecturerSemaphore.release(); // release the semaphore lock
        }
    }

    // call enter function which is required.
    @Override
    public void run() {
        enter();
    }
}