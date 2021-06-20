package Assignment01;

import java.util.Random;

class threadClass implements Runnable {

    public static Random rand;
    public static Classroom []classrooms;
    public static Visitor[] visitors = new Visitor[5];  //maximum 5 visitors
    public static Student[] students = new Student[65]; //maximum 60+5(visitors) students in a class
    public static Lecturer[] lecturers = new Lecturer[6];   //total given lecturers

    public static void simulate() {
        rand = new Random();

        classrooms = new Classroom[]{new Classroom(60, "W201", 60), //given classrooms
                new Classroom(60, "W202", 60),      //with their allowed capacity
                new Classroom(20, "JS101", 20),
                new Classroom(30, "W101", 30)};

        for (int i=0;i<5;i++)
            visitors[i] = new Visitor(classrooms[rand.nextInt(4)]);

        String []lecturersNames = {"Osama","Barry","Faheem","Alex","Aqeel","Waseem"};
        for (int i=0;i<lecturersNames.length;i++)
            lecturers[i] = new Lecturer(lecturersNames[i], classrooms[rand.nextInt(4)], students);

        //start threads for students+visitors+lecturers+monitor board
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i, classrooms[rand.nextInt(4)]);
            students[i].start();
        }
        for (Visitor visitor : visitors) visitor.start();
        for (Lecturer lecturer : lecturers) lecturer.start();
        Monitor monitor = new Monitor(classrooms);
        monitor.start();

        //start the lecture
        for (Lecturer lecturer : lecturers) lecturer.startLecture();

        try {
            //join threads
            for (Student student : students) student.join();
            for (Lecturer lecturer : lecturers) lecturer.join();
            monitor.join();
            for (Lecturer lecturer : lecturers) lecturer.join();
            //end the lecture
            for (Lecturer lecturer : lecturers) lecturer.leave();
            //remove the students if lecture is not running
            for (Classroom classroom : classrooms)
                if (!classroom.inClass) {   //if professor not in class, let the students leave
                    for (Student student : students) {
                        if (student.isSitting) continue;
                        student.leave();
                    }
                    for (Visitor v : visitors){
                        if(v.isSitting) continue;
                        v.leave();
                    }
                }
        } catch (Exception ignored) {
        }
    }

    public void run() {
        do
            try {
                simulate();     // simulating the classroom for 1 cycle
                Thread.sleep(2000); // sleeping for 2 seconds to start next cycle
            } catch (Exception ignored) {
            } while (true);
    }
}