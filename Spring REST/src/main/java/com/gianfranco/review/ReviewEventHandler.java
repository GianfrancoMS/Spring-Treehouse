package com.gianfranco.review;

import com.gianfranco.user.User;
import com.gianfranco.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Review.class)
public class ReviewEventHandler {

    @Autowired
    private UserRepository userRepository;

    @HandleBeforeCreate
    public void addReviewerBasedOnLoggedUser(Review review) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        review.setReviewer(user);
    }
}
