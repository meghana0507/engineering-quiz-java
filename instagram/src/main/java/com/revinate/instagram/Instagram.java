package com.revinate.instagram;

import java.util.*;

/**
 * Your Instagram object will be instantiated and called as such:
 * Instagram instagram = new Instagram();
 * instagram.postMedia(userId,mediaId);
 * List<Integer> feed = instagram.getMediaFeed(userId);
 * instagram.follow(followerId,followedId);
 * instagram.unfollow(followerId,followedId);
 */

public class Instagram {

    /** Initialize your data structure here. */

    //ArrayList of feeds with userId and mediaId
    List<List<Integer>> feedInfo;

    //HashMap with followerId as key and List of followedIds as value
    HashMap<Integer, List<Integer>> followsInfo;

    public Instagram()
    {
        this.feedInfo = new ArrayList<List<Integer>>();
        this.followsInfo = new HashMap<Integer, List<Integer>>();
    }

    /** Add a new media. */
    public void postMedia(int userId, int mediaId)
    {
        List<Integer> mediaInfo = new ArrayList<Integer>();

        mediaInfo.add(userId);
        mediaInfo.add(mediaId);

        feedInfo.add(mediaInfo);
    }

    /** Retrieve the 10 most recent media ids in the user's media feed.
     * Each media must be posted by the user herself or by someone the user follows
     * Media must be ordered from most recent to least recent. */
    public List<Integer> getMediaFeed(int userId)
    {
        List<Integer> resultFeed = new ArrayList<Integer>();

        for(List<Integer> li : feedInfo)
        {
            List<Integer> tmpFollows = new ArrayList<Integer>();

            if(followsInfo.containsKey(userId))
            {
                tmpFollows = followsInfo.get(userId);
            }

            if( (li.get(0) == userId) || (tmpFollows.contains(li.get(0))) )
            {
                resultFeed.add(li.get(1));
            }
        }

        Collections.reverse(resultFeed); //to get most recent to least recent

        if(resultFeed.size() > 10)
        {
            return resultFeed.subList(0,10); //displaying recent 10 feeds
        }

        return resultFeed;
     }

    /** A Follower follows a followed. Nothing happens if invalid */
    public void follow(int followerId, int followedId)
    {
        if(followsInfo.containsKey(followerId))
        {
            followsInfo.get(followerId).add(followedId); //update follwedIds for a followerId
        }
        else
        {
            List<Integer> follows = new ArrayList<Integer>();
            follows.add(followedId);

            followsInfo.put(followerId, follows); //initial entry in HashMap
        }
    }

    /** A Follower unfollows a followed. Nothing happens if invalid */
    public void unfollow(int followerId, int followedId)
    {
        if(followsInfo.containsKey(followerId))
        {
            List<Integer> tmpFollows = followsInfo.get(followerId);
            int ind = tmpFollows.indexOf(followedId);
            tmpFollows.remove(ind); //unfollows a followedId
        }
    }
}


