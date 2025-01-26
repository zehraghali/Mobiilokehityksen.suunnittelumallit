open class Human(val name: String, var age: Int) {
    fun getOlder() {
        age++
    }
}

data class CourseRecord(
    val name: String,
    val yearCompleted: Int,
    val credits: Int,
    val grade: Double
)

class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalCredits = courses.sumOf { it.credits }
        val weightedSum = courses.sumOf { it.grade * it.credits }
        return if (totalCredits > 0) weightedSum / totalCredits else 0.0
    }

    fun weightedAverage(year: Int): Double {
        val filteredCourses = courses.filter { it.yearCompleted == year }
        val totalCredits = filteredCourses.sumOf { it.credits }
        val weightedSum = filteredCourses.sumOf { it.grade * it.credits }
        return if (totalCredits > 0) weightedSum / totalCredits else 0.0
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val grades = courses.map { it.grade }
        return Pair(grades.minOrNull() ?: 0.0, grades.maxOrNull() ?: 0.0)
    }
}

class Major(val name: String) {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }.filter { it > 0.0 }
        val min = averages.minOrNull() ?: 0.0
        val max = averages.maxOrNull() ?: 0.0
        val average = if (averages.isNotEmpty()) averages.average() else 0.0
        return Triple(min, max, average)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val grades = students.flatMap { student ->
            student.courses.filter { it.name == courseName }.map { it.grade }
        }
        val min = grades.minOrNull() ?: 0.0
        val max = grades.maxOrNull() ?: 0.0
        val average = if (grades.isNotEmpty()) grades.average() else 0.0
        return Triple(min, max, average)
    }
}
