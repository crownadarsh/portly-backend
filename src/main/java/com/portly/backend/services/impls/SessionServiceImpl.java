package com.portly.backend.services.impls;


import com.portly.backend.entities.Session;
import com.portly.backend.entities.User;
import com.portly.backend.repositories.SessionRepository;
import com.portly.backend.services.SessionService;
import com.portly.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    @SuppressWarnings("FieldCanBeLocal")
    private final int SESSION_LIMIT = 1;


    @Override
    public void createSession(User user, String refreshToken) {

        List<Session> userSessions = sessionRepository.findByUser(user);

        if(userSessions.size() == SESSION_LIMIT){
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }


        Session newSession = Session.builder()
                .refreshToken(refreshToken)
                .user(user)
                .build();
        sessionRepository.save(newSession);

    }

    @Override
    public void validateSession(String refreshToken) {

        Session session = sessionRepository.findByRefreshToken(refreshToken).orElseThrow(() ->
                new SessionAuthenticationException("Session is not found with refresh token. "));

        session.setLastUsedAt(LocalDateTime.now());
        session.setId(null);
        sessionRepository.save(session);

    }
}
