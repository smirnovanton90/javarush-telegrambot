package com.github.smirnovanton90.jrtb.service;

/**
 * Service for finding new posts.
 */
public interface FindNewPostService {

    /**
     * Find new posts and notify subscribers about it.
     */
    void findNewPosts();
}