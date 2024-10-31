package org.example.author;

import java.util.ArrayList;
import java.util.List;

public class AuthorEventNotifier {
    private List<AuthorEventListener> listeners = new ArrayList<>();

    public void addListener(AuthorEventListener listener) {
        listeners.add(listener);
    }

    public void notifyAuthorAdded(Author author) {
        for (AuthorEventListener listener : listeners) {
            listener.onAuthorAdded(author);
        }
    }

    public void notifyAuthorUpdated(Author author) {
        for (AuthorEventListener listener : listeners) {
            listener.onAuthorUpdated(author);
        }
    }

    public void notifyAuthorDeleted(Long authorId) {
        for (AuthorEventListener listener : listeners) {
            listener.onAuthorDeleted(authorId);
        }
    }
}