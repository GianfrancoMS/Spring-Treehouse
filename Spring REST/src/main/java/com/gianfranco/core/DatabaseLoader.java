package com.gianfranco.core;

import com.gianfranco.course.Course;
import com.gianfranco.course.CourseRepository;
import com.gianfranco.review.Review;
import com.gianfranco.user.User;
import com.gianfranco.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> templates = Arrays.asList(
                "Up and Running with %s",
                "%s Basics",
                "%s for Beginners",
                "%s for Neckbeards",
                "Under the hood: %s"
        );

        List<String> buzzwords = Arrays.asList(
                "Spring REST Data",
                "Java 9",
                "Scala",
                "Groovy",
                "Hibernate",
                "Spring HATEOAS"
        );

        List<User> students = Arrays.asList(
                new User("admin", "Gianfranco", "Monzon", "admin", new String[]{"ROLE_USER", "ROLE_ADMIN"}),
                new User("jacobproffer", "Jacob", "Proffer", "password", new String[]{"ROLE_USER"}),
                new User("mlnorman", "Mike", "Norman", "password", new String[]{"ROLE_USER"}),
                new User("k_freemansmith", "Karen", "Freeman-Smith", "password", new String[]{"ROLE_USER"}),
                new User("seth_lk", "Seth", "Kroger", "password", new String[]{"ROLE_USER"}),
                new User("mrstreetgrid", "Java", "Vince", "password", new String[]{"ROLE_USER"}),
                new User("anthonymikhail", "Tony", "Mikhail", "password", new String[]{"ROLE_USER"}),
                new User("boog690", "AJ", "Teacher", "password", new String[]{"ROLE_USER"}),
                new User("faelor", "Erik", "Faelor Shafer", "password", new String[]{"ROLE_USER"}),
                new User("christophernowack", "Christopher", "Nowack", "password", new String[]{"ROLE_USER"}),
                new User("calebkleveter", "Caleb", "Kleveter", "password", new String[]{"ROLE_USER"}),
                new User("richdonellan", "Rich", "Donnellan", "password", new String[]{"ROLE_USER"}),
                new User("albertqerimi", "Albert", "Qerimi", "password", new String[]{"ROLE_USER"})
        );

        userRepository.save(students);

        List<Course> courses = new ArrayList<>();

        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates.get(i % templates.size());
                    String buzzword = buzzwords.get(i % buzzwords.size());
                    String title = String.format(template, buzzword);
                    Course course = new Course(title, "http://wwww.example.com");
                    Review review = new Review(i % 5, String.format("Moar %s please!!", buzzword));
                    review.setReviewer(students.get(i % students.size()));
                    course.addReview(review);
                    courses.add(course);
                });

        courseRepository.save(courses);
    }
}
