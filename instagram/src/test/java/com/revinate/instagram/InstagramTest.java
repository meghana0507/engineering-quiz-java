package com.revinate.instagram;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InstagramTest {

    @Test
    public void getMediaFeed() throws Exception {
        Instagram instagram = new Instagram();
        instagram.postMedia(1,1025);
        Thread.sleep(1);
        instagram.postMedia(2,1135);
        Thread.sleep(1);
        instagram.postMedia(3,1092);
        Thread.sleep(1);
        instagram.postMedia(3,3094);
        Thread.sleep(1);
        instagram.postMedia(1,1022);

        // testing if it displays not more than 10 recent feeds
        Thread.sleep(1);
        instagram.postMedia(3,1111);
        Thread.sleep(1);
        instagram.postMedia(2, 2222);
        instagram.postMedia(3,3333);
        Thread.sleep(1);
        instagram.postMedia(3,4444);
        instagram.postMedia(3,5555);
        Thread.sleep(1);
        instagram.postMedia(3,6666);
        instagram.postMedia(3,7777);
        Thread.sleep(1);
        instagram.postMedia(3,8888);

        instagram.follow(1, 2);
        instagram.follow(1, 3);

        List<Integer> feed = instagram.getMediaFeed(1);
        assertThat(feed.size(), is(10));
        assertThat(feed.get(0), is(8888));
        assertThat(feed.get(1), is(7777));
        assertThat(feed.get(2), is(6666));
        assertThat(feed.get(3), is(5555));
        assertThat(feed.get(4), is(4444));
        assertThat(feed.get(5), is(3333));
        assertThat(feed.get(6), is(2222));
        assertThat(feed.get(7), is(1111));
        assertThat(feed.get(8), is(1022));
        assertThat(feed.get(9), is(3094));

        instagram.unfollow(1, 3);

        List<Integer> feed_2 = instagram.getMediaFeed(1);
        assertThat(feed_2.get(0), is(2222));
        assertThat(feed_2.get(1), is(1022));
        assertThat(feed_2.get(2), is(1135));
        assertThat(feed_2.get(3), is(1025));

    }
}