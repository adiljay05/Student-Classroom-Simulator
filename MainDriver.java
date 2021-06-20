package Assignment01;

class MainDriver {
    public static void printHeader(){   //header for monitor board
        System.out.println("=======================================================================");
        System.out.println("Classroom\tLecturer\tInSession\tStudents\tVisitors");
        System.out.println("=======================================================================");
    }
    public static void main(String[] args) {
        printHeader();

        threadClass t = new threadClass();
        Thread obj = new Thread(t); //start the simulation class via threads
        obj.start();
    }
}