package org.example.author;

public interface AuthorEventListener {
    void onAuthorAdded(Author author);
    void onAuthorUpdated(Author author);
    void onAuthorDeleted(Long authorId);
}