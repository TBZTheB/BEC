public class Test1 {
    public static char grade(int averageGrade) {
        if (averageGrade > 90 && averageGrade <= 100)
            return 'A';
        else if (averageGrade > 82 && averageGrade <= 90)
            return 'B';
        else if (averageGrade > 70 && averageGrade <= 82)
            return 'C';
        else if (averageGrade > 60 && averageGrade <= 68) {
            if (averageGrade + 4 <= 69) return 'A';
            return 'D';
        }
        else if (averageGrade >= 0 && averageGrade <= 60)
            return 'F';
        return 'I'; // 'I' for invalid input
    }
}
