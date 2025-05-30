import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

class Child {
    private String fullName;
    private LocalDate dateOfBirth;

    public Child(String fullName, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public MonthDay getMonthDayOfBirth() {
        return MonthDay.from(dateOfBirth);
    }

    public LocalDate getNextBirthday() {
        LocalDate today = LocalDate.now();
        MonthDay birthMonthDay = getMonthDayOfBirth();
        LocalDate nextBirthday = birthMonthDay.atYear(today.getYear());

        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1); // Sang năm sau
        }
        return nextBirthday;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Child> children = Arrays.asList(
                new Child("Nguyễn Văn An", LocalDate.of(2014, 6, 15)),
                new Child("Trần Thị Bình", LocalDate.of(2013, 12, 1)),
                new Child("Lê Hữu Cường", LocalDate.of(2012, 7, 5)),
                new Child("Phạm Ngọc Dung", LocalDate.of(2015, 5, 30)),
                new Child("Hoàng Minh Đức", LocalDate.of(2014, 1, 10))
        );

        LocalDate today = LocalDate.now();
        Child closestBirthdayChild = null;
        long minDaysUntilBirthday = Long.MAX_VALUE;

        for (Child child : children) {
            LocalDate nextBirthday = child.getNextBirthday();
            long daysUntilBirthday = ChronoUnit.DAYS.between(today, nextBirthday);

            if (daysUntilBirthday < minDaysUntilBirthday) {
                minDaysUntilBirthday = daysUntilBirthday;
                closestBirthdayChild = child;
            }
        }

        if (closestBirthdayChild != null) {
            MonthDay birthday = closestBirthdayChild.getMonthDayOfBirth();
            System.out.println("Bạn có sinh nhật gần nhất sắp tới:");
            System.out.println("Họ tên: " + closestBirthdayChild.getFullName());
            System.out.println("Ngày sinh: " + birthday.getDayOfMonth() + "/" + birthday.getMonthValue());
        } else {
            System.out.println("Không tìm thấy sinh nhật phù hợp.");
        }
    }
}
